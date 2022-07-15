package net.sydokiddo.endlessencore.misc;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;

@SuppressWarnings("ALL")

// Tag Registry:

public class EndlessEncoreTags {
    public static final TagKey<Item> GRAVITY_DISOBEYING_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("endlessencore", "gravity_disobeying_items"));
    public static final TagKey<Item> BABY_ETHEREAL_BRIBING_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("endlessencore", "baby_ethereal_bribing_items"));
    public static final TagKey<Block> BASE_STONE_END = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("endlessencore", "base_stone_end"));
    public static final TagKey<Block> RIFT_FOUNTAIN_BLOCKS = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("endlessencore", "rift_fountain_blocks"));
    public static final TagKey<GameEvent> ETHEREAL_CAN_LISTEN = TagKey.create(Registry.GAME_EVENT_REGISTRY, new ResourceLocation("endlessencore", "ethereal_can_listen"));
    public static final TagKey<GameEvent> BABY_ETHEREAL_CAN_LISTEN = TagKey.create(Registry.GAME_EVENT_REGISTRY, new ResourceLocation("endlessencore", "baby_ethereal_can_listen"));
    public static final TagKey<GameEvent> GLADIATOR_CAN_LISTEN = TagKey.create(Registry.GAME_EVENT_REGISTRY, new ResourceLocation("endlessencore", "gladiator_can_listen"));
    public static final TagKey<GameEvent> CHARMER_CAN_LISTEN = TagKey.create(Registry.GAME_EVENT_REGISTRY, new ResourceLocation("endlessencore", "charmer_can_listen"));
    public static final TagKey<GameEvent> GOLIATH_CAN_LISTEN = TagKey.create(Registry.GAME_EVENT_REGISTRY, new ResourceLocation("endlessencore", "goliath_can_listen"));
}