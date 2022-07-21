package net.sydokiddo.endlessencore.sound;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.sydokiddo.endlessencore.EndlessEncore;

@SuppressWarnings("ALL")
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

    public static final SoundEvent BLOCK_ETHEREAL_URN_BREAK = registerSoundEvent("block.ethereal_urn.break");
    public static final SoundEvent BLOCK_ETHEREAL_URN_STEP = registerSoundEvent("block.ethereal_urn.step");
    public static final SoundEvent BLOCK_ETHEREAL_URN_PLACE = registerSoundEvent("block.ethereal_urn.place");
    public static final SoundEvent BLOCK_ETHEREAL_URN_HIT = registerSoundEvent("block.ethereal_urn.hit");
    public static final SoundEvent BLOCK_ETHEREAL_URN_FALL = registerSoundEvent("block.ethereal_urn.fall");
    public static final SoundEvent BLOCK_ETHEREAL_URN_INSERT = registerSoundEvent("block.ethereal_urn.insert");

    public static final SoundEvent BLOCK_ETHEREAL_CLOUD_BREAK = registerSoundEvent("block.ethereal_cloud.break");
    public static final SoundEvent BLOCK_ETHEREAL_CLOUD_STEP = registerSoundEvent("block.ethereal_cloud.step");
    public static final SoundEvent BLOCK_ETHEREAL_CLOUD_PLACE = registerSoundEvent("block.ethereal_cloud.place");
    public static final SoundEvent BLOCK_ETHEREAL_CLOUD_HIT = registerSoundEvent("block.ethereal_cloud.hit");
    public static final SoundEvent BLOCK_ETHEREAL_CLOUD_FALL = registerSoundEvent("block.ethereal_cloud.fall");

    // - Misc Sounds:

    public static final SoundEvent PLAYER_ELYTRA_CLOSE = registerSoundEvent("item.elytra.close");
    public static final SoundEvent PLAYER_ELYTRA_BREAK = registerSoundEvent("item.elytra.break");
    public static final SoundEvent EVENT_SIEGE_GONG = registerSoundEvent("event.siege.gong");
    public static final SoundEvent ITEM_ARMOR_EQUIP_PERORATITE = registerSoundEvent("item.armor.equip_peroratite");

    // - Ambient Sounds:

    public static final SoundEvent AMBIENT_GENERAL_END_MOOD = registerSoundEvent("ambient.general_end.mood");
    public static final SoundEvent AMBIENT_GENERAL_END_LOOP = registerSoundEvent("ambient.general_end.loop");

    // - Entity Sounds:

    public static final SoundEvent ENDERMITE_AMBIENT = registerSoundEvent("entity.endermite.ambient");
    public static final SoundEvent ENDERMITE_HURT = registerSoundEvent("entity.endermite.hurt");
    public static final SoundEvent ENDERMITE_DEATH = registerSoundEvent("entity.endermite.death");
    public static final SoundEvent ENDERMITE_TELEPORT = registerSoundEvent("entity.endermite.teleport");

    public static final SoundEvent ETHEREAL_AMBIENT = registerSoundEvent("entity.ethereal.ambient");
    public static final SoundEvent ETHEREAL_NOTICE = registerSoundEvent("entity.ethereal.notice");
    public static final SoundEvent ETHEREAL_HURT = registerSoundEvent("entity.ethereal.hurt");
    public static final SoundEvent ETHEREAL_DEATH = registerSoundEvent("entity.ethereal.death");
    public static final SoundEvent ETHEREAL_FRENZY = registerSoundEvent("entity.ethereal.frenzy");
    public static final SoundEvent ETHEREAL_FRENZY_END = registerSoundEvent("entity.ethereal.frenzy_end");

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
    public static final SoundEvent CHARMER_DISORIENTATION_SONG = registerSoundEvent("entity.charmer.disorientation_song");
    public static final SoundEvent CHARMER_DISORIENT_PLAYER = registerSoundEvent("entity.charmer.debuff");

    public static final SoundEvent BARRAGE_AMBIENT = registerSoundEvent("entity.barrage.ambient");
    public static final SoundEvent BARRAGE_OPEN = registerSoundEvent("entity.barrage.open");
    public static final SoundEvent BARRAGE_CLOSE = registerSoundEvent("entity.barrage.close");
    public static final SoundEvent BARRAGE_HURT = registerSoundEvent("entity.barrage.hurt");
    public static final SoundEvent BARRAGE_HURT_CLOSED = registerSoundEvent("entity.barrage.hurt_closed");
    public static final SoundEvent BARRAGE_DEATH = registerSoundEvent("entity.barrage.death");
    public static final SoundEvent BARRAGE_LOCK = registerSoundEvent("entity.barrage.lock");
    public static final SoundEvent BARRAGE_SHOOT = registerSoundEvent("entity.barrage.shoot");

    // Music:

    public static final SoundEvent MUSIC_ETHEREAL_CITY = registerSoundEvent("music.end.ethereal_city");

    // Sound Groups:

    public static final SoundType NETTLED_END_STONE = new SoundType(1f, 1f,
            ModSoundEvents.BLOCK_NETTLED_END_STONE_BREAK, ModSoundEvents.BLOCK_NETTLED_END_STONE_STEP, ModSoundEvents.BLOCK_NETTLED_END_STONE_PLACE,
            ModSoundEvents.BLOCK_NETTLED_END_STONE_HIT, ModSoundEvents.BLOCK_NETTLED_END_STONE_FALL);

    public static final SoundType IOLITE = new SoundType(1f, 1f,
            ModSoundEvents.BLOCK_IOLITE_BREAK, ModSoundEvents.BLOCK_IOLITE_STEP, ModSoundEvents.BLOCK_IOLITE_PLACE,
            ModSoundEvents.BLOCK_IOLITE_HIT, ModSoundEvents.BLOCK_IOLITE_FALL);

    public static final SoundType END_STALK = new SoundType(1f, 1f,
            ModSoundEvents.BLOCK_END_STALK_BREAK, ModSoundEvents.BLOCK_END_STALK_STEP, ModSoundEvents.BLOCK_END_STALK_PLACE,
            ModSoundEvents.BLOCK_END_STALK_HIT, ModSoundEvents.BLOCK_END_STALK_FALL);

    public static final SoundType END_ORE = new SoundType(1f, 1f,
            ModSoundEvents.BLOCK_END_ORE_BREAK, ModSoundEvents.BLOCK_END_ORE_STEP, ModSoundEvents.BLOCK_END_ORE_PLACE,
            ModSoundEvents.BLOCK_END_ORE_HIT, ModSoundEvents.BLOCK_END_ORE_FALL);

    public static final SoundType ETHEREAL_URN = new SoundType(1f, 1f,
            ModSoundEvents.BLOCK_ETHEREAL_URN_BREAK, ModSoundEvents.BLOCK_ETHEREAL_URN_STEP, ModSoundEvents.BLOCK_ETHEREAL_URN_PLACE,
            ModSoundEvents.BLOCK_ETHEREAL_URN_HIT, ModSoundEvents.BLOCK_ETHEREAL_URN_FALL);

    public static final SoundType ETHEREAL_CLOUD = new SoundType(1f, 1f,
            ModSoundEvents.BLOCK_ETHEREAL_CLOUD_BREAK, ModSoundEvents.BLOCK_ETHEREAL_CLOUD_STEP, ModSoundEvents.BLOCK_ETHEREAL_CLOUD_PLACE,
            ModSoundEvents.BLOCK_ETHEREAL_CLOUD_HIT, ModSoundEvents.BLOCK_ETHEREAL_CLOUD_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(EndlessEncore.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + EndlessEncore.MOD_ID);
    }
}
