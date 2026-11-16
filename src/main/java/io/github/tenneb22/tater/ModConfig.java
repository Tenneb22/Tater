package io.github.tenneb22.tater;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry(isSlider = true, min = 0, max = 100)
    public static int angryTaterSpawnChance = 20;
}
