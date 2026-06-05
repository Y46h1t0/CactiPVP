package me.yaghito.cactipvp.client.mixin;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import com.mojang.blaze3d.vertex.PoseStack;
import me.yaghito.cactipvp.LowShieldModule;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void onRenderArmWithItem(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo ci) {

        LowShieldModule module = (LowShieldModule) ModuleManager.get().get(LowShieldModule.class);
        if (module == null || !module.active()) return;

        // If the item currently being rendered is NOT a shield, leave it completely alone!
        if (!itemStack.is(Items.SHIELD)) {
            return;
        }

        // Isolate the matrix state specifically for the shield
        poseStack.pushPose();

        if (hand == InteractionHand.MAIN_HAND) {
            poseStack.translate(
                    module.mainHandX.get() / 100.0f,
                    module.mainHandY.get() / 100.0f,
                    0
            );
        } else {
            poseStack.translate(
                    module.offhandX.get() / 100.0f,
                    module.offhandY.get() / 100.0f,
                    0
            );
        }
    }

    @Inject(method = "renderArmWithItem", at = @At("TAIL"))
    private void onRenderArmWithItemTail(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo ci) {

        LowShieldModule module = (LowShieldModule) ModuleManager.get().get(LowShieldModule.class);
        if (module == null || !module.active()) return;

        // Only pop the pose if it was pushed (meaning the item is a shield)
        if (itemStack.is(Items.SHIELD)) {
            poseStack.popPose();
        }
    }
}