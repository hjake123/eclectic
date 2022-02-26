package com.hyperlynx.eclectic.mixin;

// Mixin class to add "LOCKED" property to trapdoors.

import com.hyperlynx.eclectic.EclecticMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.modify.LocalVariableDiscriminator;

@Mixin(TrapDoorBlock.class)
public abstract class TrapDoorBlockMixin {
    private static final BooleanProperty LOCKED = BlockStateProperties.LOCKED;

    @Inject(method = "createBlockStateDefinition", at = @At("RETURN"))
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder, CallbackInfo ci) {
        pBuilder.add(LOCKED);
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    protected void use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        if (pState.hasProperty(LOCKED) && pState.getValue(LOCKED)) {
            cir.setReturnValue(InteractionResult.PASS);
            cir.cancel(); // Don't open the trapdoor when LOCKED.
        }
    }

    @ModifyVariable(method="getStateForPlacement", at=@At("LOAD"))
    protected BlockState getStateForPlacement(BlockState blockstate) {
         return blockstate.setValue(LOCKED, false);
    }
}
