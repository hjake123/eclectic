package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MovingObsidianBlock extends Block {
    public MovingObsidianBlock(Properties props) {
        super(props);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random){
        if(level.dimension().equals(Level.END)) {
            if (level.random.nextFloat() < 0.8F) {
                level.setBlock(pos, Registration.MOURNING_OBSIDIAN.get().defaultBlockState(), 2);
            }else{
                level.setBlock(pos, Registration.DEAD_OBSIDIAN.get().defaultBlockState(), 2);
            }
        }

        // Movement!
        BlockPos target = pos.relative(Direction.getRandom(level.random));
        if(level.getBlockState(target).isAir()){
            level.setBlock(target, state, 2);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }

}
