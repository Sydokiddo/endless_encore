package net.sydokiddo.endlessencore.api;

import net.minecraft.util.registry.Registry;
import net.sydokiddo.endlessencore.EndlessEncore;
import net.sydokiddo.endlessencore.api.inventory.PreservationContainerProvider;

public interface PreservationApi {

    Registry<PreservationContainerProvider<?>> CONTAINERS = EndlessEncore.CONTAINERS;
}