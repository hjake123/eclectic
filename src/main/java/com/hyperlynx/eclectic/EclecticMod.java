package com.hyperlynx.eclectic;

import com.hyperlynx.eclectic.util.ConfigMan;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EclecticMod.MODID)
public class EclecticMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "eclectic";

    public EclecticMod() {
        Registration.init();

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigMan.commonSpec);

    }

    @SubscribeEvent
    public void loadComplete(FMLLoadCompleteEvent event) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> Registration::loadCompleteClient);
    }

}
