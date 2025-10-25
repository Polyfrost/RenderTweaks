package org.polyfrost.rendertweaks.mixins;

import net.minecraft.client.renderer.entity.RenderLightningBolt;
import org.polyfrost.rendertweaks.RenderTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderLightningBolt.class)
public class RenderLightningBoltMixin {
    @Inject(method = "doRender(Lnet/minecraft/entity/effect/EntityLightningBolt;DDDFF)V", at = @At("HEAD"), cancellable = true)
    private void patcher$cancelLightningBoltRender(CallbackInfo ci) {
        if (RenderTweaks.config.disableLightning) ci.cancel();
    }
}
