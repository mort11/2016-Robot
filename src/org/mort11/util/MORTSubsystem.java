package org.mort11.util;

public interface MORTSubsystem {
    void disable();

    boolean isDisabled();

    void enable();

    double getCurrent();
}
