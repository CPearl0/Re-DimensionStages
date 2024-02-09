# 维度阶段重制版(Re-Dimension Stages)
这是模组[Dimension Stages](https://github.com/Darkhax-Minecraft/DimensionStages)的非官方高版本重制版。<br>
前置：[Game Stages](https://github.com/Darkhax-Minecraft/Game-Stages)<br>

通过[CraftTweaker](https://github.com/CraftTweaker/CraftTweaker)进行配置。<br>
首先导包：<br>
```ZenScript
import mods.redimstages.ReDimensionStages;
```
设置进入暮色森林维度需要"twilightforest"阶段：<br>
```ZenScript
ReDimensionStages.restrict("twilightforest:twilight_forest", "twilightforest");
```
设置进入暮色森林维度需要"stage1"阶段和"stage2"阶段：<br>
```ZenScript
ReDimensionStages.restrict("twilightforest:twilight_forest", "stage1", "stage2");
```
更改拒绝进入维度信息：<br>
```ZenScript
ReDimensionStages.restrictWithMessage("twilightforest:twilight_forest", "Cannot go to twilight forest!", "twilightforest");
```
---
This is an unofficial version of mod [Dimension Stages](https://github.com/Darkhax-Minecraft/DimensionStages) for higher MC version.<br>
Dependency: [Game Stages](https://github.com/Darkhax-Minecraft/Game-Stages)<br>

Config through [CraftTweaker](https://github.com/CraftTweaker/CraftTweaker)<br>
Firstly import the package:<br>
```ZenScript
import mods.redimstages.ReDimensionStages;
```
Go into twilightforest need stage "twilightforest":<br>
```ZenScript
ReDimensionStages.restrict("twilightforest:twilight_forest", "twilightforest");
```
Go into twilightforest need both stage "stage1" and stage "stage2":<br>
```ZenScript
ReDimensionStages.restrict("twilightforest:twilight_forest", "stage1", "stage2");
```
Modify the message when denying player from going into the dimension:<br>
```ZenScript
ReDimensionStages.restrictWithMessage("twilightforest:twilight_forest", "Cannot go to twilight forest!", "twilightforest");
```
