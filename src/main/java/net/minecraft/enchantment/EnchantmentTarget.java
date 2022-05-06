package net.minecraft.enchantment;

import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.item.Vanishable;
import net.minecraft.item.Wearable;
import net.sydokiddo.endlessencore.item.custom_items.SickleItem;

public enum EnchantmentTarget {
    ARMOR {
        public boolean isAcceptableItem(Item item) {
            return item instanceof ArmorItem;
        }
    },
    ARMOR_FEET {
        public boolean isAcceptableItem(Item item) {
            return item instanceof ArmorItem && ((ArmorItem)item).getSlotType() == EquipmentSlot.FEET;
        }
    },
    ARMOR_LEGS {
        public boolean isAcceptableItem(Item item) {
            return item instanceof ArmorItem && ((ArmorItem)item).getSlotType() == EquipmentSlot.LEGS;
        }
    },
    ARMOR_CHEST {
        public boolean isAcceptableItem(Item item) {
            return item instanceof ArmorItem && ((ArmorItem)item).getSlotType() == EquipmentSlot.CHEST;
        }
    },
    ARMOR_HEAD {
        public boolean isAcceptableItem(Item item) {
            return item instanceof ArmorItem && ((ArmorItem)item).getSlotType() == EquipmentSlot.HEAD;
        }
    },
    WEAPON {
        public boolean isAcceptableItem(Item item) {
            return item instanceof SwordItem || item instanceof SickleItem;
        }
    },
    DIGGER {
        public boolean isAcceptableItem(Item item) {
            return item instanceof MiningToolItem;
        }
    },
    FISHING_ROD {
        public boolean isAcceptableItem(Item item) {
            return item instanceof FishingRodItem;
        }
    },
    TRIDENT {
        public boolean isAcceptableItem(Item item) {
            return item instanceof TridentItem;
        }
    },
    BREAKABLE {
        public boolean isAcceptableItem(Item item) {
            return item.isDamageable();
        }
    },
    BOW {
        public boolean isAcceptableItem(Item item) {
            return item instanceof BowItem;
        }
    },
    WEARABLE {
        public boolean isAcceptableItem(Item item) {
            return item instanceof Wearable || Block.getBlockFromItem(item) instanceof Wearable;
        }
    },
    CROSSBOW {
        public boolean isAcceptableItem(Item item) {
            return item instanceof CrossbowItem;
        }
    },
    VANISHABLE {
        public boolean isAcceptableItem(Item item) {
            return item instanceof Vanishable || Block.getBlockFromItem(item) instanceof Vanishable || BREAKABLE.isAcceptableItem(item);
        }
    };

    EnchantmentTarget() {
    }

    public abstract boolean isAcceptableItem(Item item);
}
