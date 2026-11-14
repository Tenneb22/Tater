package io.github.tenneb22.tater.id;

import io.github.tenneb22.tater.Tater;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {
    public static final BlockItemId TINY_POTATO = create("tiny_potato");

    private static BlockItemId create(String name) {
        Identifier id = Tater.id(name);
        return BlockItemId.create(id, id);
    }
}
