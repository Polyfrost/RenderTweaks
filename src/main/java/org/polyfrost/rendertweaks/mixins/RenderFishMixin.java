package org.polyfrost.rendertweaks.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.entity.player.EntityPlayer;
import org.polyfrost.rendertweaks.RenderTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderFish.class)
public class RenderFishMixin {
    @ModifyExpressionValue(
        method = "doRender(Lnet/minecraft/entity/projectile/EntityFishHook;DDDFF)V",
        at = @At(value = "FIELD", target = "Lnet/minecraft/entity/projectile/EntityFishHook;angler:Lnet/minecraft/entity/player/EntityPlayer;")
    )
    private EntityPlayer rendertweaks$cancelFishingHook(EntityPlayer original) {
        if (RenderTweaks.config.disableFishingLine) return null;
        return original;
    }
}
