package me.yaghito.cactipvp.client;

import com.dwarslooper.cactus.client.feature.module.ModuleManager;
import me.yaghito.cactipvp.CustomHitboxModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class CactipvpClient implements ClientModInitializer {
    private static final double RAYCAST_RANGE = 256.0; // Extended range for raycast

    @Override
    public void onInitializeClient() {
        System.out.println("[CactiPVP] Hitbox renderer initialized");

        LevelRenderEvents.AFTER_TRANSLUCENT_FEATURES.register(context -> {
            Minecraft client = Minecraft.getInstance();
            if (client.level == null || client.player == null) {
                return;
            }

            CustomHitboxModule module = (CustomHitboxModule) ModuleManager.get().get(CustomHitboxModule.class);
            if (module == null || !module.active()) {
                return;
            }

            double attackRange = client.player.entityInteractionRange();
            Entity lookedEntity = getRaycastEntity(client);
            int drawn = 0;

            for (Entity entity : client.level.entitiesForRendering()) {
                if (entity == client.player) {
                    continue;
                }

                if (!(entity instanceof LivingEntity)) {
                    continue;
                }

                double distance = client.player.distanceTo(entity);
                boolean inRange = distance <= attackRange;
                boolean looking = entity == lookedEntity;
                int color = getColor(module, inRange, looking);

                Gizmos.cuboid(entity.getBoundingBox(), GizmoStyle.stroke(color));
                if (module.showvertex.get()) {
                    Vec3 eyePos = entity.getEyePosition();
                    Vec3 viewVec = entity.getViewVector(1.0f);
                    Gizmos.arrow(eyePos, eyePos.add(viewVec.scale(2.0)), color);
                }
                drawn++;
            }

            if (drawn == 0) {
                System.out.println("[CactiPVP] No entities found for hitbox rendering");
            }
        });
    }

    private static Entity getRaycastEntity(Minecraft client) {
        if (client.player == null || client.level == null) {
            return null;
        }

        Vec3 eyePos = client.player.getEyePosition();
        Vec3 viewVec = client.player.getViewVector(1.0f);
        Vec3 rayEnd = eyePos.add(viewVec.scale(RAYCAST_RANGE));

        EntityHitResult result = null;
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
                    result = new EntityHitResult(entity);
                }
            }
        }

        return result != null ? result.getEntity() : null;
    }

    private static int getColor(CustomHitboxModule module, boolean inRange, boolean looking) {
        if (inRange && looking) {
            return module.watchinginrange.get().color();
        }
        if (inRange) {
            return module.inrange.get().color();
        }
        if (looking) {
            return module.watchingoutofrange.get().color();
        }
        return module.defaulthitbox.get().color();
    }
}
