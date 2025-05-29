package dev.microcontrollers.rendertweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Cancellable;
import com.llamalad7.mixinextras.sugar.Local;
import dev.microcontrollers.rendertweaks.config.RenderTweaksConfig;
import dev.microcontrollers.rendertweaks.util.ColorUtil;
import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(WorldBorderRenderer.class)
public class LevelRendererMixin {
    @Unique
    private static final RenderTweaksConfig config = RenderTweaksConfig.CONFIG.instance();

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/border/BorderStatus;getColor()I"))
    private int changeWorldBorderColor(int original, @Local(ordinal = 0, argsOnly = true) WorldBorder worldBorder, @Cancellable CallbackInfo ci) {
        return switch (worldBorder.getStatus().name()) {
            case "STATIONARY" -> {
                if (config.disableWorldBorderStationary || config.worldBorderStationaryColor.getAlpha() == 0 || config.worldBorderStationaryAlpha == 0F) ci.cancel();
                Color color = ColorUtil.getColor(
                        config.worldBorderStationaryChroma,
                        config.worldBorderStationaryColor,
                        config.worldBorderStationarySpeed,
                        config.worldBorderStationarySaturation,
                        config.worldBorderStationaryBrightness,
                        config.worldBorderStationaryAlpha
                );
                yield color.getRGB();
            }
            case "SHRINKING" -> {
                if (config.disableWorldBorderShrinking || config.worldBorderShrinkingColor.getAlpha() == 0 || config.worldBorderShrinkingAlpha == 0F) ci.cancel();
                Color color = ColorUtil.getColor(
                        config.worldBorderShrinkingChroma,
                        config.worldBorderShrinkingColor,
                        config.worldBorderShrinkingSpeed,
                        config.worldBorderShrinkingSaturation,
                        config.worldBorderShrinkingBrightness,
                        config.worldBorderShrinkingAlpha
                );
                yield color.getRGB();
            }
            case "GROWING" -> {
                if (config.disableWorldBorderGrowing || config.worldBorderGrowingColor.getAlpha() == 0 || config.worldBorderGrowingAlpha == 0F) ci.cancel();
                Color color = ColorUtil.getColor(
                        config.worldBorderGrowingChroma,
                        config.worldBorderGrowingColor,
                        config.worldBorderGrowingSpeed,
                        config.worldBorderGrowingSaturation,
                        config.worldBorderGrowingBrightness,
                        config.worldBorderGrowingAlpha
                );
                yield color.getRGB();
            }
            default -> original;
        };
    }
}
