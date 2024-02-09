package com.cpearl.redimstages;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;

public class ActionStageDimension implements IRuntimeAction {
    private final String dimensionId;
    private final String[] stages;
    private final Component restrictionMessage;
    public ActionStageDimension(String dimensionId, @Nullable Component message, String... stages) {
        this.dimensionId = dimensionId;
        this.stages = stages;
        this.restrictionMessage = message;
    }

    @Override
    public boolean validate(Logger logger) {
        if (ResourceLocation.tryParse(this.dimensionId) == null) {
            logger.error("[Re-Dimension Stages] Invalid dimension '" + this.dimensionId + "'.");
            return false;
        }

        var validStages = new HashSet<String>();
        for (var stage : this.stages) {
            if (!GameStageHelper.isValidStageName(stage)) {
                logger.error("[Re-Dimension Stages] Invalid stage name '" + stage + "' for dimension '" + this.dimensionId + "'. " + this.getDeclaredScriptPosition());
                continue;
            }

            if (!GameStageHelper.getKnownStages().isEmpty() && !GameStageHelper.isStageKnown(stage)) {
                logger.warn("[Re-Dimension Stages] Unknown stage '" + stage + "' for dimension '" + this.dimensionId + "' " + this.getDeclaredScriptPosition());
            }

            validStages.add(stage);
        }

        if (validStages.isEmpty()) {
            logger.error("[Re-Dimension Stages] No valid stages specified for dimension '" + this.dimensionId + "'. " + this.getDeclaredScriptPosition());
            return false;
        }

        return true;
    }

    @Override
    public void apply () {
        var restriction =  ReDimensionStages.RESTRICTIONS.addRestriction(new ResourceLocation(this.dimensionId), new DimensionRestriction());

        for (var stage : this.stages) {
            if (GameStageHelper.isValidStageName(stage)) {
                restriction.addStage(stage);
            }
        }

        restriction.restrictionMessage = this.restrictionMessage;
    }

    @Override
    public String describe () {
        return "[Re-Dimension Stages] Staging dimension '" + this.dimensionId + "' to stage(s) '" + Arrays.toString(this.stages) + "'. " + this.getDeclaredScriptPosition();
    }

    @Override
    public String systemName() {
        return "Re-DimensionStages";
    }
}
