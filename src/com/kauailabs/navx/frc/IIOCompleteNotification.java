package com.kauailabs.navx.frc;

import com.kauailabs.navx.AHRSProtocol;
import com.kauailabs.navx.IMUProtocol;

interface IIOCompleteNotification {
    void setYawPitchRoll(IMUProtocol.YPRUpdate paramYPRUpdate);

    void setAHRSData(AHRSProtocol.AHRSUpdate paramAHRSUpdate);

    void setAHRSPosData(AHRSProtocol.AHRSPosUpdate paramAHRSPosUpdate);

    void setRawData(IMUProtocol.GyroUpdate paramGyroUpdate);

    void setBoardID(AHRSProtocol.BoardID paramBoardID);

    void setBoardState(BoardState paramBoardState);

    class BoardState {
        public byte op_status;
        public short sensor_status;
        public byte cal_status;
        public byte selftest_status;
        public short capability_flags;
        public byte update_rate_hz;
        public short accel_fsr_g;
        public short gyro_fsr_dps;
    }
}


