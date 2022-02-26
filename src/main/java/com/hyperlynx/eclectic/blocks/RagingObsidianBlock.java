package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import com.hyperlynx.eclectic.util.ConfigMan;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class RagingObsidianBlock extends Block {
    public RagingObsidianBlock(Properties prop) {
        super(prop);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random){
        Vec3 center = Vec3.atCenterOf(pos);
        if(level.dimensionType().ultraWarm() && ConfigMan.COMMON.ragingBurning.get()) {
            int scale = ConfigMan.COMMON.rageRange.get();
            List<Entity> nearby_entities = level.getEntities(null, AABB.ofSize(center, scale, scale, scale));
            for (Entity nearby_entity : nearby_entities) {
                if (!nearby_entity.fireImmune()) {
                    nearby_entity.setSecondsOnFire(5);
                }
            }
            if(level.isEmptyBlock(pos.above())){
                level.playLocalSound(center.x, center.y, center.z, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 0.5F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.8), false);
                level.setBlock(pos.above(), Blocks.FIRE.defaultBlockState(), 2);
            }
        }

        if((level.isRaining() && level.canSeeSky(pos.above())) || level.isWaterAt(pos.above())){
            if(level.isClientSide()){
                level.playLocalSound(center.x, center.y, center.z, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.8), false);
                for(int l = 0; l < 8; ++l) {
                    level.addParticle(ParticleTypes.LARGE_SMOKE, (double)pos.getX() + Math.random(), (double)pos.getY() + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            }
            level.setBlock(pos, Registration.DEAD_OBSIDIAN.get().defaultBlockState(), 2);
        }

        if(level.dimension().equals(Level.END)) {
            if(level.isClientSide()){
                level.playLocalSound(center.x, center.y, center.z, SoundEvents.CONDUIT_DEACTIVATE, SoundSource.BLOCKS, 0.5F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.8), false);
                for(int l = 0; l < 8; ++l) {
                    level.addParticle(ParticleTypes.SOUL, (double)pos.getX() + Math.random(), (double)pos.getY() + Math.random(), (double)pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            }
            level.setBlock(pos, Registration.MOURNING_OBSIDIAN.get().defaultBlockState(), 2);
        }
    }

        @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction direction){
        return true;
    }

    @Override
    public boolean isBurning(BlockState state, BlockGetter world, BlockPos pos){
        return true;
    }

    // Stolen from CryingObsidianBlock, because, well obviously
    @Override
    public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Random pRandom) {
        int reps = 1;
        if(pLevel.dimensionType().ultraWarm()) reps = 4;
        for(int i = 0; i < reps; i++) {
            Direction direction = Direction.getRandom(pRandom);
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            if (!pState.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, direction.getOpposite())) {
                double d0 = direction.getStepX() == 0 ? pRandom.nextDouble() : 0.5D + (double) direction.getStepX() * 0.6D;
                double d1 = direction.getStepY() == 0 ? pRandom.nextDouble() : 0.5D + (double) direction.getStepY() * 0.6D;
                double d2 = direction.getStepZ() == 0 ? pRandom.nextDouble() : 0.5D + (double) direction.getStepZ() * 0.6D;
                if(!pLevel.dimension().equals(Level.END)) {
                    pLevel.addParticle(ParticleTypes.FLAME, (double) pPos.getX() + d0, (double) pPos.getY() + d1, (double) pPos.getZ() + d2, 0, 0, 0);
                }
                else{
                    pLevel.addParticle(ParticleTypes.SOUL_FIRE_FLAME, (double) pPos.getX() + d0, (double) pPos.getY() + d1, (double) pPos.getZ() + d2, 0, 0, 0);

                }
            }
        }
    }
}
