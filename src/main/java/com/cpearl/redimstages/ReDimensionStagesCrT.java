package com.cpearl.redimstages;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.network.chat.Component;
import org.openzen.zencode.java.ZenCodeType;

@ZenCodeType.Name("mods.redimstages.ReDimensionStages")
@ZenRegister
public class ReDimensionStagesCrT {
    @ZenCodeType.Method
    public static void restrict(String dimensionId, String... stages) {
        CraftTweakerAPI.apply(new ActionStageDimension(dimensionId, DimensionRestriction.DEFAULT_MESSAGE, stages));
    }

    @ZenCodeType.Method
    public static void restrictWithMessage(String dimensionId, Component message, String... stages) {
        CraftTweakerAPI.apply(new ActionStageDimension(dimensionId, message, stages));
    }
}
