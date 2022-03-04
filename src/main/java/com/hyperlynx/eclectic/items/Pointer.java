package com.hyperlynx.eclectic.items;

import com.hyperlynx.eclectic.Registration;
import com.hyperlynx.eclectic.util.Helper;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Pointer extends Item {

    int RANGE = 64;
    double BLINDNESS_ANGLE = Mth.TWO_PI / 32d;
    int CAT_ATTRACTION_RANGE = 8;

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
        if (user instanceof Player player) {
            var blockHit = Helper.playerRayTrace(level, player, ClipContext.Fluid.NONE, ClipContext.Block.VISUAL,
                RANGE);
            var blockHitPos = blockHit.getLocation();
            var start = player.getEyePosition();
            var end = start.add(player.getLookAngle().scale(RANGE));
            var entityHit = ProjectileUtil.getEntityHitResult(
                player, start, end, new AABB(start, end), e -> e instanceof LivingEntity, Double.MAX_VALUE
            );
            // Check which is closer
            Vec3 laserPos;
            if (entityHit == null) {
                laserPos = blockHitPos;
            } else if (entityHit.getLocation().distanceToSqr(start) < blockHitPos.distanceToSqr(start)) {
                laserPos = entityHit.getLocation();
            } else {
                laserPos = blockHitPos;
            }

            if (user instanceof LocalPlayer) {
                for (int i = 0; i < 5; i++) {
                    level.addParticle(Registration.LASER_PARTICLE, laserPos.x,laserPos.y, laserPos.z, 0, 0, 0);
                }
            } else if (user instanceof ServerPlayer splayer) {
                if (entityHit != null) {
                    var target = (LivingEntity) entityHit.getEntity();
                    var targetEyes = target.getEyePosition();

                    var perfectHit = targetEyes.subtract(start).normalize();
                    var hitDeltaTh = angleBetween(perfectHit, splayer.getLookAngle());
                    var targetLookDeltaTh = angleBetween(perfectHit, target.getLookAngle());
                    if (hitDeltaTh <= BLINDNESS_ANGLE && targetLookDeltaTh >= Mth.HALF_PI) {
                        var blindness = new MobEffectInstance(MobEffects.BLINDNESS, 5, 0);
                        target.addEffect(blindness);
                    }
                }

                var catAABB = new AABB(
                    blockHitPos.subtract(CAT_ATTRACTION_RANGE, CAT_ATTRACTION_RANGE, CAT_ATTRACTION_RANGE),
                    blockHitPos.add(CAT_ATTRACTION_RANGE, CAT_ATTRACTION_RANGE, CAT_ATTRACTION_RANGE));
                var cats = level.getEntities((Entity) null, catAABB,
                    e -> (e instanceof Ocelot || e instanceof Cat)
                        && e.distanceToSqr(laserPos) <= CAT_ATTRACTION_RANGE * CAT_ATTRACTION_RANGE);
                for (var e : cats) {
                    var cat = (PathfinderMob) e;
                    cat.getNavigation().moveTo(laserPos.x, laserPos.y, laserPos.z, 1d);
                    cat.lookAt(EntityAnchorArgument.Anchor.EYES, laserPos);
                }
            }
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

    private static double angleBetween(Vec3 a, Vec3 b) {
        return Math.acos(
            a.dot(b) / (a.length() * b.length()));
    }
}
