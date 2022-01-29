package com.hyperlynx.eclectic.blocks;

import com.hyperlynx.eclectic.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class SconceBlock extends Block { // This is only the Amethyst Sconce.
    public SconceBlock(BlockBehaviour.Properties prop) {
        super(prop);
    }

    protected static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 12, 12);

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(player.getItemInHand(hand).is(Items.AMETHYST_SHARD)){
            level.setBlock(pos, Registration.SCONCE.get().defaultBlockState(), 2);
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 0.4F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.6));
            return InteractionResult.SUCCESS;
        }
        if(player.getItemInHand(hand).is(Items.GLOWSTONE_DUST)){
            level.setBlock(pos, Registration.GLOW_SCONCE.get().defaultBlockState(), 2);
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 0.4F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.8));
            return InteractionResult.SUCCESS;
        }
        if(player.getItemInHand(hand).is(Items.BLAZE_POWDER)) {
            level.setBlock(pos, Registration.BLAZE_SCONCE.get().defaultBlockState(), 2);
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 0.4F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.4));
            return InteractionResult.SUCCESS;
        }
        if(player.getItemInHand(hand).is(Items.SOUL_SOIL)) {
            level.setBlock(pos, Registration.SOUL_SCONCE.get().defaultBlockState(), 2);
            level.playSound(player, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 0.4F, (float) (2.6 + (level.random.nextFloat() - level.random.nextFloat()) * 0.2));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
