package design.aeonic.logicnetworks.api.logic;

public enum RedstoneControl {
    /**
     * Active regardless of redstone signal.
     */
    ALWAYS,
    /**
     *Active when the redstone signal is high.
     */
    HIGH,
    /**
     * Active when the redstone signal is off.
     */
    LOW,
    /**
     * Never active.
     */
    NEVER,
    /**
     * Runs once per pulse, on the rising edge.
     */
    PULSE;
}