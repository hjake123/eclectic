package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Ref;

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
                .add(Registration.DEAD_OBSIDIAN.get())
                .add(Registration.SCONCE.get())
                .add(Registration.GLOW_SCONCE.get())
                .add(Registration.BLAZE_SCONCE.get())
                .add(Registration.SOUL_SCONCE.get())
                .add(Registration.EYE_STONE.get())
                .add(Registration.MIND_LANTERN.get())
                .add(Registration.MOVING_OBSIDIAN.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(Registration.WEEPING_OBSIDIAN.get())
                .add(Registration.SOBBING_OBSIDIAN.get())
                .add(Registration.RAGING_OBSIDIAN.get())
                .add(Registration.MOURNING_OBSIDIAN.get())
                .add(Registration.MOVING_OBSIDIAN.get())
                .add(Registration.DEAD_OBSIDIAN.get());
        tag(BlockTags.PIGLIN_REPELLENTS)
                .add(Registration.RAGING_OBSIDIAN.get());
        tag(BlockTags.STRIDER_WARM_BLOCKS)
                .add(Registration.RAGING_OBSIDIAN.get());
        tag(BlockTags.HOGLIN_REPELLENTS)
                .add(Registration.MOURNING_OBSIDIAN.get())
                .add(Registration.SOUL_SCONCE.get());
        tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(Registration.MOURNING_OBSIDIAN.get())
                .add(Registration.EYE_STONE.get());
        tag(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(Registration.SCONCE.get())
                .add(Registration.GLOW_SCONCE.get())
                .add(Registration.BLAZE_SCONCE.get())
                .add(Registration.SOUL_SCONCE.get());
    }


    @NotNull
    @Override
    public String getName() {
        return "Eclectic Mod Tags";
    }
}
