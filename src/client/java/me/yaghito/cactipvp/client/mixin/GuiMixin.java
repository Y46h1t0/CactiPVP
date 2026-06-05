package me.yaghito.cactipvp.client.mixin;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import me.yaghito.cactipvp.CustomCrosshairModule;
import me.yaghito.cactipvp.client.CustomCrosshairClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Gui.class)
public class GuiMixin {
    // Placeholder - crosshair rendering requires finding the right injection point in MC 26.1
    // Available methods on Gui are limited and rendering context isn't accessible from common hooks
}

