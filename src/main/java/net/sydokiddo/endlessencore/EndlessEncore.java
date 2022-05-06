package net.sydokiddo.endlessencore;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Lifecycle;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry;
import net.fabricmc.fabric.mixin.registry.sync.AccessorRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import net.sydokiddo.endlessencore.api.PreservationApi;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainer;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainerProvider;
import net.sydokiddo.endlessencore.block.ModBlocks;
import net.sydokiddo.endlessencore.core.EndlessEncoreConfig;
import net.sydokiddo.endlessencore.core.PreservationHooks;
import net.sydokiddo.endlessencore.core.PreservationPersistentState;
import net.sydokiddo.endlessencore.core.inventory.PlayerInventoryContainer;
import net.sydokiddo.endlessencore.core.inventory.PlayerInventoryContainerProvider;
import net.sydokiddo.endlessencore.enchantment.PreservationEnchantment;
import net.sydokiddo.endlessencore.item.ModItems;
import net.sydokiddo.endlessencore.network.PlayerAttackPacket;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.function.Supplier;

public class EndlessEncore implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String MOD_ID = "endlessencore";
	public static final Supplier<EndlessEncoreConfig> CONFIG = Util.make(() -> {
		AutoConfig.register(EndlessEncoreConfig.class, JanksonConfigSerializer::new);
		return AutoConfig.getConfigHolder(EndlessEncoreConfig.class);
	});

	public static final RegistryKey<? extends Registry<PreservationContainerProvider<?>>> CONTAINERS_KEY = RegistryKey.ofRegistry(new Identifier(MOD_ID, "containers"));
	public static final MutableRegistry<PreservationContainerProvider<?>> CONTAINERS = Util.make(new SimpleRegistry<>(CONTAINERS_KEY, Lifecycle.stable(), null), it -> ((FabricRegistry) it).build(ImmutableSet.of(RegistryAttribute.PERSISTED)));
	public static final PreservationEnchantment ENCHANT_PRESERVATION = new PreservationEnchantment();
	public static final PreservationContainerProvider<PlayerInventoryContainer> PLAYER_CONTAINER_PROVIDER = new PlayerInventoryContainerProvider();

	@Override
	public void onInitialize() {

		// Registry

		PlayerAttackPacket.init();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModSoundEvents.registerSounds();

		// Preservation Enchantment

		AccessorRegistry.getROOT().add(((AccessorRegistry) CONTAINERS).getRegistryKey(), CONTAINERS, Lifecycle.stable());
		Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "preservation"), ENCHANT_PRESERVATION);
		Registry.register(CONTAINERS, new Identifier(MOD_ID, "player_inventory"), PLAYER_CONTAINER_PROVIDER);
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
			if (!alive) {
				PreservationPersistentState persistentState = PreservationPersistentState.get(newPlayer.getServer());
				Map<Identifier, NbtCompound> saveData = persistentState.restorePlayer(oldPlayer);
				if (saveData != null) {
					saveData.forEach((id, data) -> PreservationApi.CONTAINERS.getOrEmpty(id).ifPresentOrElse(provider -> {
						@Nullable PreservationContainer container = provider.getContainer(newPlayer);
						if (container != null) {
							container.restoreFromNbt(data, PreservationHooks.createItemProcessor(container));
						} else {
							EndlessEncore.LOGGER.warn("tried to deserialize null container for provider {}", id);
						}
					}, () -> EndlessEncore.LOGGER.error("tried to deserialize unknown provider {} for player {}", id, newPlayer.getEntityName())));
				}
			}
		});

		LOGGER.info("Hello Fabric world!");
	}
}
