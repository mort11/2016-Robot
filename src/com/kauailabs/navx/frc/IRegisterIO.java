package com.kauailabs.navx.frc;

interface IRegisterIO {
    boolean init();

    boolean write(byte paramByte1, byte paramByte2);

    boolean read(byte paramByte, byte[] paramArrayOfByte);

    boolean shutdown();
}


