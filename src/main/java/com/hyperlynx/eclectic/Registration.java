package com.hyperlynx.eclectic;

import com.hyperlynx.eclectic.items.Pointer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

}
