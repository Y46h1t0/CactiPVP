package me.yaghito.cactipvp;

import com.dwarslooper.cactus.client.feature.module.Category;
import com.dwarslooper.cactus.client.feature.module.Module;
import com.dwarslooper.cactus.client.systems.config.settings.impl.BooleanSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.ColorSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.Setting;

import java.awt.*;

import static me.yaghito.cactipvp.Cactipvp.PVP_CATEGORY;

public class CustomHitboxModule extends Module {

    public Setting<ColorSetting.ColorValue> defaulthitbox;
    public Setting<ColorSetting.ColorValue> watchingoutofrange;
    public Setting<ColorSetting.ColorValue> inrange;
    public Setting<ColorSetting.ColorValue> watchinginrange;
    public Setting<Boolean> showvertex;


    public CustomHitboxModule() {
        super("Custom Hitboxes", PVP_CATEGORY);
        this.defaulthitbox = this.mainGroup.add(new ColorSetting("Default Hitboxes", new ColorSetting.ColorValue(new java.awt.Color(0xffffff), false)));
        this.watchingoutofrange = this.mainGroup.add(new ColorSetting("Out of range + Looking", new ColorSetting.ColorValue(new java.awt.Color(0xffffff), false)));
        this.inrange = this.mainGroup.add(new ColorSetting("In Range", new ColorSetting.ColorValue(new java.awt.Color(0xC4B0B0), false)));
        this.watchinginrange = this.mainGroup.add(new ColorSetting("In Range + Looking", new ColorSetting.ColorValue(new java.awt.Color(0xFF0000), false)));
        this.showvertex = this.mainGroup.add(new BooleanSetting("Show vertex", Boolean.TRUE));
    }


}