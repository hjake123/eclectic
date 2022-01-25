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
        singleTexture(Registration.POINTER_ITEM.get().getRegistryName().getPath(), mcLoc("item/generated"),
                "layer0", modLoc("item/pointer"));
    }
}
