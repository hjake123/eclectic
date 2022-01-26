package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModBlockTags extends BlockTagsProvider{

    public ModBlockTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, EclecticMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.WEEPING_OBSIDIAN.get())
                .add(Registration.SOBBING_OBSIDIAN.get())
                .add(Registration.RAGING_OBSIDIAN.get())
                .add(Registration.MOURNING_OBSIDIAN.get())
                .add(Registration.DEAD_OBSIDIAN.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Registration.WEEPING_OBSIDIAN.get())
                .add(Registration.SOBBING_OBSIDIAN.get())
                .add(Registration.RAGING_OBSIDIAN.get())
                .add(Registration.MOURNING_OBSIDIAN.get())
                .add(Registration.DEAD_OBSIDIAN.get());
    }


    @NotNull
    @Override
    public String getName() {
        return "Eclectic Mod Tags";
    }
}
