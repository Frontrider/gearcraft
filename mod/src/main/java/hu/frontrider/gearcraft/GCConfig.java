package hu.frontrider.gearcraft;

import net.minecraftforge.common.config.Config;

import static hu.frontrider.gearcraft.GearCraft.MODID;

@Config(modid = MODID)
public class GCConfig {

    public static String[] GrinderExtraDustList = new String[]{
        "dustIron","dustGold","dustCopper","dustRedstone","dustLead","dust"
    };
}
