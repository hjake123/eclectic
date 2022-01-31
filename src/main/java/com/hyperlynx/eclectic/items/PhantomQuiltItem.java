package com.hyperlynx.eclectic.items;

import com.hyperlynx.eclectic.Registration;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class PhantomQuiltItem extends BlockItem {
    public PhantomQuiltItem(Properties pProperties) {
        super(Registration.PHANTOM_QUILT.get(), pProperties);
    }

    // TODO: Catch the player as they fall.
}
