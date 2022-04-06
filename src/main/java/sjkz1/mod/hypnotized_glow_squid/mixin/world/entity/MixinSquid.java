package sjkz1.mod.hypnotized_glow_squid.mixin.world.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sjkz1.mod.hypnotized_glow_squid.HypnotizeGlowSquid;

@Mixin(GlowSquid.class)
public class MixinSquid extends WaterAnimal {


    private TargetingConditions targetingConditions = TargetingConditions.forCombat().range(16).selector(livingEntity -> this.isLookingAtMe((Player) livingEntity));

    protected MixinSquid(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "aiStep", at = @At("TAIL"))
    public void aiStep(CallbackInfo ci) {
        if (targetingConditions != null) {
            Player player = this.level.getNearestPlayer(targetingConditions, this);
            if (player != null) {
                if (isLookingAtMe(player)) {
                    player.addEffect(new MobEffectInstance(HypnotizeGlowSquid.HYPNOTIZE, 100, 1));
                    player.level.addParticle(ParticleTypes.GLOW, player.getRandomX(0.6), player.getRandomY(), player.getRandomZ(0.6), 0.0, 0.0, 0.0);
                }
            }
        }
    }

    boolean isLookingAtMe(Player player) {
        ItemStack itemStack = player.getInventory().armor.get(3);
        if (itemStack.is(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        }
        Vec3 vec3 = player.getViewVector(1.0F).normalize();
        Vec3 vec32 = new Vec3(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
        double d = vec32.length();
        vec32 = vec32.normalize();
        double e = vec3.dot(vec32);
        return e > 1.0D - 0.025D / d ? player.hasLineOfSight(this) : false;
    }

}
