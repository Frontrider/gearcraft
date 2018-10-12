package hu.frontrider.gearcraft.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface Plugin {
    Block[] getBlocks();
    Item[] getItems();
}
