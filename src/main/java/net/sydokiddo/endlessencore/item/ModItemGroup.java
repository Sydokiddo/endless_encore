package net.sydokiddo.endlessencore.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModItemGroup {
    public static final ItemGroup ENDLESS_ENCORE = FabricItemGroupBuilder.build(new Identifier(EndlessEncore.MOD_ID, "endlessencore"),
            () -> new ItemStack(ModItems.IOLITE_FRAGMENT));
}
