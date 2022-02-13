package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, EclecticMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.POINTER_ITEM.get(), "Pointer");
        add(Registration.WEEPING_OBSIDIAN.get(), "Weeping Obsidian");
        add(Registration.SOBBING_OBSIDIAN.get(), "Sobbing Obsidian");
        add(Registration.RAGING_OBSIDIAN.get(), "Raging Obsidian");
        add(Registration.MOURNING_OBSIDIAN.get(), "Mourning Obsidian");
        add(Registration.DEAD_OBSIDIAN.get(), "Depleted Obsidian");
        add(Registration.SCONCE.get(), "Amethyst Sconce");
        add(Registration.GLOW_SCONCE.get(), "Glowing Sconce");
        add(Registration.BLAZE_SCONCE.get(), "Blazing Sconce");
        add(Registration.SOUL_SCONCE.get(), "Soulful Sconce");
        add(Registration.PHANTOM_QUILT.get(), "Phantom Quilt");
        add(Registration.EYE_STONE.get(), "Observant Stone");
        add(Registration.MIND_LANTERN.get(), "Mind Lantern");
    }
}
