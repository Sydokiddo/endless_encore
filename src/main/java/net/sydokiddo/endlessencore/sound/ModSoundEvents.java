package net.sydokiddo.endlessencore.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;

public class ModSoundEvents {

    // Sound Registry:

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

    public static final SoundEvent AMBIENT_GENERAL_END_MOOD = registerSoundEvent("ambient.general_end.mood");
    public static final SoundEvent AMBIENT_GENERAL_END_LOOP = registerSoundEvent("ambient.general_end.loop");

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
