package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = EclecticMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new ModRecipes(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new ModItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new ModLanguageProvider(generator, "en_us"));
        }
    }
}
