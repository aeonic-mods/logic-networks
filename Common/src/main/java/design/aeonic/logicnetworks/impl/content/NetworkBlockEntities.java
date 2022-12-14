package design.aeonic.logicnetworks.impl.content;

import design.aeonic.logicnetworks.api.util.Registrar;
import design.aeonic.logicnetworks.impl.content.anchor.NetworkAnchorBlockEntity;
import design.aeonic.logicnetworks.impl.content.cache.NetworkCacheBlockEntity;
import design.aeonic.logicnetworks.impl.content.controller.NetworkControllerBlockEntity;
import design.aeonic.logicnetworks.impl.services.Services;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class NetworkBlockEntities {
    public static final BlockEntityType<NetworkControllerBlockEntity> NETWORK_CONTROLLER = Services.ACCESS.blockEntityType(NetworkControllerBlockEntity::new, NetworkBlocks.NETWORK_CONTROLLER);
    public static final BlockEntityType<NetworkAnchorBlockEntity> NETWORK_ANCHOR = Services.ACCESS.blockEntityType(NetworkAnchorBlockEntity::new, NetworkBlocks.NETWORK_ANCHOR);
    public static final BlockEntityType<NetworkCacheBlockEntity> NETWORK_CACHE = Services.ACCESS.blockEntityType(NetworkCacheBlockEntity::new, NetworkBlocks.NETWORK_CACHE);

    public static void register(Registrar<BlockEntityType<?>> registrar) {
        registrar.accept("network_controller", NETWORK_CONTROLLER);
        registrar.accept("network_anchor", NETWORK_ANCHOR);
        registrar.accept("network_cache", NETWORK_CACHE);
    }
}
