package net.sydokiddo.endlessencore.init;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.SweepAttackParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleInit {

    public static final DefaultParticleType OFFHAND_SWEEP_ATTACK = FabricParticleTypes.simple(true);

    public static void initServer() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("endlessencore", "offhand_sweep_attack"), OFFHAND_SWEEP_ATTACK);
    }

    public static void initClient() {
        ParticleFactoryRegistry.getInstance().register(OFFHAND_SWEEP_ATTACK, SweepAttackParticle.Factory::new);
    }

}