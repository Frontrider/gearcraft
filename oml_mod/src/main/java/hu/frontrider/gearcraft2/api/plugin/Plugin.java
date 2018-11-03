package hu.frontrider.gearcraft2.api.plugin;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface Plugin {
    Block[] getBlocks();
    Item[] getItems();
}
