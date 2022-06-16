package com.hyperlynx.eclectic.datagen;

import com.hyperlynx.eclectic.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
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
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(Items.SMOOTH_STONE), Registration.EYE_STONE.get())
                .unlockedBy("eyeGet", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENDER_EYE))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Registration.MIND_LANTERN_ITEM.get())
                .requires(Items.SOUL_LANTERN)
                .requires(Items.PHANTOM_MEMBRANE)
                .unlockedBy("membrane'd", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PHANTOM_MEMBRANE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(Registration.HAMMER_ITEM.get())
                .pattern(" ii")
                .pattern(" ci")
                .pattern("c  ")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('c', Tags.Items.INGOTS_COPPER)
                .unlockedBy("ironic", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);
    }
}
