package dev.microcontrollers.rendertweaks.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.microcontrollers.rendertweaks.config.RenderTweaksConfig;
import dev.microcontrollers.rendertweaks.util.ColorUtil;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {
    @Unique
    private static final RenderTweaksConfig config = RenderTweaksConfig.CONFIG.instance();

    @Inject(method = "stringVertex", at = @At("HEAD"), cancellable = true)
    private static void cancelFishingLineRendering(float x, float y, float z, VertexConsumer consumer, PoseStack.Pose pose, float stringFraction, float nextStringFraction, CallbackInfo ci) {
        if (config.disableFishingLine || config.fishingLineColor.getAlpha() == 0 || config.fishingLineAlpha == 0F) ci.cancel();
    }

    @ModifyArg(method = "stringVertex", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/VertexConsumer;setColor(I)Lcom/mojang/blaze3d/vertex/VertexConsumer;"))
    private static int changeFishingLineColor(int argb) {
        return ColorUtil.getColor(
                config.fishingLineChroma,
                config.fishingLineColor,
                config.fishingLineSpeed,
                config.fishingLineSaturation,
                config.fishingLineBrightness,
                config.fishingLineAlpha
        ).getRGB();
    }
}
