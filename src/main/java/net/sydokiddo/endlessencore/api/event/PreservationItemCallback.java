package net.sydokiddo.endlessencore.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.item.ItemStack;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainer;

public interface PreservationItemCallback {

    Event<PreservationItemCallback> EVENT = EventFactory.createArrayBacked(PreservationItemCallback.class, callbacks -> ctx -> {
        for (PreservationItemCallback callback : callbacks) {
            TriState value = callback.apply(ctx);
            if (value != TriState.DEFAULT) {
                return value;
            }
        }
        return TriState.DEFAULT;
    });

    TriState apply(Context ctx);

    class Context {

        final PreservationContainer container;
        ItemStack stack;
        double levelPreservationChance;

        public Context(PreservationContainer container, ItemStack stack, double levelPreservationChance) {
            this.container = container;
            this.stack = stack;
            this.levelPreservationChance = levelPreservationChance;
        }

        public PreservationContainer getContainer() {
            return container;
        }

        public ItemStack getStack() {
            return stack;
        }

        public void setStack(ItemStack stack) {
            this.stack = stack;
        }

        public double getLevelPreservationChance() {
            return levelPreservationChance;
        }

        public void setLevelPreservationChance(double levelPreservationChance) {
            this.levelPreservationChance = levelPreservationChance;
        }
    }
}