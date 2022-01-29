package com.hyperlynx.eclectic;

import com.hyperlynx.eclectic.blocks.*;
import com.hyperlynx.eclectic.items.Pointer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
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

    public static final RegistryObject<Block> WEEPING_OBSIDIAN = BLOCKS.register("weeping_obsidian",
            () -> new WeepingObsidianBlock(Block.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(20.0F, 1100.0F)
                    .lightLevel((BlockState bs) -> 10)));
    public static final RegistryObject<Item> WEEPING_OBSIDIAN_ITEM = fromBlock(WEEPING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SOBBING_OBSIDIAN = BLOCKS.register("sobbing_obsidian",
            () -> new SobbingObsidianBlock(Block.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(18.0F, 1000.0F)
                    .lightLevel((BlockState bs) -> 6)
                    .friction(0.99F)));
    public static final RegistryObject<Item> SOBBING_OBSIDIAN_ITEM = fromBlock(SOBBING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> RAGING_OBSIDIAN = BLOCKS.register("raging_obsidian",
            () -> new RagingObsidianBlock(Block.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(24.0F, 2000.0F)
                    .lightLevel((BlockState bs) -> 14)));
    public static final RegistryObject<Item> RAGING_OBSIDIAN_ITEM = fromBlock(RAGING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MOURNING_OBSIDIAN = BLOCKS.register("mourning_obsidian",
            () -> new MourningObsidianBlock(Block.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(10.0F, 50.0F)
                    .lightLevel((BlockState bs) -> 0)));
    public static final RegistryObject<Item> MOURNING_OBSIDIAN_ITEM = fromBlock(MOURNING_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> DEAD_OBSIDIAN = BLOCKS.register("depleted_obsidian",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(0.8F)
                    .lightLevel((BlockState bs) -> 0)
                    .sound(SoundType.CALCITE)));
    public static final RegistryObject<Item> DEAD_OBSIDIAN_ITEM = fromBlock(DEAD_OBSIDIAN, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SCONCE = BLOCKS.register("sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(1.0F)
                    .noOcclusion()
                    .lightLevel((BlockState bs) -> 3)));
    public static final RegistryObject<Item> SCONCE_ITEM = fromBlock(SCONCE, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> GLOW_SCONCE = BLOCKS.register("glow_sconce",
            () -> new SconceBlock(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(1.0F)
                    .noOcclusion()
                    .lightLevel((BlockState bs) -> 15)));
    public static final RegistryObject<Item> GLOW_SCONCE_ITEM = fromBlock(GLOW_SCONCE, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> BLAZE_SCONCE = BLOCKS.register("blaze_sconce",
            () -> new FlareSconceBlock(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(1.0F)
                    .noOcclusion()
                    .lightLevel((BlockState bs) -> 7),ParticleTypes.FLAME));
    public static final RegistryObject<Item> BLAZE_SCONCE_ITEM = fromBlock(BLAZE_SCONCE, CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> SOUL_SCONCE = BLOCKS.register("soul_sconce",
            () -> new FlareSconceBlock(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(1.0F)
                    .noOcclusion()
                    .lightLevel((BlockState bs) -> 6),ParticleTypes.SOUL_FIRE_FLAME));
    public static final RegistryObject<Item> SOUL_SCONCE_ITEM = fromBlock(SOUL_SCONCE, CreativeModeTab.TAB_DECORATIONS);

    // Helper method for BlockItem registration
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, CreativeModeTab tab) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
