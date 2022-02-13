package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import com.ibm.icu.text.MessagePattern;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class FlareSconceBlock extends SconceBlock { // This is the Blazing Sconce.

    ParticleOptions particles;
    public FlareSconceBlock(Properties prop, ParticleOptions particleOptions) {
        super(prop);
        particles = particleOptions;
    }

    @Override
    public void animateTick(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Random random) {
        for (int i = 0; i < 3; i++) {
            double d0 = random.nextDouble() * 16 - 8;
            double d1 = random.nextDouble() * 16 - 8;
            double d2 = random.nextDouble() * 16 - 8;
            level.addParticle(particles, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, 0, 0, 0);
        }
    }
}
