package me.yaghito.cactipvp.client.mixin;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import me.yaghito.cactipvp.ParticleControlModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Shadow private Minecraft minecraft;

    @Inject(method = "addParticle", at = @At("HEAD"), cancellable = true)
    private void onAddParticle(ParticleOptions particle, double x, double y, double z, double xd, double yd, double zd, CallbackInfo ci) {
        ParticleControlModule module = (ParticleControlModule) ModuleManager.get().get(ParticleControlModule.class);
        if (module == null || !module.active()) return;

        // Particle disabler logic
        if (particle.getType() == ParticleTypes.TOTEM_OF_UNDYING && !module.TotemParticle.get()) {
            ci.cancel(); return;
        }
        if ((particle.getType() == ParticleTypes.PORTAL || particle.getType() == ParticleTypes.REVERSE_PORTAL) && !module.EnderpearlParticle.get()) {
            ci.cancel(); return;
        }
        if ((particle.getType() == ParticleTypes.CRIT || particle.getType() == ParticleTypes.ENCHANTED_HIT) && !module.CritParticle.get()) {
            ci.cancel(); return;
        }
        if ((particle.getType() == ParticleTypes.EXPLOSION || particle.getType() == ParticleTypes.EXPLOSION_EMITTER) && !module.ExplosionParticle.get()) {
            ci.cancel(); return;
        }
        if ((particle.getType() == ParticleTypes.GUST || particle.getType() == ParticleTypes.GUST_EMITTER_LARGE || particle.getType() == ParticleTypes.GUST_EMITTER_SMALL) && !module.MaceParticle.get()) {
            ci.cancel(); return;
        }
        if (particle.getType() == ParticleTypes.ENCHANTED_HIT && !module.EnchantedHitParticle.get()) {
            ci.cancel(); return;
        }

        // Particle multiplier logic
        int multiplier = module.ParticleMultiplier.get();
        if (multiplier == 0) return;

        if (multiplier > 0) {
            // Spawn extra particles
            for (int i = 0; i < multiplier; i++) {
                minecraft.particleEngine.createParticle(particle, x, y, z, xd, yd, zd);
            }
        } else {
            // Reduce particles probabilistically
            float cancelChance = Math.abs(multiplier) / 5.0f;
            if (Math.random() < cancelChance) {
                ci.cancel();
            }
        }
    }
}