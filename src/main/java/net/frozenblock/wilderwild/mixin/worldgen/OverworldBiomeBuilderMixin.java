package net.frozenblock.wilderwild.mixin.worldgen;

import com.mojang.datafixers.util.Pair;
import net.frozenblock.wilderwild.init.WWBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {

    @Shadow @Final private Climate.Parameter[] humidities;
    @Shadow @Final private Climate.Parameter[] temperatures;

    @Inject(at = @At("TAIL"), method = "addLowSlice")
    private void WW$addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness, CallbackInfo ci) {
        this.addSurfaceBiome(consumer, Climate.Parameter.span(this.temperatures[1], this.temperatures[3]), Climate.Parameter.span(this.humidities[2], this.humidities[4]), Climate.Parameter.span(-0.2F, 0.5F), Climate.Parameter.span(0.5F, 1.0F), weirdness, 0.0F, WWBiomes.CYPRESS_WETLANDS.getKey());
    }

    @Inject(at = @At("TAIL"), method = "addMidSlice")
    private void WW$addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness, CallbackInfo ci) {
        this.addSurfaceBiome(consumer, Climate.Parameter.span(this.temperatures[1], this.temperatures[3]), Climate.Parameter.span(this.humidities[2], this.humidities[4]), Climate.Parameter.span(-0.2F, 0.5F), Climate.Parameter.span(0.5F, 1.0F), weirdness, 0.0F, WWBiomes.CYPRESS_WETLANDS.getKey());
    }

    @Inject(at = @At("TAIL"), method = "addValleys")
    private void WW$addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness, CallbackInfo ci) {
        this.addSurfaceBiome(consumer, Climate.Parameter.span(this.temperatures[1], this.temperatures[3]), Climate.Parameter.span(this.humidities[2], this.humidities[4]), Climate.Parameter.span(-0.2F, 0.5F), Climate.Parameter.span(0.5F, 1.0F), weirdness, 0.0F, WWBiomes.CYPRESS_WETLANDS.getKey());
    }

    @Shadow
    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, final float offset, ResourceKey<Biome> biome) {
        parameters.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.0F, 1.0F), weirdness, offset), biome));
    }

}
