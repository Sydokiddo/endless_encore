package net.sydokiddo.endlessencore.misc;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.event.GameEvent;

public class EndlessEncoreTags {
    public static final TagKey<Item> GRAVITY_DISOBEYING_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier("endlessencore", "gravity_disobeying_items"));
    public static final TagKey<Item> BABY_ETHEREAL_BRIBING_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier("endlessencore", "baby_ethereal_bribing_items"));
    public static final TagKey<Block> BASE_STONE_END = TagKey.of(Registry.BLOCK_KEY, new Identifier("endlessencore", "base_stone_end"));
    public static final TagKey<Block> RIFT_FOUNTAIN_BLOCKS = TagKey.of(Registry.BLOCK_KEY, new Identifier("endlessencore", "rift_fountain_blocks"));
    public static final TagKey<GameEvent> ETHEREAL_CAN_LISTEN = TagKey.of(Registry.GAME_EVENT_KEY, new Identifier("endlessencore", "ethereal_can_listen"));
    public static final TagKey<GameEvent> BABY_ETHEREAL_CAN_LISTEN = TagKey.of(Registry.GAME_EVENT_KEY, new Identifier("endlessencore", "baby_ethereal_can_listen"));
    public static final TagKey<GameEvent> GLADIATOR_CAN_LISTEN = TagKey.of(Registry.GAME_EVENT_KEY, new Identifier("endlessencore", "gladiator_can_listen"));
    public static final TagKey<GameEvent> CHARMER_CAN_LISTEN = TagKey.of(Registry.GAME_EVENT_KEY, new Identifier("endlessencore", "charmer_can_listen"));
    public static final TagKey<GameEvent> GOLIATH_CAN_LISTEN = TagKey.of(Registry.GAME_EVENT_KEY, new Identifier("endlessencore", "goliath_can_listen"));
}