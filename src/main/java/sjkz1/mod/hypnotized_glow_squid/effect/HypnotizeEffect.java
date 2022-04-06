package sjkz1.mod.hypnotized_glow_squid.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class HypnotizeEffect extends MobEffect {

    public HypnotizeEffect() {
        super(MobEffectCategory.HARMFUL, 0x135c4b);
    }
    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        livingEntity.makeStuckInBlock(livingEntity.getFeetBlockState(), new Vec3(0.9f, 0.3f, 0.9f));
    }
}
