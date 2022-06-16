package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {
    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, EclecticMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Non-block based item models are custom.
        withExistingParent(Registration.WEEPING_OBSIDIAN.get().getName().toString(), modLoc("block/weeping_obsidian"));
        withExistingParent(Registration.SOBBING_OBSIDIAN.get().getName().toString(), modLoc("block/sobbing_obsidian"));
        withExistingParent(Registration.RAGING_OBSIDIAN.get().getName().toString(), modLoc("block/raging_obsidian"));
        withExistingParent(Registration.MOURNING_OBSIDIAN.get().getName().toString(), modLoc("block/mourning_obsidian"));
        withExistingParent(Registration.MOVING_OBSIDIAN.get().getName().toString(), modLoc("block/moving_obsidian"));
        withExistingParent(Registration.DEAD_OBSIDIAN.get().getName().toString(), modLoc("block/depleted_obsidian"));
        withExistingParent(Registration.EYE_STONE.get().getName().toString(), modLoc("block/eye_stone"));
    }
}
