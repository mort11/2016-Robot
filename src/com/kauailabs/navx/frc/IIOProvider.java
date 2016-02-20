package com.kauailabs.navx.frc;

interface IIOProvider {
    boolean isConnected();

    double getByteCount();

    double getUpdateCount();

    void setUpdateRateHz(byte paramByte);

    void zeroYaw();

    void zeroDisplacement();

    void run();

    void stop();
}


