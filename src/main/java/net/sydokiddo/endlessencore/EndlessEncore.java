package net.sydokiddo.endlessencore;

import net.fabricmc.api.ModInitializer;
import net.sydokiddo.endlessencore.block.ModBlocks;
import net.sydokiddo.endlessencore.item.ModItems;
import net.sydokiddo.endlessencore.network.PlayerAttackPacket;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndlessEncore implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final String MOD_ID = "endlessencore";

	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		PlayerAttackPacket.init();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModSoundEvents.registerSounds();

		LOGGER.info("Hello Fabric world!");
	}
}
