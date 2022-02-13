package com.hyperlynx.eclectic.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class MindLanternBlock extends LanternBlock {

    public static final BooleanProperty ON = BooleanProperty.create("on");

    public MindLanternBlock(Properties p) {
        super(p);
        registerDefaultState(stateDefinition.any().setValue(ON, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ON);
        super.createBlockStateDefinition(builder);
    }

    // Lovingly borrowed from PhantomSpawner.java
    private boolean playerHasInsomnia(Player player){
        ServerStatsCounter serverstatscounter = ((ServerPlayer) player).getStats();
        return serverstatscounter.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) >= 72000;
    }

    // Copied from LanternBlock because i was getting annoyed.
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context){
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                    blockstate = blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
                    boolean insom = false;
                    if(!context.getLevel().isClientSide()){
                        insom = playerHasInsomnia(context.getPlayer());
                    }
                    return blockstate.setValue(ON, context.getLevel().dimension().equals(Level.END) || insom);
                }
            }
        }
        return null;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(!level.isClientSide()) {
            if(player.getItemInHand(hand).is(Items.ENDER_EYE) && !level.dimension().equals(Level.NETHER)){
                level.setBlock(pos, state.setValue(ON, true), 2);
                return InteractionResult.CONSUME;
            }
            if(!level.dimension().equals(Level.NETHER) && !level.dimension().equals(Level.END)){
                ServerStatsCounter serverstatscounter = ((ServerPlayer) player).getStats();
                level.setBlock(pos, state.setValue(ON, playerHasInsomnia(player)), 2);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos){
        return state.getValue(ON) ? 13 : 5;
    }

}
