package org.polyfrost.rendertweaks.config;

import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;
import org.polyfrost.rendertweaks.RenderTweaks;
import org.polyfrost.oneconfig.api.config.v1.Config;

public class RenderTweaksConfig extends Config {
    public RenderTweaksConfig() {
        super(RenderTweaks.ID + ".json", RenderTweaks.NAME, Category.QOL);
    }

    @Switch(
        title = "Disable Lightning Bolts",
        description = "Stop lightning bolts from appearing."
    )
    public boolean disableLightning = false;
    /*
    public Color lightningColor = new Color(0.45F, 0.45F, 0.5F, 0.3F);
    public float lightningAlpha = 102;
    public boolean lightningChroma = false;
    public float lightningSaturation = 1F;
    public float lightningBrightness = 1F;
    public float lightningSpeed = 0.25F;
     */

    @Switch(
        title = "Disable Fishing Line",
        description = "Stop the fishing line from appearing."
    )
    public boolean disableFishingLine = false;
    /*
    public Color fishingLineColor = new Color(-16777216);
    public float fishingLineAlpha = 102;
    public boolean fishingLineChroma = false;
    public float fishingLineSaturation = 1F;
    public float fishingLineBrightness = 1F;
    public float fishingLineSpeed = 0.25F;
     */

    /*
    public boolean disableWorldBorderStationary = false;
    public Color worldBorderStationaryColor = new Color(2138367);
    public float worldBorderStationaryAlpha = 102;
    public boolean worldBorderStationaryChroma = false;
    public float worldBorderStationarySaturation = 1F;
    public float worldBorderStationaryBrightness = 1F;
    public float worldBorderStationarySpeed = 0.25F;

    public boolean disableWorldBorderShrinking = false;
    public Color worldBorderShrinkingColor = new Color(16724016);
    public float worldBorderShrinkingAlpha = 102;
    public boolean worldBorderShrinkingChroma = false;
    public float worldBorderShrinkingSaturation = 1F;
    public float worldBorderShrinkingBrightness = 1F;
    public float worldBorderShrinkingSpeed = 0.25F;

    public boolean disableWorldBorderGrowing = false;
    public Color worldBorderGrowingColor = new Color(4259712);
    public float worldBorderGrowingAlpha = 102;
    public boolean worldBorderGrowingChroma = false;
    public float worldBorderGrowingSaturation = 1F;
    public float worldBorderGrowingBrightness = 1F;
    public float worldBorderGrowingSpeed = 0.25F;*/

}
