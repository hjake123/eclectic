package com.hyperlynx.eclectic.util;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigMan {
    public static class Common {
        public ForgeConfigSpec.BooleanValue parachuteQuilt;
        public ForgeConfigSpec.BooleanValue ragingBurning;
        public ForgeConfigSpec.IntValue rageRange;
        public ForgeConfigSpec.BooleanValue mourningTP;
        public ForgeConfigSpec.IntValue mourningRange;
        public ForgeConfigSpec.IntValue insomniaTicks;
        public ForgeConfigSpec.IntValue quiltActivateHeight;
        Common(ForgeConfigSpec.Builder builder){
            builder.comment("Config Settings")
                    .push("config");
            parachuteQuilt = builder.comment("Phantom Quilt acts like a parachute. [Default: true]")
                    .define("parachuteQuilt", true);
            quiltActivateHeight = builder.comment("Number of blocks to fall before the Phantom Quilt opens. [Default: 8]")
                    .defineInRange("quiltActivateHeight", 8, 1, 383);
            ragingBurning = builder.comment("Raging Obsidian in the Nether occasionally burns nearby entities. [Default: true]")
                    .define("ragingBurning", true);
            rageRange = builder.comment("Range in blocks of Raging Obsidian's entity burning effect. [Default: 5]")
                    .defineInRange("rageRange", 5, 1, 1024);
            mourningTP = builder.comment("Mourning Obsidian teleports creatures that walk on top of it. [Default: true]")
                    .define("mourningTP", true);
            mourningRange = builder.comment("Range in blocks of Mourning Obsidian's teleportation. [Default: 10]")
                    .defineInRange("mourningRange", 10, 1, 1024);
            insomniaTicks = builder.comment("Number of game ticks without sleeping before the Mind Lantern turns on. [Default: 72000]")
                    .defineInRange("insomniaTicks", 72000, 1, Integer.MAX_VALUE);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

}
