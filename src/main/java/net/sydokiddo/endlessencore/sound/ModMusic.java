package net.sydokiddo.endlessencore.sound;

import net.minecraft.sounds.Music;

// Music Registry:

@SuppressWarnings("ALL")
public class ModMusic {
    public static final Music ETHEREAL_CITY;

    static {
        ETHEREAL_CITY = new Music(ModSoundEvents.MUSIC_ETHEREAL_CITY, 6000, 24000, true);
    }
}