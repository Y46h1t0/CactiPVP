package me.yaghito.cactipvp;

import com.dwarslooper.cactus.client.addon.v2.ICactusAddon;
import com.dwarslooper.cactus.client.addon.v2.RegistryBus;
import com.dwarslooper.cactus.client.feature.module.Category;
import com.dwarslooper.cactus.client.feature.module.Module;
import net.minecraft.world.item.Items;

public class Cactipvp implements ICactusAddon {
    public static final Category PVP_CATEGORY = new Category("PvP", Items.DIAMOND_SWORD);

    @Override
    public void onInitialize(RegistryBus registryBus) {
        // PVP CATEGORY REGISTRY
        registryBus.register(Category.class, ctx -> PVP_CATEGORY);

        // LOW SHIELD REGISTRY
        registryBus.register(Module.class, ctx -> new LowShieldModule());

        // PARTICLE CONTROL REGISTRY
        registryBus.register(Module.class, ctx -> new ParticleControlModule());

        // LOW FIRE REGISTRY
        registryBus.register(Module.class, ctx -> new LowFireModule());

        // CUSTOM HITBOX REGISTRY
        registryBus.register(Module.class, ctx -> new CustomHitboxModule());

        // CUSTOM CROSSHAIR REGISTRY
        // registryBus.register(Module.class, ctx -> new CustomCrosshairModule());
    }

    @Override
    public void onLoadComplete() {

    }

    @Override
    public void onShutdown() {

    }


}