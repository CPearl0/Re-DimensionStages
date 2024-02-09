package com.cpearl.redimstages;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Restrictions extends SimplePreparableReloadListener<Void> {
    private final Map<ResourceLocation, DimensionRestriction> restrictions = new HashMap<>();
    public Restrictions() {
        MinecraftForge.EVENT_BUS.addListener(this::onEntityTravelToDimension);
        MinecraftForge.EVENT_BUS.addListener(this::addReloadListeners);
    }

    public DimensionRestriction addRestriction (ResourceLocation dimensionId, DimensionRestriction restriction) {

        this.restrictions.put(dimensionId, restriction);
        return restriction;
    }

    private void onEntityTravelToDimension (EntityTravelToDimensionEvent event) {

        if (event.getEntity() instanceof final ServerPlayer player) {
            var dimensionId = event.getDimension().location();
            var restriction = restrictions.get(dimensionId);
            if (restriction != null && restriction.isRestricted(player)) {
                event.setCanceled(true);
                ReDimensionStages.LOGGER.debug("Restricted {} from accessing dimension {}. Restriction={}", player.getDisplayName().getString(), dimensionId, restriction);

                final Component message = restriction.restrictionMessage;
                if (message != null) {
                    player.displayClientMessage(message, true);
                }
            }
        }
    }

    private void addReloadListeners (AddReloadListenerEvent event) {
        event.addListener(this);
    }

    @Override
    protected @NotNull Void prepare (ResourceManager manager, ProfilerFiller profiler) {
        return null;
    }

    @Override
    protected void apply (Void data, ResourceManager manager, ProfilerFiller profiler) {
        this.restrictions.clear();
    }
}
