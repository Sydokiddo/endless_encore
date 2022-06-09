package net.sydokiddo.endlessencore.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModSoundEvents {

    // Sound Registry:

    // - Block Sounds:

    public static final SoundEvent BLOCK_NETTLED_END_STONE_BREAK = registerSoundEvent("block.nettled_end_stone.break");
    public static final SoundEvent BLOCK_NETTLED_END_STONE_STEP = registerSoundEvent("block.nettled_end_stone.step");
    public static final SoundEvent BLOCK_NETTLED_END_STONE_PLACE = registerSoundEvent("block.nettled_end_stone.place");
    public static final SoundEvent BLOCK_NETTLED_END_STONE_HIT = registerSoundEvent("block.nettled_end_stone.hit");
    public static final SoundEvent BLOCK_NETTLED_END_STONE_FALL = registerSoundEvent("block.nettled_end_stone.fall");

    public static final SoundEvent BLOCK_IOLITE_BREAK = registerSoundEvent("block.iolite.break");
    public static final SoundEvent BLOCK_IOLITE_STEP = registerSoundEvent("block.iolite.step");
    public static final SoundEvent BLOCK_IOLITE_PLACE = registerSoundEvent("block.iolite.place");
    public static final SoundEvent BLOCK_IOLITE_HIT = registerSoundEvent("block.iolite.hit");
    public static final SoundEvent BLOCK_IOLITE_FALL = registerSoundEvent("block.iolite.fall");

    public static final SoundEvent BLOCK_END_STALK_BREAK = registerSoundEvent("block.end_stalk.break");
    public static final SoundEvent BLOCK_END_STALK_STEP = registerSoundEvent("block.end_stalk.step");
    public static final SoundEvent BLOCK_END_STALK_PLACE = registerSoundEvent("block.end_stalk.place");
    public static final SoundEvent BLOCK_END_STALK_HIT = registerSoundEvent("block.end_stalk.hit");
    public static final SoundEvent BLOCK_END_STALK_FALL = registerSoundEvent("block.end_stalk.fall");

    public static final SoundEvent BLOCK_END_ORE_BREAK = registerSoundEvent("block.end_ore.break");
    public static final SoundEvent BLOCK_END_ORE_STEP = registerSoundEvent("block.end_ore.step");
    public static final SoundEvent BLOCK_END_ORE_PLACE = registerSoundEvent("block.end_ore.place");
    public static final SoundEvent BLOCK_END_ORE_HIT = registerSoundEvent("block.end_ore.hit");
    public static final SoundEvent BLOCK_END_ORE_FALL = registerSoundEvent("block.end_ore.fall");

    // - Misc Sounds:

    public static final SoundEvent PLAYER_ELYTRA_CLOSE = registerSoundEvent("item.elytra.close");
    public static final SoundEvent EVENT_SIEGE_GONG = registerSoundEvent("event.siege.gong");
    public static final SoundEvent ITEM_ARMOR_EQUIP_PERORATITE = registerSoundEvent("item.armor.equip_peroratite");

    // - Ambient Sounds:

    public static final SoundEvent AMBIENT_GENERAL_END_MOOD = registerSoundEvent("ambient.general_end.mood");
    public static final SoundEvent AMBIENT_GENERAL_END_LOOP = registerSoundEvent("ambient.general_end.loop");

    // - Entity Sounds:

    public static final SoundEvent ETHEREAL_AMBIENT = registerSoundEvent("entity.ethereal.ambient");
    public static final SoundEvent ETHEREAL_NOTICE = registerSoundEvent("entity.ethereal.notice");
    public static final SoundEvent ETHEREAL_HURT = registerSoundEvent("entity.ethereal.hurt");
    public static final SoundEvent ETHEREAL_DEATH = registerSoundEvent("entity.ethereal.death");
    public static final SoundEvent ETHEREAL_FRENZY = registerSoundEvent("entity.ethereal.frenzy");
    public static final SoundEvent ETHEREAL_FRENZY_END = registerSoundEvent("entity.ethereal.frenzy_end");

//    public static final SoundEvent GLADIATOR_AMBIENT = registerSoundEvent("entity.gladiator.ambient");
//    public static final SoundEvent GLADIATOR_NOTICE = registerSoundEvent("entity.gladiator.notice");
//    public static final SoundEvent GLADIATOR_HURT = registerSoundEvent("entity.gladiator.hurt");
//    public static final SoundEvent GLADIATOR_DEATH = registerSoundEvent("entity.gladiator.death");
//    public static final SoundEvent GLADIATOR_FRENZY = registerSoundEvent("entity.gladiator.frenzy");
//    public static final SoundEvent GLADIATOR_FRENZY_END = registerSoundEvent("entity.gladiator.frenzy_end");

    public static final SoundEvent CHARMER_AMBIENT = registerSoundEvent("entity.charmer.ambient");
    public static final SoundEvent CHARMER_HURT = registerSoundEvent("entity.charmer.hurt");
    public static final SoundEvent CHARMER_DEATH = registerSoundEvent("entity.charmer.death");
    public static final SoundEvent CHARMER_FRENZY = registerSoundEvent("entity.charmer.frenzy");
    public static final SoundEvent CHARMER_FRENZY_END = registerSoundEvent("entity.charmer.frenzy_end");
    public static final SoundEvent CHARMER_REGENERATION_SONG = registerSoundEvent("entity.charmer.regeneration_song");
    public static final SoundEvent CHARMER_STRENGTH_SONG = registerSoundEvent("entity.charmer.strength_song");
    public static final SoundEvent CHARMER_RESISTANCE_SONG = registerSoundEvent("entity.charmer.resistance_song");
    public static final SoundEvent CHARMER_SPEED_SONG = registerSoundEvent("entity.charmer.speed_song");
    public static final SoundEvent CHARMER_VICTORY_SONG = registerSoundEvent("entity.charmer.victory_song");
    public static final SoundEvent CHARMER_POWER_UP = registerSoundEvent("entity.charmer.power_up");

    public static final SoundEvent BARRAGE_AMBIENT = registerSoundEvent("entity.barrage.ambient");
    public static final SoundEvent BARRAGE_OPEN = registerSoundEvent("entity.barrage.open");
    public static final SoundEvent BARRAGE_CLOSE = registerSoundEvent("entity.barrage.close");
    public static final SoundEvent BARRAGE_HURT = registerSoundEvent("entity.barrage.hurt");
    public static final SoundEvent BARRAGE_HURT_CLOSED = registerSoundEvent("entity.barrage.hurt_closed");
    public static final SoundEvent BARRAGE_DEATH = registerSoundEvent("entity.barrage.death");
    public static final SoundEvent BARRAGE_LOCK = registerSoundEvent("entity.barrage.lock");
    public static final SoundEvent BARRAGE_SHOOT = registerSoundEvent("entity.barrage.shoot");

    // Sound Groups:

    public static final BlockSoundGroup NETTLED_END_STONE = new BlockSoundGroup(1f, 1f,
            ModSoundEvents.BLOCK_NETTLED_END_STONE_BREAK, ModSoundEvents.BLOCK_NETTLED_END_STONE_STEP, ModSoundEvents.BLOCK_NETTLED_END_STONE_PLACE,
            ModSoundEvents.BLOCK_NETTLED_END_STONE_HIT, ModSoundEvents.BLOCK_NETTLED_END_STONE_FALL);

    public static final BlockSoundGroup IOLITE = new BlockSoundGroup(1f, 1f,
            ModSoundEvents.BLOCK_IOLITE_BREAK, ModSoundEvents.BLOCK_IOLITE_STEP, ModSoundEvents.BLOCK_IOLITE_PLACE,
            ModSoundEvents.BLOCK_IOLITE_HIT, ModSoundEvents.BLOCK_IOLITE_FALL);

    public static final BlockSoundGroup END_STALK = new BlockSoundGroup(1f, 1f,
            ModSoundEvents.BLOCK_END_STALK_BREAK, ModSoundEvents.BLOCK_END_STALK_STEP, ModSoundEvents.BLOCK_END_STALK_PLACE,
            ModSoundEvents.BLOCK_END_STALK_HIT, ModSoundEvents.BLOCK_END_STALK_FALL);

    public static final BlockSoundGroup END_ORE = new BlockSoundGroup(1f, 1f,
            ModSoundEvents.BLOCK_END_ORE_BREAK, ModSoundEvents.BLOCK_END_ORE_STEP, ModSoundEvents.BLOCK_END_ORE_PLACE,
            ModSoundEvents.BLOCK_END_ORE_HIT, ModSoundEvents.BLOCK_END_ORE_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(EndlessEncore.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + EndlessEncore.MOD_ID);
    }
}
