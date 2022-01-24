package com.hyperlynx.eclectic;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EclecticMod.MODID)
public class EclecticMod
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "eclectic";

    public EclecticMod() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigMan.commonSpec);
    }


}