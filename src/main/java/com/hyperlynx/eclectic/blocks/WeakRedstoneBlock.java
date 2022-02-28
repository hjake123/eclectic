package com.hyperlynx.eclectic.blocks;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WeakRedstoneBlock extends Block {

    static IntegerProperty POWER = BlockStateProperties.POWER;

    public WeakRedstoneBlock(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(POWER, 0));
    }

    public static BlockColor getColor() {
        return (state, world, pos, tintIndex) -> RedStoneWireBlock.getColorForPower(state.getValue(WeakRedstoneBlock.POWER));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }
}
