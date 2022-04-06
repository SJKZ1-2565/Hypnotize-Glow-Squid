package sjkz1.mod.hypnotized_glow_squid;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.apache.logging.log4j.LogManager;
import sjkz1.mod.hypnotized_glow_squid.effect.HypnotizeEffect;


public class HypnotizeGlowSquid implements ModInitializer {
    public static final MobEffect HYPNOTIZE = new HypnotizeEffect();

    @Override
    public void onInitialize() {
        LogManager.getLogger("Hypnotized Glow Squid").info("Glow and Behold!");
        Registry.register(Registry.MOB_EFFECT, new ResourceLocation("hypnotized_glow_squid", "hypnotize"), HYPNOTIZE);
    }
}
