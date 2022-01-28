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
    }
}
