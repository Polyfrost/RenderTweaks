package dev.microcontrollers.rendertweaks.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.microcontrollers.rendertweaks.config.RenderTweaksConfig;
import dev.microcontrollers.rendertweaks.util.ColorUtil;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.awt.Color;

@Mixin(LightningBoltRenderer.class)
public class LightningBoltRendererMixin {
    @Unique
    private static final RenderTweaksConfig config = RenderTweaksConfig.CONFIG.instance();

    @Inject(method = "quad", at = @At("HEAD"), cancellable = true)
    private static void cancelLightningRendering(Matrix4f pose, VertexConsumer buffer, float x1, float z1, int sectionY, float x2, float z2, float red, float green, float blue, float innerThickness, float outerThickness, boolean addThicknessLeftSideX, boolean addThicknessLeftSideZ, boolean addThicknessRightSideX, boolean addThicknessRightSideZ, CallbackInfo ci) {
        if (config.disableLightning || config.lightningAlpha == 0F || config.lightningColor.getAlpha() == 0) ci.cancel();
    }

    @ModifyArgs(method = "quad", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/VertexConsumer;setColor(FFFF)Lcom/mojang/blaze3d/vertex/VertexConsumer;"))
    private static void changeLightningColors(Args args) {
        Color color = ColorUtil.getColor(
                config.lightningChroma,
                config.lightningColor,
                config.lightningSpeed,
                config.lightningSaturation,
                config.lightningBrightness,
                config.lightningAlpha
        );
        args.set(0, color.getRed() / 255F);
        args.set(1, color.getGreen() / 255F);
        args.set(2, color.getBlue() / 255F);
        args.set(3, color.getAlpha() / 255F);
    }
}
