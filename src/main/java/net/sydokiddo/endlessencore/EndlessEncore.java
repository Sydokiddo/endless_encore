package net.sydokiddo.endlessencore;

import net.fabricmc.api.ModInitializer;
import net.sydokiddo.endlessencore.block.ModBlocks;
import net.sydokiddo.endlessencore.effect.ModEffects;
import net.sydokiddo.endlessencore.entity.block_entities.ModBlockEntities;
import net.sydokiddo.endlessencore.item.ModItems;
import net.sydokiddo.endlessencore.misc.ModGameEvents;
import net.sydokiddo.endlessencore.util.PlayerAttackPacket;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import net.sydokiddo.endlessencore.world.feature.ModConfiguredFeatures;
import net.sydokiddo.endlessencore.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndlessEncore implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String MOD_ID = "endlessencore";
	public static boolean elytraThicknessFix = true;

	@Override
	public void onInitialize() {

		// Registry

		PlayerAttackPacket.init();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModSoundEvents.registerSounds();
		ModBlockEntities.registerBlockEntities();
		ModGameEvents.init();
		ModGameEvents.registerGameEvents();
		ModEffects.registerStagnationEffect();
		ModEffects.registerConfusionEffect();
		ModConfiguredFeatures.registerConfiguredFeatures();
		ModWorldGen.generateModWorldGen();

		LOGGER.info("Thank you for downloading Endless Encore! :)");
	}
}
