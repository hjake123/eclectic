package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EyeStoneBlock extends Block {
    public static final BooleanProperty CLOSED = BooleanProperty.create("closed");

    public EyeStoneBlock(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(CLOSED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLOSED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState pState) {
        return true;
    }

    // Secret blink mechanic!
    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random){
        if(level.random.nextFloat() < 0.01 && !state.getValue(CLOSED)){ //Temporary high value
            level.setBlockAndUpdate(pos, state.setValue(CLOSED, true));
            level.scheduleTick(new BlockPos(pos), this, 15); // Schedule a tick to test if we should remain weighed down.
        }
    }

    @Override
    public void tick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random rand) {
        level.setBlockAndUpdate(pos, state.setValue(CLOSED, false));
    }
}
