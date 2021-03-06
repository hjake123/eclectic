package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import com.hyperlynx.eclectic.util.ConfigMan;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MourningObsidianBlock extends Block {
    public MourningObsidianBlock(Properties prop) {
        super(prop);
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entity) {
        if(ConfigMan.COMMON.mourningTP.get()) {
            int range = ConfigMan.COMMON.mourningRange.get();
            int x = pos.getX() + level.getRandom().nextInt(2*range) - range;
            int y = pos.getY() + level.getRandom().nextInt(range);
            int z = pos.getZ() + level.getRandom().nextInt(2*range) - range;
            BlockPos tpPos = new BlockPos(x, y, z);
            if (level.isEmptyBlock(tpPos.above()))
                entity.teleportTo(x, y, z);
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        if(!level.isRaining() && level.canSeeSky(pos.above()) && level.isDay() && level.random.nextFloat() < 0.1){
            level.setBlock(pos, Blocks.OBSIDIAN.defaultBlockState(), 2);
        }else if(level.dimension().equals(Level.NETHER)){
            level.setBlock(pos, Registration.MOVING_OBSIDIAN.get().defaultBlockState(), 2);
        }
    }

    public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(100) == 0) {
            Direction direction = Direction.getRandom(pRandom);
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (!pState.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, direction.getOpposite())) {
                double d0 = direction.getStepX() == 0 ? pRandom.nextDouble() : 0.5D + (double)direction.getStepX() * 0.6D;
                double d1 = direction.getStepY() == 0 ? pRandom.nextDouble() : 0.5D + (double)direction.getStepY() * 0.6D;
                double d2 = direction.getStepZ() == 0 ? pRandom.nextDouble() : 0.5D + (double)direction.getStepZ() * 0.6D;
                pLevel.addParticle(ParticleTypes.DRIPPING_WATER, (double)pPos.getX() + d0, (double)pPos.getY() + d1, (double)pPos.getZ() + d2, 0, 0, 0);
            }
        }
    }

}
