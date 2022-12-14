package design.aeonic.logicnetworks;

import design.aeonic.logicnetworks.api.core.Constants;
import design.aeonic.logicnetworks.api.util.Registrar;
import design.aeonic.logicnetworks.impl.LogicNetworks;
import design.aeonic.logicnetworks.impl.client.NetworkBlockEntityRenderers;
import design.aeonic.logicnetworks.impl.client.NetworkItemProperties;
import design.aeonic.logicnetworks.impl.client.NetworkKeybinds;
import design.aeonic.logicnetworks.impl.content.NetworkBlockEntities;
import design.aeonic.logicnetworks.impl.content.NetworkBlocks;
import design.aeonic.logicnetworks.impl.content.NetworkItems;
import design.aeonic.logicnetworks.impl.content.NetworkMenus;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class ForgeLogicNetworks {
    
    public ForgeLogicNetworks() {
        LogicNetworks.init();

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener((RegisterEvent event) -> {
            NetworkBlocks.register(registrar(event, ForgeRegistries.Keys.BLOCKS));
            NetworkItems.register(registrar(event, ForgeRegistries.Keys.ITEMS));
            NetworkBlockEntities.register(registrar(event, ForgeRegistries.Keys.BLOCK_ENTITY_TYPES));
            NetworkMenus.register(registrar(event, ForgeRegistries.Keys.MENU_TYPES));
        });

        modBus.addListener((FMLClientSetupEvent event) -> {
            LogicNetworks.clientInit(event::enqueueWork);
            event.enqueueWork(() -> {
                NetworkItemProperties.register(ItemProperties::register);
            });
        });

        modBus.addListener((RegisterKeyMappingsEvent event) -> {
            NetworkKeybinds.register(($, mapping) -> {
                mapping.setKeyConflictContext(KeyConflictContext.GUI);
                event.register(mapping);
            });
        });

        modBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            NetworkBlockEntityRenderers.register(event::registerBlockEntityRenderer);
        });
    }

    <T> Registrar<T> registrar(RegisterEvent event, ResourceKey<? extends Registry<T>> registry) {
        return Registrar.of(Constants.MOD_ID, (key, value) -> event.register(registry, key, () -> value));
    }
}