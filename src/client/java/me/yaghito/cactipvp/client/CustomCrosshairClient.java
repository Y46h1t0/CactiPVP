package me.yaghito.cactipvp.client;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import me.yaghito.cactipvp.CustomCrosshairModule;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CustomCrosshairClient implements ClientModInitializer {
    private static final double RAYCAST_RANGE = 256.0;

    @Override
    public void onInitializeClient() {
        System.out.println("[CactiPVP] Custom crosshair utilities initialized");
    }

    public static Entity getRaycastEntity(Minecraft client) {
        if (client.player == null || client.level == null) {
            return null;
        }

        Vec3 eyePos = client.player.getEyePosition();
        Vec3 viewVec = client.player.getViewVector(1.0f);
        Vec3 rayEnd = eyePos.add(viewVec.scale(RAYCAST_RANGE));

        Entity closestEntity = null;
        double closestDistance = RAYCAST_RANGE;

        for (Entity entity : client.level.entitiesForRendering()) {
            if (entity == client.player) {
                continue;
            }

            if (!(entity instanceof LivingEntity)) {
                continue;
            }

            AABB bb = entity.getBoundingBox().expandTowards(entity.getPickRadius(), entity.getPickRadius(), entity.getPickRadius());
            java.util.Optional<Vec3> intersection = bb.clip(eyePos, rayEnd);

            if (intersection.isPresent()) {
                double distance = eyePos.distanceTo(intersection.get());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestEntity = entity;
                }
            }
        }

        return closestEntity;
    }

    public static boolean shouldRenderCrosshair(Minecraft client) {
        CustomCrosshairModule module = (CustomCrosshairModule) ModuleManager.get().get(CustomCrosshairModule.class);
        if (module == null || !module.active()) {
            return false;
        }

        if (!module.hideWhenNotLooking.get()) {
            return true;
        }

        Entity lookedEntity = getRaycastEntity(client);
        return lookedEntity instanceof Player && lookedEntity != client.player;
    }

    public static int getCrosshairColor(Minecraft client) {
        CustomCrosshairModule module = (CustomCrosshairModule) ModuleManager.get().get(CustomCrosshairModule.class);
        if (module == null) {
            return 0xFFFFFFFF;
        }

        Entity lookedEntity = getRaycastEntity(client);
        boolean lookingAtPlayer = lookedEntity instanceof Player && lookedEntity != client.player;

        return lookingAtPlayer ? module.playerTargetColor.get().color() : module.crosshairColor.get().color();
    }

    public static int getCrosshairScale(Minecraft client) {
        CustomCrosshairModule module = (CustomCrosshairModule) ModuleManager.get().get(CustomCrosshairModule.class);
        if (module == null) {
            return 100;
        }
        return module.crosshairScale.get();
    }

    public static int getLineThickness(Minecraft client) {
        CustomCrosshairModule module = (CustomCrosshairModule) ModuleManager.get().get(CustomCrosshairModule.class);
        if (module == null) {
            return 1;
        }
        return module.lineThickness.get();
    }

    public static int getGapSize(Minecraft client) {
        CustomCrosshairModule module = (CustomCrosshairModule) ModuleManager.get().get(CustomCrosshairModule.class);
        if (module == null) {
            return 3;
        }
        return module.gapSize.get();
    }
}
