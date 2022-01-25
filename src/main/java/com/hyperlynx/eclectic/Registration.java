package com.hyperlynx.eclectic;

import com.hyperlynx.eclectic.blocks.WeepingObsidianBlock;
import com.hyperlynx.eclectic.items.Pointer;
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

    private static final Item.Properties ITEMPROP = new Item.Properties();
    public static final RegistryObject<Item> POINTER_ITEM = ITEMS.register("pointer",
            () -> new Pointer(ITEMPROP.stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));

    private static final BlockBehaviour.Properties STONEPROP =  Block.Properties.of(Material.STONE);
    public static final RegistryObject<Block> WEEPING_OBSIDIAN = BLOCKS.register("weeping_obsidian",
            () -> new WeepingObsidianBlock(STONEPROP
                    .requiresCorrectToolForDrops()
                    .strength(49.0F, 1100.0F)
                    .lightLevel((BlockState bs) -> {return 10;})));
    public static final RegistryObject<Item> WEEPING_OBSIDIAN_ITEM = fromBlock(WEEPING_OBSIDIAN);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEMPROP));
    }

}
