package me.yaghito.cactipvp.client.mixin;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import com.mojang.blaze3d.vertex.PoseStack;
import me.yaghito.cactipvp.LowFireModule;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

    @Inject(method = "renderFire", at = @At("HEAD"))
    private static void onRenderFire(PoseStack poseStack, MultiBufferSource bufferSource, TextureAtlasSprite sprite, CallbackInfo ci) {
        LowFireModule module = (LowFireModule) ModuleManager.get().get(LowFireModule.class);
        if (module == null || !module.active()) return;

        poseStack.translate(0, module.fireY.get() / 100.0f, 0);
    }
}