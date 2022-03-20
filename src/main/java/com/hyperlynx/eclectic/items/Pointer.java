package com.hyperlynx.eclectic.items;

import com.hyperlynx.eclectic.Registration;
import com.hyperlynx.eclectic.util.ConfigMan;
import com.hyperlynx.eclectic.util.Helper;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.EnderMan;
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
        int cat_range = ConfigMan.COMMON.laserCatRange.get();
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

            if (level.isClientSide()) {
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
                    if(ConfigMan.COMMON.laserBlindness.get() && !(target instanceof Ocelot || target instanceof Cat
                            || target instanceof EnderMan || target instanceof EnderDragon || target instanceof WitherBoss)) {
                        if (hitDeltaTh <= BLINDNESS_ANGLE && targetLookDeltaTh >= Mth.HALF_PI) {
                            var blindness = new MobEffectInstance(MobEffects.BLINDNESS, 5, 0);
                            target.addEffect(blindness);
                        }
                    }
                }

                if(ConfigMan.COMMON.laserCatRange.get() > 0) {
                    var catAABB = new AABB(
                            blockHitPos.subtract(cat_range, cat_range, cat_range),
                            blockHitPos.add(cat_range, cat_range, cat_range));
                    var cats = level.getEntities((Entity) null, catAABB,
                            e -> (e instanceof Ocelot || e instanceof Cat)
                                    && e.distanceToSqr(laserPos) <= cat_range * cat_range);
                    for (var e : cats) {
                        var cat = (PathfinderMob) e;
                        if(!cat.hasEffect(MobEffects.BLINDNESS)) {
                            cat.getNavigation().moveTo(laserPos.x, laserPos.y, laserPos.z, 1.2d);
                            cat.lookAt(EntityAnchorArgument.Anchor.EYES, laserPos);
                        }
                    }
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
