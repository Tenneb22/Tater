package io.github.tenneb22.tater;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TaterDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(TaterBlockLootTableProvider::new);
		pack.addProvider(TaterModelProvider::new);
		pack.addProvider(TaterRecipeProvider::new);
	}
}
