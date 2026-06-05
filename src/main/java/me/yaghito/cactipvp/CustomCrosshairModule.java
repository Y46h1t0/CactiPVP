package me.yaghito.cactipvp;

import com.dwarslooper.cactus.client.feature.module.Category;
import com.dwarslooper.cactus.client.feature.module.Module;
import com.dwarslooper.cactus.client.systems.config.settings.impl.BooleanSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.ColorSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.IntegerSetting;
import com.dwarslooper.cactus.client.systems.config.settings.impl.Setting;

import java.awt.*;

import static me.yaghito.cactipvp.Cactipvp.PVP_CATEGORY;

public class CustomCrosshairModule extends Module {

    public Setting<ColorSetting.ColorValue> crosshairColor;
    public Setting<ColorSetting.ColorValue> playerTargetColor;
    public Setting<Boolean> dynamicVisibility;
    public Setting<Boolean> hideWhenNotLooking;
    public Setting<Integer> crosshairScale;
    public Setting<Integer> lineThickness;
    public Setting<Integer> gapSize;

    public CustomCrosshairModule() {
        super("Custom Crosshair", PVP_CATEGORY);
        this.crosshairColor = this.mainGroup.add(new ColorSetting("Crosshair Color", new ColorSetting.ColorValue(new java.awt.Color(255, 255, 255), false)));
        this.playerTargetColor = this.mainGroup.add(new ColorSetting("Player Target Color", new ColorSetting.ColorValue(new java.awt.Color(0, 255, 0), false)));
        this.dynamicVisibility = this.mainGroup.add(new BooleanSetting("Dynamic Visibility", Boolean.TRUE));
        this.hideWhenNotLooking = this.mainGroup.add(new BooleanSetting("Hide When Not Looking at Player", Boolean.TRUE));
        this.crosshairScale = this.mainGroup.add(new IntegerSetting("Crosshair Scale", 100).min(50).max(300));
        this.lineThickness = this.mainGroup.add(new IntegerSetting("Line Thickness", 1).min(1).max(3));
        this.gapSize = this.mainGroup.add(new IntegerSetting("Gap Size", 3).min(0).max(10));
    }
}
