package design.aeonic.logicnetworks.api.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public interface LinkCard {
    @Nullable BlockPos getLink(ItemStack stack);

    @Nullable Direction getDirection(ItemStack stack);

    LinkStatus getLinkStatus(ItemStack stack);
}
