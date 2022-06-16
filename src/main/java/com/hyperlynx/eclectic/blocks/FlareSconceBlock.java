package com.hyperlynx.eclectic.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FlareSconceBlock extends SconceBlock { // This is the Blazing Sconce.

    ParticleOptions particles;
    public FlareSconceBlock(Properties prop, ParticleOptions particleOptions) {
        super(prop);
        particles = particleOptions;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        for (int i = 0; i < 3; i++) {
            double d0 = random.nextDouble() * 16 - 8;
            double d1 = random.nextDouble() * 16 - 8;
            double d2 = random.nextDouble() * 16 - 8;
            level.addParticle(particles, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, 0, 0, 0);
        }
    }
}
