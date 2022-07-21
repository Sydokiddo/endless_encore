package net.sydokiddo.endlessencore.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

// Mod Config

@SuppressWarnings("all")

@Config(name = "endlessencore")
public class ModConfig implements ConfigData {

    @Comment("If true, will allow for Endless Encore's custom biomes to generate.")
    public boolean biome_generation = true;

    @Comment("If true, will allow for Endless Encore's custom structures to generate.")
    public boolean structure_generation = true;

    @Comment("If true, will allow for Endless Encore's new mobs to be able to spawn and exist. " +
            "(Warning: Setting this to false will delete any existing Endless Encore mobs from your world!)")
    public boolean mob_registry = true;

    @Comment("If true, will enable changes to the behavior of the Elytra, " +
            "such as Elytra hopping, fixed model UVs, and the Aerial Fatigue " +
            "status effect being recieved upon taking damage while gliding.")
    public boolean elytra_changes = true;

    @Comment("If true, will enable Endless Encore's Elytra camera tilting feature while gliding.")
    public boolean elytra_camera = true;

    @Comment("If true, will allow for the Ender Dragon fight to be tweaked, allowing the spawning of Snapdragons, " +
            "doubling the dragon's health to 400, and making it immune to explosive damage.")
    public boolean upgraded_dragon_fight = true;

    @Comment("If true, will allow for Dragon Omelettes to exist and be crafted.")
    public boolean allow_dragon_omelettes = true;

    @Comment("If true, will allow for Rift Fountains to be created and activated.")
    public boolean allow_rift_fountains = true;
}