package io.github.tenneb22.tater.id;

import io.github.tenneb22.tater.TaterMod;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {
    public static final BlockItemId TINY_POTATO = create("tiny_potato");

    private static BlockItemId create(String name) {
        Identifier id = TaterMod.id(name);
        return BlockItemId.create(id, id);
    }
}
