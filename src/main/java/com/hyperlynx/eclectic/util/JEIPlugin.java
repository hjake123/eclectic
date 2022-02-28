package com.hyperlynx.eclectic.util;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(EclecticMod.MODID, "jei_plugin");
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        IModPlugin.super.registerRecipes(registration);
        registration.addIngredientInfo(new ItemStack(Registration.WEEPING_OBSIDIAN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("Crying Obsidian whose true emotions have been unlocked. Exposing it to a harsh environment might further change its emotional state..."));
        registration.addIngredientInfo(new ItemStack(Registration.SOBBING_OBSIDIAN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("The rain has dampened its mood. Best to let it dry out in the sun."));
        registration.addIngredientInfo(new ItemStack(Registration.RAGING_OBSIDIAN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("What was quiet sorrow has changed to burning resentment. Perhaps a deep void would help it calm down."));
        registration.addIngredientInfo(new ItemStack(Registration.MOURNING_OBSIDIAN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("The fires of rage have given way to grief. Maybe the light of the sun will allow it to finally heal."));
        registration.addIngredientInfo(new ItemStack(Registration.MOVING_OBSIDIAN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("Mournful grief is set to motion by a place filled with spirits. It wanders aimlessly through unobstructed spaces. Return it to the void to let it rest."));
        registration.addIngredientInfo(new ItemStack(Registration.DEAD_OBSIDIAN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("It's gone."));
        registration.addIngredientInfo(new ItemStack(Registration.PHANTOM_QUILT_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("A floating quilt that breaks any fall. If you fall while one is in your inventory, it will deploy beneath you automatically."));
        registration.addIngredientInfo(new ItemStack(Registration.SCONCE_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("A decorative sconce. By applying different items, it changes form. These items can be used: \n\nAmethyst Shard\nGlowstone Dust\nBlaze Powder\nSoul Soil"));
        registration.addIngredientInfo(new ItemStack(Registration.MIND_LANTERN_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("A psychic lantern. If touched by one who might soon fall prey to insomnia, it will glow green.\n\nAn Eye of Ender can be used to activate it freely."));
        registration.addIngredientInfo(new ItemStack(Registration.HAMMER_ITEM.get()), VanillaTypes.ITEM,
                Component.nullToEmpty("This hammer can be used to force trapdoors to remain in place, or to free them."));


    }
}
