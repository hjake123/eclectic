package com.hyperlynx.eclectic.items;

import com.hyperlynx.eclectic.Registration;
import com.hyperlynx.eclectic.blocks.PhantomQuiltBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class PhantomQuiltItem extends BlockItem {
    public PhantomQuiltItem(Properties pProperties) {
        super(Registration.PHANTOM_QUILT.get(), pProperties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean selected){
        int ACTIVATE_HEIGHT = 8; // Number of blocks to fall before activating. Configurable?
        if(entity.fallDistance > ACTIVATE_HEIGHT && !entity.isInvulnerableTo(DamageSource.FALL)){
            BlockPos underfoot = entity.blockPosition().below();
            if(level.isEmptyBlock(underfoot)) {
                level.setBlock(underfoot, Registration.PHANTOM_QUILT.get().defaultBlockState(), 2);
                stack.setCount(0);
            }
        }
    }
}
