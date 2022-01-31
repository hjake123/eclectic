package com.hyperlynx.eclectic;

import com.hyperlynx.eclectic.util.ConfigMan;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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


}
