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
        withExistingParent("eclectic:item/weeping_obsidian", modLoc("block/weeping_obsidian"));
        withExistingParent("eclectic:item/sobbing_obsidian", modLoc("block/sobbing_obsidian"));
        withExistingParent("eclectic:item/raging_obsidian", modLoc("block/raging_obsidian"));
        withExistingParent("eclectic:item/mourning_obsidian", modLoc("block/mourning_obsidian"));
        withExistingParent("eclectic:item/moving_obsidian", modLoc("block/moving_obsidian"));
        withExistingParent("eclectic:item/depleted_obsidian", modLoc("block/depleted_obsidian"));
        withExistingParent("eclectic:item/eye_stone", modLoc("block/eye_stone"));
    }
}
