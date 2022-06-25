package net.sydokiddo.endlessencore.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.sydokiddo.endlessencore.block.ModBlocks;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class EndlessEncoreClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Renders Blocks in List as Transparent (Without Translucency)

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout()

//                ModBlocks.NETTLE_LEAVES,
//                ModBlocks.FLOWERING_NETTLE_LEAVES,
//                ModBlocks.NETTLE_CARPET,
//                ModBlocks.NETTLE_ROOTS,
//                ModBlocks.FLOWERING_NETTLE_ROOTS,
//                ModBlocks.NETTLE_SHRUB,
//                ModBlocks.FLOWERING_NETTLE_SHRUB,
//                ModBlocks.NETTLE_VINES,
//                ModBlocks.NETTLE_VINES_PLANT,
//                ModBlocks.BLIGHTED_CARPET

        );

        // Renders Blocks in List as Transparent (With Translucency)

        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getTranslucent(),

                ModBlocks.ETHEREAL_CLOUD

        );
    }
}