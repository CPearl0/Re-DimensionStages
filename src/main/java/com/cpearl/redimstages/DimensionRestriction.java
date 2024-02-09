package com.cpearl.redimstages;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DimensionRestriction {
    public static final Component DEFAULT_MESSAGE = Component.translatable("message.redimstages.noentry");
    public Component restrictionMessage = DEFAULT_MESSAGE;

    private final Set<String> requiredStages = new HashSet<>();
    private final Set<String> unmodifiable = Collections.unmodifiableSet(requiredStages);

    public boolean addStage(String stage) {
        if (!GameStageHelper.isValidStageName(stage)) {
            throw new IllegalArgumentException("The stage '" + stage + "' is invalid.");
        }
        if (!GameStageHelper.isStageKnown(stage)) {
            ReDimensionStages.LOGGER.warn("Restriction configured with unknown stage name '{}'. Refer to your known_stages.json file.", stage);
        }
        return this.requiredStages.add(stage);
    }

    public Set<String> getRequiredStages() {
        return unmodifiable;
    }
    public boolean isRestricted(Player player) {
        return !GameStageHelper.hasAllOf(player, requiredStages);
    }
}
