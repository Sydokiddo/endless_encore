package net.sydokiddo.endlessencore.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.sydokiddo.endlessencore.block.ModBlocks;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class EndlessEncoreClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Renders Blocks in List as Transparent (Without Translucency)

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderType.cutout(),

                  ModBlocks.ETHEREAL_URN

        );

        // Renders Blocks in List as Transparent (With Translucency)

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderType.translucent(),

                ModBlocks.ETHEREAL_CLOUD

        );
    }
}