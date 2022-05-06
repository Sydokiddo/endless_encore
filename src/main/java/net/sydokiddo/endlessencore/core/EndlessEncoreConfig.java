package net.sydokiddo.endlessencore.core;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.util.math.MathHelper;
import net.sydokiddo.endlessencore.EndlessEncore;

@Config(name = EndlessEncore.MOD_ID)
public class EndlessEncoreConfig implements ConfigData {

    public double preservationPreservationRate = 1.0F;
    public double preservationDropChance = 0.0F;

    @Override
    public void validatePostLoad() throws ValidationException {
        preservationPreservationRate = MathHelper.clamp(preservationPreservationRate, 0.0F, 1.0F);
        preservationDropChance = MathHelper.clamp(preservationDropChance, 0.0F, 1.0F);
    }
}