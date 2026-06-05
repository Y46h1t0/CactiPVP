package me.yaghito.cactipvp.client.mixin;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.yaghito.cactipvp.CustomCrosshairModule;
import me.yaghito.cactipvp.client.CustomCrosshairClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    // This mixin is currently a placeholder
    // GameRenderer rendering occurs too early/late for proper 2D crosshair overlay
    // Would need to hook into screen rendering instead
}

