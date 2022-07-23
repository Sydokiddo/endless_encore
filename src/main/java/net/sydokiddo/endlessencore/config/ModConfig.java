package net.sydokiddo.endlessencore.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

// Mod Config

@SuppressWarnings("all")

@Config(name = "endlessencore")
@Config.Gui.Background("minecraft:textures/block/end_stone.png")
public class ModConfig implements ConfigData {

    // Biome Generation

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public BiomeGeneration biome_generation = new BiomeGeneration();

    public static class BiomeGeneration {
    }

    // Structure Generation

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public StructureGeneration structure_generation = new StructureGeneration();

    public static class StructureGeneration {
        @ConfigEntry.Gui.Tooltip
        public boolean ethereal_city = true;
    }

    // New Mobs

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public NewMobs mobs = new NewMobs();

    public static class NewMobs {
        @ConfigEntry.Gui.Tooltip
        public boolean ethereal = true;

        @ConfigEntry.Gui.Tooltip
        public boolean baby_ethereal = true;

        @ConfigEntry.Gui.Tooltip
        public boolean gladiator = true;

        @ConfigEntry.Gui.Tooltip
        public boolean charmer = true;

        @ConfigEntry.Gui.Tooltip
        public boolean goliath = true;

        @ConfigEntry.Gui.Tooltip
        public boolean fleet = true;

        @ConfigEntry.Gui.Tooltip
        public boolean monolith = true;

        @ConfigEntry.Gui.Tooltip
        public boolean snapdragon = true;
    }

    // Vanilla Mob Changes

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public VanillaMobs vanilla_mobs = new VanillaMobs();

    public static class VanillaMobs {
        @ConfigEntry.Gui.Tooltip
        public boolean endermite_changes = true;

        @ConfigEntry.Gui.Tooltip
        public boolean shulker_changes = true;
    }

    // Elytra Changes

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public ElytraChanges elytra_changes = new ElytraChanges();

    public static class ElytraChanges {
        @ConfigEntry.Gui.Tooltip
        public boolean elytra_camera = true;

        @ConfigEntry.Gui.Tooltip
        public boolean fixed_elytra_uvs = true;

        @ConfigEntry.Gui.Tooltip
        public boolean elytra_hopping = true;

        @ConfigEntry.Gui.Tooltip
        public boolean aerial_fatigue = true;

        @ConfigEntry.Gui.Tooltip
        public boolean repair_with_chitin = true;
    }

    // Ender Dragon Changes

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public EnderDragonChanges ender_dragon_changes = new EnderDragonChanges();

    public static class EnderDragonChanges {
        @ConfigEntry.Gui.Tooltip
        public boolean increased_health = true;

        @ConfigEntry.Gui.Tooltip
        public boolean explosion_immunity = true;

        @ConfigEntry.Gui.Tooltip
        public boolean snapdragons = true;

        @ConfigEntry.Gui.Tooltip
        public boolean dragon_egg_per_player = true;

        @ConfigEntry.Gui.Tooltip
        public boolean dragon_omelette = true;
    }

    // Misc Tweaks

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = false)
    public MiscTweaks misc_tweaks = new MiscTweaks();

    public static class MiscTweaks {
        @ConfigEntry.Gui.Tooltip
        public boolean allow_levitation_potions = true;
    }
}