package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import com.ibm.icu.text.MessagePattern;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.data.SoundDefinition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class WeepingObsidianBlock extends Block {
    public WeepingObsidianBlock(Properties prop) {
        super(prop);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random){
        if(level.isRaining() && level.canSeeSky(pos.above())){
            level.setBlock(pos, Registration.SOBBING_OBSIDIAN.get().defaultBlockState(), 2);
        }
        if(level.dimensionType().ultraWarm()){
            level.setBlock(pos, Registration.RAGING_OBSIDIAN.get().defaultBlockState(), 2);
        }
        if(level.dimension().equals(Level.END)){
            level.setBlock(pos, Blocks.CRYING_OBSIDIAN.defaultBlockState(), 2);
        }
    }

    // Stolen from CryingObsidianBlock, because, well obviously
    @Override
    public void animateTick(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, Random pRandom) {
        if (pRandom.nextInt(4) == 0) {
            Direction direction = Direction.getRandom(pRandom);
            if (direction != Direction.UP) {
                BlockPos blockpos = pPos.relative(direction);
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (!pState.canOcclude() || !blockstate.isFaceSturdy(pLevel, blockpos, direction.getOpposite())) {
                    double d0 = direction.getStepX() == 0 ? pRandom.nextDouble() : 0.5D + (double)direction.getStepX() * 0.6D;
                    double d1 = direction.getStepY() == 0 ? pRandom.nextDouble() : 0.5D + (double)direction.getStepY() * 0.6D;
                    double d2 = direction.getStepZ() == 0 ? pRandom.nextDouble() : 0.5D + (double)direction.getStepZ() * 0.6D;
                    pLevel.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (double)pPos.getX() + d0, (double)pPos.getY() + d1, (double)pPos.getZ() + d2, 0, 0, 0);
                }
            }
        }
    }
}
