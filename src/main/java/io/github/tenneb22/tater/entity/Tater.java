package io.github.tenneb22.tater.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class Tater extends TamableAnimal {
    private static final Item FOOD = Items.POTATO;

    protected Tater(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 1.5));
        goalSelector.addGoal(2, new FollowOwnerGoal(this, 1, 10, 2.5f));
        goalSelector.addGoal(3, new RandomStrollGoal(this, 0.6));
        goalSelector.addGoal(4, new RandomLookAroundGoal(this));
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

        else if (isFood(itemStack) && !level().isClientSide()) {
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
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }
}
