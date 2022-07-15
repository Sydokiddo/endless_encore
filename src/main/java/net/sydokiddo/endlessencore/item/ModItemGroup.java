package net.sydokiddo.endlessencore.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModItemGroup {
    public static final CreativeModeTab ENDLESS_ENCORE = FabricItemGroupBuilder.build(new ResourceLocation(EndlessEncore.MOD_ID, "endlessencore"),
            () -> new ItemStack(ModItems.PERORATITE_INGOT));
}