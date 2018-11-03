package hu.frontrider.gearcraft2;

import net.minecraftforge.common.config.Config;

import static hu.frontrider.gearcraft.GearCraft.MODID;

@Config(modid = MODID)
public class GCConfig {

    @Config.Comment({
            "Enables debugging for the GUI",
            "It will show the outlines of the clickable areas."
    })
    @Config.Name("GUI Debug mode")
    public static boolean GUIDebug =true;
}
