package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.EclecticMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PhantomQuiltBlock extends Block {
    public static final BooleanProperty WEIGHED = BooleanProperty.create("weighed");

    protected static final VoxelShape SHAPE = Block.box(1, 2, 1, 15, 3, 15);
    protected static final VoxelShape SHAPE_LOW = Block.box(1, 1, 1, 15, 2, 15);
    protected static final VoxelShape TOUCH_SHAPE = Block.box(1, 2, 1, 15, 4, 15);

    public PhantomQuiltBlock(Properties prop) {
        super(prop);
        registerDefaultState(stateDefinition.any().setValue(WEIGHED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WEIGHED);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(WEIGHED) ? SHAPE_LOW : SHAPE;
    }

    @Override
    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity p_153365_, float p_153366_) {
        // Don't cause fall damage! ;)
        // state.setValue(WEIGHED, shouldBeWeighed(state, level, pos));
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        if(!level.isClientSide() && !state.getValue(WEIGHED)) {
            boolean last = state.getValue(WEIGHED);
            level.setBlockAndUpdate(pos, state.setValue(WEIGHED, shouldBeWeighed(state, level, pos)));
            if (last != level.getBlockState(pos).getValue(WEIGHED)) {
                EclecticMod.LOGGER.info("Changed state due to entity presence!");
            }
            level.scheduleTick(new BlockPos(pos), this, 10); // Schedule a tick to test if we should remain weighed down.
        }
    }

    @Override
    public void tick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random rand) {
        boolean last = state.getValue(WEIGHED);
        level.setBlockAndUpdate(pos, state.setValue(WEIGHED, shouldBeWeighed(state, level, pos)));
        if(state.getValue(WEIGHED)){
            level.scheduleTick(pos, this, 10);
        }
        if(last != level.getBlockState(pos).getValue(WEIGHED)){
            EclecticMod.LOGGER.info("Changed state due to tick!");
        }
    }

    private boolean shouldBeWeighed(@NotNull BlockState state, Level level, @NotNull BlockPos pos){
        Vec3 corner = Vec3.atBottomCenterOf(pos);
        List<Entity> ents = level.getEntities(null, TOUCH_SHAPE.bounds().move(pos));
        EclecticMod.LOGGER.info("Testing if should be weighed! I think: " + (ents.size() > 0));
        return ents.size() > 0;
    }

        // TODO: Change block state when stepped on, sink by 1 pixel.


}
