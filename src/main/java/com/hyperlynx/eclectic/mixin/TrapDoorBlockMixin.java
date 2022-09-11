package com.hyperlynx.eclectic.mixin;

// Mixin class to add "LOCKED" property to trapdoors.

import com.hyperlynx.eclectic.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrapDoorBlock.class)
public abstract class TrapDoorBlockMixin extends Block {
    private static final BooleanProperty LOCKED = BlockStateProperties.LOCKED;

    public TrapDoorBlockMixin(Properties p_57526_) {
        super(p_57526_); // Dummy constructor
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    protected void TrapDoorBlock(BlockBehaviour.Properties p_57526_, CallbackInfo ci) {
        this.registerDefaultState(this.defaultBlockState().setValue(LOCKED, false));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("RETURN"))
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder, CallbackInfo ci) {
        pBuilder.add(LOCKED);
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    protected void use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        if(pPlayer.getItemInHand(pHand).is(Registration.HAMMER_ITEM.get())){
            boolean was_locked = pState.getValue(LOCKED);
            pLevel.setBlock(pPos, pState.cycle(LOCKED), 2);
            if(was_locked){
                pLevel.playSound(pPlayer, pPos, SoundEvents.WOODEN_DOOR_OPEN, SoundSource.PLAYERS, 0.5F, 1.2F);
                pPlayer.displayClientMessage(Component.translatable("message.eclectic.trapdoor.unlock"), true);
            }else{
                pLevel.playSound(pPlayer, pPos, SoundEvents.WOODEN_DOOR_CLOSE, SoundSource.PLAYERS, 0.5F, 0.8F);
                pPlayer.displayClientMessage(Component.translatable("message.eclectic.trapdoor.lock"), true);
            }
            cir.setReturnValue(InteractionResult.SUCCESS);
            cir.cancel();
        }
        else if (pState.hasProperty(LOCKED) && pState.getValue(LOCKED)) {
            cir.setReturnValue(InteractionResult.PASS);
            cir.cancel(); // Don't open the trapdoor when LOCKED.
        }
    }

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    public void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir){
        if(context.getPlayer() != null) {
            if (context.getPlayer().getOffhandItem().is(Registration.HAMMER_ITEM.get())) {
                cir.setReturnValue(cir.getReturnValue().setValue(LOCKED, true));
                context.getLevel().playSound(null, context.getClickedPos(), SoundEvents.WOODEN_DOOR_CLOSE, SoundSource.PLAYERS, 0.5F, 0.8F);
            } else {
                cir.setReturnValue(cir.getReturnValue().setValue(LOCKED, false));
            }
        }
    }
}
