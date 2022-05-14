package net.sydokiddo.endlessencore.misc;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EndlessEncoreTags {
    public static final TagKey<Item> GRAVITY_DISOBEYING_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier("endlessencore", "gravity_disobeying_items"));
}