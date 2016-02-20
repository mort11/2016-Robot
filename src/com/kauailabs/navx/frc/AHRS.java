package com.kauailabs.navx.frc;

import com.kauailabs.navx.AHRSProtocol;
import com.kauailabs.navx.IMUProtocol;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;


public class AHRS
        extends SensorBase
        implements PIDSource, LiveWindowSendable {
    static final byte NAVX_DEFAULT_UPDATE_RATE_HZ = 60;
    static final int YAW_HISTORY_LENGTH = 10;
    static final short DEFAULT_ACCEL_FSR_G = 2;
    static final short DEFAULT_GYRO_FSR_DPS = 2000;
    private final float DEV_UNITS_MAX = 32768.0F;
    private final float UTESLA_PER_DEV_UNIT = 0.15F;
    volatile float yaw;
    volatile float pitch;
    volatile float roll;
    volatile float compass_heading;
    volatile float world_linear_accel_x;
    volatile float world_linear_accel_y;
    volatile float world_linear_accel_z;
    volatile float mpu_temp_c;
    volatile float fused_heading;
    volatile float altitude;
    volatile float baro_pressure;
    volatile boolean is_moving;
    volatile boolean is_rotating;
    volatile float baro_sensor_temp_c;
    volatile boolean altitude_valid;
    volatile boolean is_magnetometer_calibrated;
    volatile boolean magnetic_disturbance;
    volatile short quaternionW;
    volatile short quaternionX;
    volatile short quaternionY;
    volatile short quaternionZ;
    float[] velocity = new float[3];
    float[] displacement = new float[3];
    volatile short raw_gyro_x;
    volatile short raw_gyro_y;
    volatile short raw_gyro_z;
    volatile short raw_accel_x;
    volatile short raw_accel_y;
    volatile short raw_accel_z;
    volatile short cal_mag_x;
    volatile short cal_mag_y;
    volatile short cal_mag_z;
    volatile byte update_rate_hz;
    volatile short accel_fsr_g = 2;
    volatile short gyro_fsr_dps = 2000;
    volatile short capability_flags;
    volatile byte op_status;
    volatile short sensor_status;
    volatile byte cal_status;
    volatile byte selftest_status;
    volatile byte board_type;
    volatile byte hw_rev;
    volatile byte fw_ver_major;
    volatile byte fw_ver_minor;
    long last_sensor_timestamp;
    double last_update_time;
    ITable m_table;
    InertialDataIntegrator integrator;
    ContinuousAngleTracker yaw_angle_tracker;
    OffsetTracker yaw_offset_tracker;
    IIOProvider io;
    BoardCapabilities board_capabilities;
    IOCompleteNotification io_complete_sink;
    IOThread io_thread;
    PIDSourceType pid_source_type = PIDSourceType.kDisplacement;

    public AHRS(SPI.Port spi_port_id, byte update_rate_hz) {
        commonInit(update_rate_hz);
        this.io = new RegisterIO(new RegisterIO_SPI(new SPI(spi_port_id)), update_rate_hz, this.io_complete_sink, this.board_capabilities);
        this.io_thread.start();
    }


    public AHRS(SPI.Port spi_port_id, int spi_bitrate, byte update_rate_hz) {
        commonInit(update_rate_hz);
        this.io = new RegisterIO(new RegisterIO_SPI(new SPI(spi_port_id), spi_bitrate), update_rate_hz, this.io_complete_sink, this.board_capabilities);
        this.io_thread.start();
    }


    public AHRS(I2C.Port i2c_port_id, byte update_rate_hz) {
        commonInit(update_rate_hz);
        this.io = new RegisterIO(new RegisterIO_I2C(new I2C(i2c_port_id, 50)), update_rate_hz, this.io_complete_sink, this.board_capabilities);
        this.io_thread.start();
    }


    public AHRS(SerialPort.Port serial_port_id, SerialDataType data_type, byte update_rate_hz) {
        commonInit(update_rate_hz);
        boolean processed_data = data_type == SerialDataType.kProcessedData;
        this.io = new SerialIO(serial_port_id, update_rate_hz, processed_data, this.io_complete_sink, this.board_capabilities);
        this.io_thread.start();
    }


    public AHRS(SPI.Port spi_port_id) {
        this(spi_port_id, (byte) 60);
    }


    public AHRS(I2C.Port i2c_port_id) {
        this(i2c_port_id, (byte) 60);
    }


    public AHRS(SerialPort.Port serial_port_id) {
        this(serial_port_id, SerialDataType.kProcessedData, (byte) 60);
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getRoll() {
        return this.roll;
    }

    public float getYaw() {
        if (this.board_capabilities.isBoardYawResetSupported()) {
            return this.yaw;
        }
        return (float) this.yaw_offset_tracker.applyOffset(this.yaw);
    }

    public float getCompassHeading() {
        return this.compass_heading;
    }

    public void zeroYaw() {
        if (this.board_capabilities.isBoardYawResetSupported()) {
            this.io.zeroYaw();
        } else {
            this.yaw_offset_tracker.setOffset();
        }
    }

    public boolean isCalibrating() {
        return (this.cal_status & 0x3) != 2;
    }

    public boolean isConnected() {
        return this.io.isConnected();
    }

    public double getByteCount() {
        return this.io.getByteCount();
    }

    public double getUpdateCount() {
        return this.io.getUpdateCount();
    }

    public float getWorldLinearAccelX() {
        return this.world_linear_accel_x;
    }

    public float getWorldLinearAccelY() {
        return this.world_linear_accel_y;
    }

    public float getWorldLinearAccelZ() {
        return this.world_linear_accel_z;
    }

    public boolean isMoving() {
        return this.is_moving;
    }

    public boolean isRotating() {
        return this.is_rotating;
    }

    public float getBarometricPressure() {
        return this.baro_pressure;
    }

    public float getAltitude() {
        return this.altitude;
    }

    public boolean isAltitudeValid() {
        return this.altitude_valid;
    }

    public float getFusedHeading() {
        return this.fused_heading;
    }

    public boolean isMagneticDisturbance() {
        return this.magnetic_disturbance;
    }

    public boolean isMagnetometerCalibrated() {
        return this.is_magnetometer_calibrated;
    }

    public float getQuaternionW() {
        return this.quaternionW / 16384.0F;
    }

    public float getQuaternionX() {
        return this.quaternionX / 16384.0F;
    }

    public float getQuaternionY() {
        return this.quaternionY / 16384.0F;
    }

    public float getQuaternionZ() {
        return this.quaternionZ / 16384.0F;
    }

    public void resetDisplacement() {
        if (this.board_capabilities.isDisplacementSupported()) {
            this.io.zeroDisplacement();
        } else {
            this.integrator.resetDisplacement();
        }
    }

    private void updateDisplacement(float accel_x_g, float accel_y_g, int update_rate_hz, boolean is_moving) {
        this.integrator.updateDisplacement(accel_x_g, accel_y_g, update_rate_hz, is_moving);
    }

    public float getVelocityX() {
        return this.board_capabilities.isDisplacementSupported() ? this.velocity[0] : this.integrator.getVelocityX();
    }

    public float getVelocityY() {
        return this.board_capabilities.isDisplacementSupported() ? this.velocity[1] : this.integrator.getVelocityY();
    }

    public float getVelocityZ() {
        return this.board_capabilities.isDisplacementSupported() ? this.velocity[2] : 0.0F;
    }

    public float getDisplacementX() {
        return this.board_capabilities.isDisplacementSupported() ? this.displacement[0] : this.integrator.getDisplacementX();
    }

    public float getDisplacementY() {
        return this.board_capabilities.isDisplacementSupported() ? this.displacement[1] : this.integrator.getDisplacementY();
    }

    public float getDisplacementZ() {
        return this.board_capabilities.isDisplacementSupported() ? this.displacement[2] : 0.0F;
    }

    private void commonInit(byte update_rate_hz) {
        this.board_capabilities = new BoardCapabilities();
        this.io_complete_sink = new IOCompleteNotification();
        this.io_thread = new IOThread();
        this.update_rate_hz = update_rate_hz;
        this.integrator = new InertialDataIntegrator();
        this.yaw_offset_tracker = new OffsetTracker(10);
        this.yaw_angle_tracker = new ContinuousAngleTracker();
    }

    public PIDSourceType getPIDSourceType() {
        return this.pid_source_type;
    }

    public void setPIDSourceType(PIDSourceType type) {
        this.pid_source_type = type;
    }

    public double pidGet() {
        if (this.pid_source_type == PIDSourceType.kRate) {
            return getRate();
        }
        return getYaw();
    }

    public double getAngle() {
        return this.yaw_angle_tracker.getAngle();
    }

    public double getRate() {
        return this.yaw_angle_tracker.getRate();
    }

    public void reset() {
        zeroYaw();
    }

    public float getRawGyroX() {
        return this.raw_gyro_x / (32768.0F / this.gyro_fsr_dps);
    }

    public float getRawGyroY() {
        return this.raw_gyro_y / (32768.0F / this.gyro_fsr_dps);
    }

    public float getRawGyroZ() {
        return this.raw_gyro_z / (32768.0F / this.gyro_fsr_dps);
    }

    public float getRawAccelX() {
        return this.raw_accel_x / (32768.0F / this.accel_fsr_g);
    }

    public float getRawAccelY() {
        return this.raw_accel_y / (32768.0F / this.accel_fsr_g);
    }

    public float getRawAccelZ() {
        return this.raw_accel_z / (32768.0F / this.accel_fsr_g);
    }

    public float getRawMagX() {
        return this.cal_mag_x / 0.15F;
    }

    public float getRawMagY() {
        return this.cal_mag_y / 0.15F;
    }

    public float getRawMagZ() {
        return this.cal_mag_z / 0.15F;
    }

    public float getPressure() {
        return 0.0F;
    }

    public float getTempC() {
        return this.mpu_temp_c;
    }

    public BoardYawAxis getBoardYawAxis() {
        BoardYawAxis yaw_axis = new BoardYawAxis();
        short yaw_axis_info = (short) (this.capability_flags >> 3);
        yaw_axis_info = (short) (yaw_axis_info & 0x7);
        if (yaw_axis_info == 0) {
            yaw_axis.up = true;
            yaw_axis.board_axis = BoardAxis.kBoardAxisZ;
        } else {
            yaw_axis.up = ((yaw_axis_info & 0x1) != 0);
            yaw_axis_info = (short) (yaw_axis_info >> 1);
            switch ((byte) yaw_axis_info) {
                case 0:
                    yaw_axis.board_axis = BoardAxis.kBoardAxisX;
                    break;
                case 1:
                    yaw_axis.board_axis = BoardAxis.kBoardAxisY;
                    break;
                case 2:
                default:
                    yaw_axis.board_axis = BoardAxis.kBoardAxisZ;
            }

        }
        return yaw_axis;
    }

    public String getFirmwareVersion() {
        double version_number = this.fw_ver_major;
        version_number += this.fw_ver_minor / 10.0D;
        String fw_version = Double.toString(version_number);
        return fw_version;
    }

    public void updateTable() {
        if (this.m_table != null) {
            this.m_table.putNumber("", getYaw());
        }
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }

    public void initTable(ITable itable) {
        this.m_table = itable;
        updateTable();
    }

    public ITable getTable() {
        return this.m_table;
    }

    public String getSmartDashboardType() {
        return "";
    }


    public enum BoardAxis {
        kBoardAxisX(0),
        kBoardAxisY(1),
        kBoardAxisZ(2);

        private int value;

        BoardAxis(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }


    public enum SerialDataType {
        kProcessedData(0),


        kRawData(1);

        private int value;

        SerialDataType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class BoardYawAxis {
        public AHRS.BoardAxis board_axis;


        public boolean up;
    }

    class IOThread
            implements Runnable {
        Thread m_thread;
        boolean stop;

        IOThread() {
        }

        public void start() {
            this.m_thread = new Thread(this);
            this.m_thread.start();
        }

        public void run() {
            AHRS.this.io.run();
        }


        public void stop() {
        }
    }

    class BoardCapabilities
            implements IBoardCapabilities {
        BoardCapabilities() {
        }


        public boolean isOmniMountSupported() {
            return (AHRS.this.capability_flags & 0x4) != 0;
        }


        public boolean isBoardYawResetSupported() {
            return (AHRS.this.capability_flags & 0x80) != 0;
        }


        public boolean isDisplacementSupported() {
            return (AHRS.this.capability_flags & 0x40) != 0;
        }
    }

    class IOCompleteNotification implements IIOCompleteNotification {
        IOCompleteNotification() {
        }

        public void setYawPitchRoll(IMUProtocol.YPRUpdate ypr_update) {
            AHRS.this.yaw = ypr_update.yaw;
            AHRS.this.pitch = ypr_update.pitch;
            AHRS.this.roll = ypr_update.roll;
            AHRS.this.compass_heading = ypr_update.compass_heading;
        }


        public void setAHRSPosData(AHRSProtocol.AHRSPosUpdate ahrs_update) {
            AHRS.this.yaw = ahrs_update.yaw;
            AHRS.this.pitch = ahrs_update.pitch;
            AHRS.this.roll = ahrs_update.roll;
            AHRS.this.compass_heading = ahrs_update.compass_heading;
            AHRS.this.yaw_offset_tracker.updateHistory(ahrs_update.yaw);


            AHRS.this.fused_heading = ahrs_update.fused_heading;


            AHRS.this.world_linear_accel_x = ahrs_update.linear_accel_x;
            AHRS.this.world_linear_accel_y = ahrs_update.linear_accel_y;
            AHRS.this.world_linear_accel_z = ahrs_update.linear_accel_z;


            AHRS.this.mpu_temp_c = ahrs_update.mpu_temp;


            AHRS.this.altitude = ahrs_update.altitude;
            AHRS.this.baro_pressure = ahrs_update.barometric_pressure;


            AHRS.this.is_moving = ((ahrs_update.sensor_status & 0x1) != 0);


            AHRS.this.is_rotating = ((ahrs_update.sensor_status & 0x2) == 0);


            AHRS.this.altitude_valid = ((ahrs_update.sensor_status & 0x8) != 0);


            AHRS.this.is_magnetometer_calibrated = ((ahrs_update.cal_status & 0x4) != 0);


            AHRS.this.magnetic_disturbance = ((ahrs_update.sensor_status & 0x4) != 0);


            AHRS.this.quaternionW = ahrs_update.quat_w;
            AHRS.this.quaternionX = ahrs_update.quat_x;
            AHRS.this.quaternionY = ahrs_update.quat_y;
            AHRS.this.quaternionZ = ahrs_update.quat_z;

            AHRS.this.velocity[0] = ahrs_update.vel_x;
            AHRS.this.velocity[1] = ahrs_update.vel_y;
            AHRS.this.velocity[2] = ahrs_update.vel_z;
            AHRS.this.displacement[0] = ahrs_update.disp_x;
            AHRS.this.displacement[1] = ahrs_update.disp_y;
            AHRS.this.displacement[2] = ahrs_update.disp_z;

            AHRS.this.yaw_angle_tracker.nextAngle(AHRS.this.getYaw());
        }

        public void setRawData(IMUProtocol.GyroUpdate raw_data_update) {
            AHRS.this.raw_gyro_x = raw_data_update.gyro_x;
            AHRS.this.raw_gyro_y = raw_data_update.gyro_y;
            AHRS.this.raw_gyro_z = raw_data_update.gyro_z;
            AHRS.this.raw_accel_x = raw_data_update.accel_x;
            AHRS.this.raw_accel_y = raw_data_update.accel_y;
            AHRS.this.raw_accel_z = raw_data_update.accel_z;
            AHRS.this.cal_mag_x = raw_data_update.mag_x;
            AHRS.this.cal_mag_y = raw_data_update.mag_y;
            AHRS.this.cal_mag_z = raw_data_update.mag_z;
            AHRS.this.mpu_temp_c = raw_data_update.temp_c;
        }


        public void setAHRSData(AHRSProtocol.AHRSUpdate ahrs_update) {
            AHRS.this.yaw = ahrs_update.yaw;
            AHRS.this.pitch = ahrs_update.pitch;
            AHRS.this.roll = ahrs_update.roll;
            AHRS.this.compass_heading = ahrs_update.compass_heading;
            AHRS.this.yaw_offset_tracker.updateHistory(ahrs_update.yaw);


            AHRS.this.fused_heading = ahrs_update.fused_heading;


            AHRS.this.world_linear_accel_x = ahrs_update.linear_accel_x;
            AHRS.this.world_linear_accel_y = ahrs_update.linear_accel_y;
            AHRS.this.world_linear_accel_z = ahrs_update.linear_accel_z;


            AHRS.this.mpu_temp_c = ahrs_update.mpu_temp;


            AHRS.this.altitude = ahrs_update.altitude;
            AHRS.this.baro_pressure = ahrs_update.barometric_pressure;


            AHRS.this.cal_mag_x = ahrs_update.cal_mag_x;
            AHRS.this.cal_mag_y = ahrs_update.cal_mag_y;
            AHRS.this.cal_mag_z = ahrs_update.cal_mag_z;


            AHRS.this.is_moving = ((ahrs_update.sensor_status & 0x1) != 0);


            AHRS.this.is_rotating = ((ahrs_update.sensor_status & 0x2) == 0);


            AHRS.this.altitude_valid = ((ahrs_update.sensor_status & 0x8) != 0);


            AHRS.this.is_magnetometer_calibrated = ((ahrs_update.cal_status & 0x4) != 0);


            AHRS.this.magnetic_disturbance = ((ahrs_update.sensor_status & 0x4) != 0);


            AHRS.this.quaternionW = ahrs_update.quat_w;
            AHRS.this.quaternionX = ahrs_update.quat_x;
            AHRS.this.quaternionY = ahrs_update.quat_y;
            AHRS.this.quaternionZ = ahrs_update.quat_z;

            AHRS.this.updateDisplacement(AHRS.this.world_linear_accel_x, AHRS.this.world_linear_accel_y, AHRS.this.update_rate_hz, AHRS.this.is_moving);


            AHRS.this.yaw_angle_tracker.nextAngle(AHRS.this.getYaw());
        }

        public void setBoardID(AHRSProtocol.BoardID board_id) {
            AHRS.this.board_type = board_id.type;
            AHRS.this.hw_rev = board_id.hw_rev;
            AHRS.this.fw_ver_major = board_id.fw_ver_major;
            AHRS.this.fw_ver_minor = board_id.fw_ver_minor;
        }

        public void setBoardState(IIOCompleteNotification.BoardState board_state) {
            AHRS.this.update_rate_hz = board_state.update_rate_hz;
            AHRS.this.accel_fsr_g = board_state.accel_fsr_g;
            AHRS.this.gyro_fsr_dps = board_state.gyro_fsr_dps;
            AHRS.this.capability_flags = board_state.capability_flags;
            AHRS.this.op_status = board_state.op_status;
            AHRS.this.sensor_status = board_state.sensor_status;
            AHRS.this.cal_status = board_state.cal_status;
            AHRS.this.selftest_status = board_state.selftest_status;
        }
    }
}


