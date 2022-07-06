package net.sydokiddo.endlessencore.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.item.custom_items.*;

@SuppressWarnings("ALL")
public class ModItems {

    // List of Items:

    public static final Item CHITIN = registerItem("chitin",
            new Item(new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item BARRAGE_SHELL = registerItem("barrage_shell",
            new Item(new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item IOLITE_FRAGMENT = registerItem("iolite_fragment",
            new Item(new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item SHULKER_PEARL = registerItem("shulker_pearl",
            new Item(new FabricItemSettings().maxCount(16).group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item FRACTURED_SHULKER_BULLET = registerItem("fractured_shulker_bullet",
            new Item(new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item SHULKER_BULLET = registerItem("shulker_bullet",
            new Item(new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_INGOT = registerItem("peroratite_ingot",
            new Item(new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item ETHEREAL_EMBERS = registerItem("ethereal_embers",
            new Item(new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PURIFIED_CHORUS_FRUIT = registerItem("purified_chorus_fruit",
            new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().alwaysEdible().hunger(6).saturationModifier(10).statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 160, 0), 1f).build())
            .group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item WOODEN_SICKLE = registerItem("wooden_sickle",
            new SickleItem(ToolMaterials.WOOD, 1, -2f,
                    new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item STONE_SICKLE = registerItem("stone_sickle",
            new SickleItem(ToolMaterials.STONE, 1, -2f,
                    new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle",
            new SickleItem(ToolMaterials.GOLD, 1, -2f,
                    new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item IRON_SICKLE = registerItem("iron_sickle",
            new SickleItem(ToolMaterials.IRON, 1, -2f,
                    new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item DIAMOND_SICKLE = registerItem("diamond_sickle",
            new SickleItem(ToolMaterials.DIAMOND, 1, -2f,
                    new FabricItemSettings().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle",
            new SickleItem(ToolMaterials.NETHERITE, 1, -2f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_SWORD = registerItem("peroratite_sword",
            new PeroratiteSwordItem(ModToolMaterial.PERORATITE, 3, -2.4f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_SICKLE = registerItem("peroratite_sickle",
            new PeroratiteSickleItem(ModToolMaterial.PERORATITE, 1, -2f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_SHOVEL = registerItem("peroratite_shovel",
            new PeroratiteShovelItem(ModToolMaterial.PERORATITE, 1.5f, -3f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_PICKAXE = registerItem("peroratite_pickaxe",
            new PeroratitePickaxeItem(ModToolMaterial.PERORATITE, 1, -2.8f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_AXE = registerItem("peroratite_axe",
            new PeroratiteAxeItem(ModToolMaterial.PERORATITE, 5, -3f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_HOE = registerItem("peroratite_hoe",
            new PeroratiteHoeItem(ModToolMaterial.PERORATITE, -5, 0f,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_HELMET = registerItem("peroratite_helmet",
            new ArmorItem(ModArmorMaterial.PERORATITE, EquipmentSlot.HEAD,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_CHESTPLATE = registerItem("peroratite_chestplate",
            new ArmorItem(ModArmorMaterial.PERORATITE, EquipmentSlot.CHEST,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_LEGGINGS = registerItem("peroratite_leggings",
            new ArmorItem(ModArmorMaterial.PERORATITE, EquipmentSlot.LEGS,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item PERORATITE_BOOTS = registerItem("peroratite_boots",
            new ArmorItem(ModArmorMaterial.PERORATITE, EquipmentSlot.FEET,
                    new FabricItemSettings().fireproof().group(ModItemGroup.ENDLESS_ENCORE)));

    public static final Item RIFT_CHAIN = registerItem("rift_chain",
            new Item(new FabricItemSettings().maxCount(1).group(ModItemGroup.ENDLESS_ENCORE)));

    // Registry for Items:

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(EndlessEncore.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Items for " + "endlessencore");
    }
}