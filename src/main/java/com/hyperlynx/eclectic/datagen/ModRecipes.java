package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registration.POINTER_ITEM.get())
                .pattern("cac")
                .pattern("crc")
                .pattern(" c ")
                .define('c', Tags.Items.INGOTS_COPPER)
                .define('a', Tags.Items.GEMS_AMETHYST)
                .define('r', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy("copperGet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Registration.WEEPING_OBSIDIAN_ITEM.get())
                .requires(Blocks.CRYING_OBSIDIAN)
                .requires(Items.GHAST_TEAR)
                .unlockedBy("cryingObsidianGet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GHAST_TEAR))
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.SCONCE_ITEM.get())
                .pattern(" a ")
                .pattern("ggg")
                .define('g', Tags.Items.INGOTS_GOLD)
                .define('a', Tags.Items.GEMS_AMETHYST)
                .unlockedBy("gemGet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.PHANTOM_QUILT_ITEM.get())
                .pattern("psp")
                .pattern("sps")
                .pattern("psp")
                .define('p', Items.PHANTOM_MEMBRANE)
                .define('s', Tags.Items.STRING)
                .unlockedBy("membrane'd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PHANTOM_MEMBRANE))
                .save(consumer);
    }
}
