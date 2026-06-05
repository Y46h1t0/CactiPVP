package me.yaghito.cactipvp;

import com.dwarslooper.cactus.client.feature.module.Category;
import com.dwarslooper.cactus.client.feature.module.Module;
import com.dwarslooper.cactus.client.systems.config.settings.impl.IntegerSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.Setting;

import static me.yaghito.cactipvp.Cactipvp.PVP_CATEGORY;

public class LowShieldModule extends Module {
    public Setting<Integer> offhandY;
    public Setting<Integer> offhandX;
    public Setting<Integer> mainHandY;
    public Setting<Integer> mainHandX;

    public LowShieldModule() {
        super("Low Shield", PVP_CATEGORY);
        this.offhandY = this.mainGroup.add(new IntegerSetting("offhand Y offset", 0).min(-100).max(100));
        this.offhandX = this.mainGroup.add(new IntegerSetting("offhand X offset", 0).min(-100).max(100));
        this.mainHandY = this.mainGroup.add(new IntegerSetting("main hand Y offset", 0).min(-100).max(100));
        this.mainHandX = this.mainGroup.add(new IntegerSetting("main hand X offset", 0).min(-100).max(100));
    }
}