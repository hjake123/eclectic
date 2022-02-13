package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModLootTables extends BaseLootTableProvider{
    public ModLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.WEEPING_OBSIDIAN.get(), createSimpleTable("weeping_obsidian", Registration.WEEPING_OBSIDIAN.get()));
        lootTables.put(Registration.SOBBING_OBSIDIAN.get(), createSimpleTable("sobbing_obsidian", Registration.SOBBING_OBSIDIAN.get()));
        lootTables.put(Registration.RAGING_OBSIDIAN.get(), createSimpleTable("raging_obsidian", Registration.RAGING_OBSIDIAN.get()));
        lootTables.put(Registration.MOURNING_OBSIDIAN.get(), createSimpleTable("weeping_obsidian", Registration.MOURNING_OBSIDIAN.get()));
        lootTables.put(Registration.DEAD_OBSIDIAN.get(), createSilkTouchTable("dead_obsidian", Registration.DEAD_OBSIDIAN.get(), Items.AIR, 0, 0));
        lootTables.put(Registration.SCONCE.get(), createSimpleTable("sconce", Registration.SCONCE.get()));
        lootTables.put(Registration.GLOW_SCONCE.get(), createSilkTouchTable("glow_sconce", Registration.GLOW_SCONCE.get(), Registration.SCONCE_ITEM.get(), 1, 1));
        lootTables.put(Registration.BLAZE_SCONCE.get(), createSilkTouchTable("blaze_sconce", Registration.BLAZE_SCONCE.get(), Registration.SCONCE_ITEM.get(), 1, 1));
        lootTables.put(Registration.SOUL_SCONCE.get(), createSilkTouchTable("soul_sconce", Registration.SOUL_SCONCE.get(), Registration.SCONCE_ITEM.get(), 1, 1));
        lootTables.put(Registration.PHANTOM_QUILT.get(), createSimpleTable("quilt", Registration.PHANTOM_QUILT.get()));
        lootTables.put(Registration.EYE_STONE.get(), createSimpleTable("eye_stone", Registration.EYE_STONE.get()));
    }
}
