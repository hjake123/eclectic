package com.hyperlynx.eclectic.items;

import com.hyperlynx.eclectic.util.Helper;
import com.mojang.math.Vector3f;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class Pointer extends Item {

    int RANGE = 64;

    public Pointer(Item.Properties props) {
        super(props);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity user, @NotNull ItemStack stack, int remainingUseDuration) {
        if(level.isClientSide() && user.getType().equals(EntityType.PLAYER)) {
            BlockHitResult ray = Helper.playerRayTrace(level, (Player) user, ClipContext.Fluid.NONE, ClipContext.Block.COLLIDER, RANGE);
            level.addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1.0f, 0.1f, 0.05f), 1.0f),
                    ray.getLocation().x, ray.getLocation().y, ray.getLocation().z,
                    0, 0, 0);
        }
    }

    @NotNull
    @Override
    public UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return Integer.MAX_VALUE;
    }
}
