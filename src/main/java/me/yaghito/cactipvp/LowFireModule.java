package me.yaghito.cactipvp;

import com.dwarslooper.cactus.client.feature.module.Module;
import com.dwarslooper.cactus.client.systems.config.settings.impl.IntegerSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.Setting;
import static me.yaghito.cactipvp.Cactipvp.PVP_CATEGORY;

public class LowFireModule extends Module {
    public Setting<Integer> fireY;

    public LowFireModule() {
        super("Low Fire", PVP_CATEGORY);
        this.fireY = this.mainGroup.add(new IntegerSetting("Fire Y offset", 0).min(-100).max(100));
    }
}