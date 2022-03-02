package com.hyperlynx.eclectic.fx;

import com.hyperlynx.eclectic.mixin.client.GlowParticleAccessor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class LaserParticle implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprite;

    public LaserParticle(SpriteSet pSprites) {
        this.sprite = pSprites;
    }

    @Nullable
    @Override
    public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
        double pXSpeed, double pYSpeed, double pZSpeed) {
        GlowParticle particle = GlowParticleAccessor.eclectic$new(
            pLevel,
            pX, pY, pZ,
            0d, 0d, 0d,
            this.sprite);
        particle.setColor(1f, 0.2f, 0.2f);
        particle.setParticleSpeed(0d, 0d, 0d);
        particle.setLifetime((int)(2.0D / (pLevel.random.nextDouble() * 0.8D + 0.2D)));
        return particle;
    }
}
