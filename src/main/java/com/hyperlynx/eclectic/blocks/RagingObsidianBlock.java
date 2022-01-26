package com.hyperlynx.eclectic.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RagingObsidianBlock extends Block {
    public RagingObsidianBlock(Properties prop) {
        super(prop);
    }

    // Stolen from CryingObsidianBlock, because, well obviously
    @Override
    public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
        for(int i = 0; i < 4; i++) {
            Direction direction = Direction.getRandom(pRandom);
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (!pState.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, direction.getOpposite())) {
                double d0 = direction.getStepX() == 0 ? pRandom.nextDouble() : 0.5D + (double) direction.getStepX() * 0.6D;
                double d1 = direction.getStepY() == 0 ? pRandom.nextDouble() : 0.5D + (double) direction.getStepY() * 0.6D;
                double d2 = direction.getStepZ() == 0 ? pRandom.nextDouble() : 0.5D + (double) direction.getStepZ() * 0.6D;
                pLevel.addParticle(ParticleTypes.FLAME, (double) pPos.getX() + d0, (double) pPos.getY() + d1, (double) pPos.getZ() + d2, 0, 0, 0);
            }
        }
    }
}
