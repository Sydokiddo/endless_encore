package net.sydokiddo.endlessencore;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.sydokiddo.endlessencore.block.ModBlocks;
import net.sydokiddo.endlessencore.effect.ModEffects;
import net.sydokiddo.endlessencore.item.ModItems;
import net.sydokiddo.endlessencore.network.PlayerAttackPacket;
import net.sydokiddo.endlessencore.sound.ModSoundEvents;
import net.sydokiddo.endlessencore.world.feature.ModConfiguredFeatures;
import net.sydokiddo.endlessencore.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SuppressWarnings({"UnstableApiUsage", "rawtypes"})
public class EndlessEncore implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String MOD_ID = "endlessencore";
	public static boolean elytraThicknessFix = true;
	public static Map<EntityType<?>, Item> bucketMap = new HashMap();
	private static final Random RANDOM = new Random();

	@Override
	public void onInitialize() {

		// Registry

		PlayerAttackPacket.init();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModSoundEvents.registerSounds();
		ModEffects.registerEffects();
		ModConfiguredFeatures.registerConfiguredFeatures();
		ModWorldGen.generateModWorldGen();

		LOGGER.info("Thank you for downloading Endless Encore! :)");
	}

	public static void put(Item item, EntityType<?> type){
		bucketMap.put(type, item);
	}
}
