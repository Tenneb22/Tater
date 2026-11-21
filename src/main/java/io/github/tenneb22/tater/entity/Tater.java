package io.github.tenneb22.tater.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class Tater extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> IS_ANGRY = SynchedEntityData.defineId(Tater.class, EntityDataSerializers.BOOLEAN);
    private static final String TAG_IS_ANGRY = "isAngry";
    public static final Item FOOD = Items.POTATO;
    private static final byte USE_KNIFE_ENTITY_EVENT = (byte) 67676767;

    private final AnimationState useKnifeAnimationState = new AnimationState();

    protected Tater(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, true));
        goalSelector.addGoal(2, new PanicGoal(this, 1.5));
        goalSelector.addGoal(3, new FollowOwnerGoal(this, 1, 10, 2.5f));
        goalSelector.addGoal(4, new RandomStrollGoal(this, 0.6));
        goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Tater.class, true, this::canAttack));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, this::canAttack));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (isTame()) {
            if (isFood(itemStack) && getHealth() < getMaxHealth()) {
                feed(player, hand, itemStack, 2.0F, 2.0F);
                return InteractionResult.SUCCESS;
            }

            if (isOwnedBy(player)) {
                InteractionResult parentInteraction = super.mobInteract(player, hand);
                if (!parentInteraction.consumesAction()) {
                    setOrderedToSit(!isOrderedToSit());
                    navigation.stop();
                    return InteractionResult.SUCCESS.withoutItem();
                }

                return parentInteraction;
            }
        }

        else if (!isAngry() && isFood(itemStack) && !level().isClientSide()) {
            itemStack.consume(1, player);
            playEatingSound();
            tryToTame(player);
            return InteractionResult.SUCCESS_SERVER;
        }

        return super.mobInteract(player, hand);
    }

    private void tryToTame(Player player) {
        if (random.nextInt(5) == 0) {
            tame(player);
            navigation.stop();
            level().broadcastEntityEvent(this, (byte) 7);
        } else {
            level().broadcastEntityEvent(this, (byte) 6);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(IS_ANGRY, false);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean(TAG_IS_ANGRY, isAngry());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        setAngry(input.getBooleanOr(TAG_IS_ANGRY, false));
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(FOOD);
    }

    @Override
    public boolean canFallInLove() {
        return !isAngry() && super.canFallInLove();
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public void swingForAttack(InteractionHand hand) {
        super.swingForAttack(hand);

        // tell clients to start use knife animation
        level().broadcastEntityEvent(this, USE_KNIFE_ENTITY_EVENT);
    }

    @Override
    public void handleEntityEvent(@EntityEvent.Value byte id) {
        if (id == USE_KNIFE_ENTITY_EVENT) {
            useKnifeAnimationState.start(tickCount);
        } else {
            super.handleEntityEvent(id);
        }
    }

    private boolean canAttack(LivingEntity target, ServerLevel level) {
        return isAngry();
    }

    public boolean isAngry() {
        return entityData.get(IS_ANGRY);
    }

    public void setAngry(boolean angry) {
        entityData.set(IS_ANGRY, angry);
    }

    public AnimationState getUseKnifeAnimationState() {
        return useKnifeAnimationState;
    }
}
