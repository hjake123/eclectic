package com.hyperlynx.eclectic.fx;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.Random;

public class LaserParticle extends TextureSheetParticle {
    static final Random RANDOM = new Random();
    private final SpriteSet sprites;

    protected LaserParticle(ClientLevel level, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
        super(level, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.sprites = pSprites;

        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.quadSize *= 0.2F;
        this.hasPhysics = false;
        this.setSpriteFromAge(pSprites);
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return 0x0f00ff; // I think?
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.setAlpha(1f - ((float) this.age / (float) this.lifetime));
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class LaserParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public LaserParticleProvider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
            double pXSpeed, double pYSpeed, double pZSpeed) {
            var particle = new LaserParticle(pLevel, pX, pY, pZ, 0d, 0d, 0d, this.sprite);
            particle.setColor(1f, 0.2f, 0.2f);
            particle.setParticleSpeed(
                (RANDOM.nextDouble() - 0.5) * 0.02,
                (RANDOM.nextDouble() - 0.5) * 0.02,
                (RANDOM.nextDouble() - 0.5) * 0.02);
            particle.setLifetime((int)(2.0D / (RANDOM.nextDouble() * 0.8D + 0.2D)));
            return particle;
        }
    }
}
