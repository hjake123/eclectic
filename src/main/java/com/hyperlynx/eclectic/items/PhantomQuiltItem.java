package com.hyperlynx.eclectic.items;

import com.hyperlynx.eclectic.EclecticMod;
import com.hyperlynx.eclectic.Registration;
import com.hyperlynx.eclectic.blocks.PhantomQuiltBlock;
import com.hyperlynx.eclectic.util.ConfigMan;
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
        if(ConfigMan.COMMON.parachuteQuilt.get()) {
            int ACTIVATE_HEIGHT = ConfigMan.COMMON.quiltActivateHeight.get();
            if (entity.fallDistance > ACTIVATE_HEIGHT && !entity.isInvulnerableTo(DamageSource.FALL)) {
                BlockPos underfoot = entity.blockPosition().below();
                if (level.isEmptyBlock(underfoot)) {
                    level.setBlock(underfoot, Registration.PHANTOM_QUILT.get().defaultBlockState(), 2);
                    stack.setCount(0);
                }
            }
        }
    }
}
