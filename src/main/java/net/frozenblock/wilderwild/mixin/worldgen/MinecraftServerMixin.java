package net.frozenblock.wilderwild.mixin.worldgen;

import net.frozenblock.wilderwild.util.interfaces.NoiseGeneratorInterface;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(
            method = {"<init>"},
            at = {@At("RETURN")}
    )
    private void onInit(CallbackInfo info) {
        MinecraftServer server = MinecraftServer.class.cast(this);

        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> stemEntry : server.getWorldData().worldGenSettings().dimensions().entrySet()) {
            LevelStem stem = stemEntry.getValue();
            ChunkGenerator chunkGenerator = stem.generator();
            if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseBasedChunkGenerator) {
                NoiseGeneratorInterface.class.cast(noiseBasedChunkGenerator.generatorSettings().value()).setDimension(stem.typeHolder());
            }
        }

    }

}
