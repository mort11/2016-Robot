package org.mort11.util;

public interface MORTSubsystem {
    /**
     * Disable the subsystem
     */
    void disable();

    /**
     * Check if subsystem is disabled
     *
     * @return Subsystem state
     */
    boolean isDisabled();

    /**
     * Re-enable subsystem that is in a disabled state
     */
    void enable();
}
