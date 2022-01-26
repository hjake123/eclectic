package com.hyperlynx.eclectic;

import com.hyperlynx.eclectic.blocks.*;
import com.hyperlynx.eclectic.items.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EclecticMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EclecticMod.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    public static final RegistryObject<Item> POINTER_ITEM = ITEMS.register("pointer",
            () -> new Pointer(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));

    private static final BlockBehaviour.Properties STONEPROP =  Block.Properties.of(Material.STONE);
    public static final RegistryObject<Block> WEEPING_OBSIDIAN = BLOCKS.register("weeping_obsidian",
            () -> new WeepingObsidianBlock(STONEPROP
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 1100.0F)
                    .lightLevel((BlockState bs) -> 10)));
    public static final RegistryObject<Item> WEEPING_OBSIDIAN_ITEM = fromBlock(WEEPING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SOBBING_OBSIDIAN = BLOCKS.register("sobbing_obsidian",
            () -> new SobbingObsidianBlock(STONEPROP
                    .requiresCorrectToolForDrops()
                    .strength(4.0F, 1000.0F)
                    .lightLevel((BlockState bs) -> 6)
                    .friction(0.99F)));
    public static final RegistryObject<Item> SOBBING_OBSIDIAN_ITEM = fromBlock(SOBBING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> RAGING_OBSIDIAN = BLOCKS.register("raging_obsidian",
            () -> new RagingObsidianBlock(STONEPROP
                    .requiresCorrectToolForDrops()
                    .strength(4.0F, 2000.0F)
                    .lightLevel((BlockState bs) -> 14)));
    public static final RegistryObject<Item> RAGING_OBSIDIAN_ITEM = fromBlock(RAGING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MOURNING_OBSIDIAN = BLOCKS.register("mourning_obsidian",
            () -> new MourningObsidianBlock(STONEPROP
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 50.0F)
                    .lightLevel((BlockState bs) -> 0)));
    public static final RegistryObject<Item> MOURNING_OBSIDIAN_ITEM = fromBlock(MOURNING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> DEAD_OBSIDIAN = BLOCKS.register("depleted_obsidian",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.0F, 1.0F)
                    .lightLevel((BlockState bs) -> 0)));
    public static final RegistryObject<Item> DEAD_OBSIDIAN_ITEM = fromBlock(DEAD_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    // Helper method for BlockItem registration
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, CreativeModeTab tab) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

}
