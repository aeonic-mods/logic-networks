package design.aeonic.logicnetworks.impl.services;

import design.aeonic.logicnetworks.api.services.PlatformAccess;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ForgePlatformAccess implements PlatformAccess {
    @Override
    public <T extends BlockEntity> BlockEntityType<T> blockEntityType(BlockEntitySupplier<T> supplier, Block... validBlocks) {
        return BlockEntityType.Builder.of(supplier::create, validBlocks).build(null);
    }

    @Override
    public <T extends AbstractContainerMenu> MenuType<T> menuType(MenuSupplier<T> menuSupplier) {
        return new MenuType<>(menuSupplier::create);
    }

    @Override
    public <M extends AbstractContainerMenu, S extends AbstractContainerScreen<M>> void registerScreen(MenuType<M> menuType, ScreenSupplier<M, S> screenSupplier) {
        MenuScreens.register(menuType, screenSupplier::create);
    }
}