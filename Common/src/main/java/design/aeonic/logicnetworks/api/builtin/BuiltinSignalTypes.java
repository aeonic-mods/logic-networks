package design.aeonic.logicnetworks.api.builtin;

import design.aeonic.logicnetworks.api.core.CommonRegistries;
import design.aeonic.logicnetworks.api.core.Constants;
import design.aeonic.logicnetworks.api.logic.SignalType;
import net.minecraft.resources.ResourceLocation;

public final class BuiltinSignalTypes {
    /**
     * Analog redstone signal type, values 0-15.
     */
    public static final SignalType<Integer> ANALOG = new SignalType<>(Integer.class, 0xDC143C);
    /**
     * Digital redstone signal type, boolean values.
     */
    public static final SignalType<Boolean> BOOLEAN = new SignalType<>(Boolean.class, 0x66CDAA);

    public static void register() {
        CommonRegistries.SIGNAL_TYPES.register(new ResourceLocation(Constants.MOD_ID, "analog"), ANALOG);
        CommonRegistries.SIGNAL_TYPES.register(new ResourceLocation(Constants.MOD_ID, "boolean"), BOOLEAN);
    }
}
