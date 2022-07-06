package net.sydokiddo.endlessencore.misc;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.event.GameEvent;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModGameEvents {

    public static final GameEvent ETHEREAL_URN_BREAK = new GameEvent("ethereal_urn_break", 16);
    public static final GameEvent BABY_ETHEREAL_TATTLE = new GameEvent("baby_ethereal_tattle", 24);
    public static final GameEvent ETHEREAL_GONG = new GameEvent("ethereal_gong", 32);

    public static void init() {
        Registry.register(Registry.GAME_EVENT, new Identifier(EndlessEncore.MOD_ID, "ethereal_urn_break"), ETHEREAL_URN_BREAK);
        Registry.register(Registry.GAME_EVENT, new Identifier(EndlessEncore.MOD_ID, "baby_ethereal_tattle"), BABY_ETHEREAL_TATTLE);
        Registry.register(Registry.GAME_EVENT, new Identifier(EndlessEncore.MOD_ID, "ethereal_gong"), ETHEREAL_GONG);
    }

    public static void registerGameEvents() {
        System.out.println("Registering Game Events for " + EndlessEncore.MOD_ID);
    }
}
