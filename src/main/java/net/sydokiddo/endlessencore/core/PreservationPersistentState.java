package net.sydokiddo.endlessencore.core;

import com.google.common.collect.Maps;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainer;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainerProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PreservationPersistentState extends PersistentState {

    private static final String PERSISTENT_ID = "soulbound_persisted_items";
    private final Map<UUID, Map<Identifier, NbtCompound>> persistedData = Maps.newHashMap();

    public static PreservationPersistentState get(MinecraftServer server) {
        return server.getOverworld().getPersistentStateManager().getOrCreate(PreservationPersistentState::fromNbt, PreservationPersistentState::new, PERSISTENT_ID);
    }

    private static PreservationPersistentState fromNbt(NbtCompound tag) {
        PreservationPersistentState value = new PreservationPersistentState();
        NbtList playerTags = tag.getList("players", NbtElement.COMPOUND_TYPE);
        for (int j = 0; j < playerTags.size(); j++) {
            NbtCompound playerTag = playerTags.getCompound(j);
            UUID uuid = playerTag.getUuid("uuid");
            Map<Identifier, NbtCompound> map = new HashMap<>();
            NbtList inventories = playerTag.getList("inventories", NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < inventories.size(); i++) {
                NbtCompound inv = inventories.getCompound(i);
                Identifier id = new Identifier(inv.getString("id"));
                if (EndlessEncore.CONTAINERS.containsId(id)) {
                    map.put(id, inv.getCompound("data"));
                } else {
                    EndlessEncore.LOGGER.error("unable to read data for unknown provider {} for player {}", id, uuid);
                }
            }
            value.persistedData.put(uuid, map);
        }
        return value;
    }

    private PreservationPersistentState() {
        super();
    }

    public void storePlayer(PlayerEntity player) {
        Map<Identifier, NbtCompound> data = new HashMap<>();
        EndlessEncore.CONTAINERS.getIds().forEach(id -> {
            PreservationContainerProvider<?> provider = Objects.requireNonNull(EndlessEncore.CONTAINERS.get(id));
            PreservationContainer container = provider.getContainer(player);
            if (container != null) {
                NbtCompound containerData = new NbtCompound();
                container.storeToNbt(containerData);
                if (!containerData.isEmpty()) {
                    data.put(id, containerData);
                }
            }
        });
        this.persistedData.put(player.getGameProfile().getId(), data);
        markDirty();
    }

    public Map<Identifier, NbtCompound> restorePlayer(PlayerEntity player) {
        Map<Identifier, NbtCompound> value = persistedData.remove(player.getGameProfile().getId());
        if (value != null) {
            markDirty();
        }
        return value;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        NbtList playerTags = new NbtList();
        persistedData.forEach((uuid, map) -> {
            NbtCompound playerTag = new NbtCompound();
            playerTag.putUuid("uuid", uuid);
            NbtList inventories = new NbtList();
            map.forEach((identifier, data) -> {
                NbtCompound inv = new NbtCompound();
                inv.putString("id", identifier.toString());
                inv.put("data", data);
                inventories.add(inv);
            });
            playerTag.put("inventories", inventories);
            playerTags.add(playerTag);
        });
        tag.put("players", playerTags);
        return tag;
    }
}