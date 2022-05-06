package net.sydokiddo.endlessencore.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Predicate;

public interface PreservationFakePlayerCallback extends Predicate<ServerPlayerEntity> {

    Event<PreservationFakePlayerCallback> EVENT = EventFactory.createArrayBacked(PreservationFakePlayerCallback.class, listeners -> player -> {
        for (PreservationFakePlayerCallback listener : listeners) {
            if (!listener.test(player)) {
                return false;
            }
        }
        return true;
    });

    @Override
    boolean test(ServerPlayerEntity player);
}