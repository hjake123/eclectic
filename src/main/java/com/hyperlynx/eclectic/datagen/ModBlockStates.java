package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, EclecticMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.WEEPING_OBSIDIAN.get());
        simpleBlock(Registration.SOBBING_OBSIDIAN.get());
        simpleBlock(Registration.RAGING_OBSIDIAN.get());
        simpleBlock(Registration.MOURNING_OBSIDIAN.get());
        simpleBlock(Registration.DEAD_OBSIDIAN.get());
        simpleBlock(Registration.MOVING_OBSIDIAN.get());
        simpleBlock(Registration.WEAK_REDSTONE.get());
    }
}
