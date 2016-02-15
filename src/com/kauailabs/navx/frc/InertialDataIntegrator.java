package com.kauailabs.navx.frc;


class InertialDataIntegrator {
    private float[] last_velocity = new float[2];
    private float[] displacement = new float[2];

    public InertialDataIntegrator() {
        resetDisplacement();
    }

    public void updateDisplacement(float accel_x_g, float accel_y_g, int update_rate_hz, boolean is_moving) {
        if (is_moving) {
            float[] accel_g = new float[2];
            float[] accel_m_s2 = new float[2];
            float[] curr_velocity_m_s = new float[2];
            float sample_time = 1.0F / update_rate_hz;
            accel_g[0] = accel_x_g;
            accel_g[1] = accel_y_g;
            for (int i = 0; i < 2; i++) {
                accel_g[i] *= 9.80665F;
                curr_velocity_m_s[i] = (this.last_velocity[i] + accel_m_s2[i] * sample_time);
                this.displacement[i] += this.last_velocity[i] + 0.5F * accel_m_s2[i] * sample_time * sample_time;
                this.last_velocity[i] = curr_velocity_m_s[i];
            }
        } else {
            this.last_velocity[0] = 0.0F;
            this.last_velocity[1] = 0.0F;
        }
    }

    public void resetDisplacement() {
        for (int i = 0; i < 2; i++) {
            this.last_velocity[i] = 0.0F;
            this.displacement[i] = 0.0F;
        }
    }

    public float getVelocityX() {
        return this.last_velocity[0];
    }

    public float getVelocityY() {
        return this.last_velocity[1];
    }

    public float getVelocityZ() {
        return 0.0F;
    }

    public float getDisplacementX() {
        return this.displacement[0];
    }

    public float getDisplacementY() {
        return this.displacement[1];
    }

    public float getDisplacementZ() {
        return 0.0F;
    }
}


