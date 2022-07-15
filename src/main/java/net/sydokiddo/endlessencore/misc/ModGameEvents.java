package net.sydokiddo.endlessencore.misc;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.gameevent.GameEvent;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModGameEvents {

    // Game Event Registry:

    public static final GameEvent ETHEREAL_URN_BREAK = new GameEvent("ethereal_urn_break", 16);
    public static final GameEvent BABY_ETHEREAL_TATTLE = new GameEvent("baby_ethereal_tattle", 24);
    public static final GameEvent ETHEREAL_GONG = new GameEvent("ethereal_gong", 32);
    public static final GameEvent ELYTRA_CLOSE = new GameEvent("elytra_close", 16);

    public static void init() {
        Registry.register(Registry.GAME_EVENT, new ResourceLocation(EndlessEncore.MOD_ID, "ethereal_urn_break"), ETHEREAL_URN_BREAK);
        Registry.register(Registry.GAME_EVENT, new ResourceLocation(EndlessEncore.MOD_ID, "baby_ethereal_tattle"), BABY_ETHEREAL_TATTLE);
        Registry.register(Registry.GAME_EVENT, new ResourceLocation(EndlessEncore.MOD_ID, "ethereal_gong"), ETHEREAL_GONG);
        Registry.register(Registry.GAME_EVENT, new ResourceLocation(EndlessEncore.MOD_ID, "elytra_close"), ELYTRA_CLOSE);
    }

    public static void registerGameEvents() {
        System.out.println("Registering Game Events for " + EndlessEncore.MOD_ID);
    }
}
