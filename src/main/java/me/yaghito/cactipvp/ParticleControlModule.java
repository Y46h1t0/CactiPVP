package me.yaghito.cactipvp;

import com.dwarslooper.cactus.client.feature.module.Category;
import com.dwarslooper.cactus.client.feature.module.Module;
import com.dwarslooper.cactus.client.systems.config.settings.impl.BooleanSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.IntegerSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.Setting;

import static me.yaghito.cactipvp.Cactipvp.PVP_CATEGORY;

public class ParticleControlModule extends Module {
    public Setting<Boolean> TotemParticle;
    public Setting<Boolean> EnderpearlParticle;
    public Setting<Boolean> MaceParticle;
    public Setting<Boolean> CritParticle;
    public Setting<Boolean> EnchantedHitParticle;
    public Setting<Boolean> ExplosionParticle;

    public Setting<Integer> ParticleMultiplier;

    public ParticleControlModule() {
        super("Particle Control", PVP_CATEGORY);

        this.ParticleMultiplier = this.mainGroup.add(new IntegerSetting("Particle Multiplier", 0).min(-5).max(5));


        this.TotemParticle = this.mainGroup.add(new BooleanSetting("Totem Particles", Boolean.TRUE));
        this.EnderpearlParticle = this.mainGroup.add(new BooleanSetting("Enderpearl Particles", Boolean.TRUE));
        this.EnchantedHitParticle = this.mainGroup.add(new BooleanSetting("Enchanted Hit Particles", Boolean.TRUE));
        this.MaceParticle = this.mainGroup.add(new BooleanSetting("Mace Particles", Boolean.TRUE));
        this.CritParticle = this.mainGroup.add(new BooleanSetting("Crit Particles", Boolean.TRUE));
        this.ExplosionParticle = this.mainGroup.add(new BooleanSetting("Explosion Particles", Boolean.TRUE));

    }


}