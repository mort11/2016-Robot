package com.kauailabs.navx.frc;


class ContinuousAngleTracker {
    private float last_angle;


    private double last_rate;


    private int zero_crossing_count;


    public ContinuousAngleTracker() {
        this.last_angle = 0.0F;
        this.zero_crossing_count = 0;
        this.last_rate = 0.0D;
    }


    public void nextAngle(float newAngle) {
        float adjusted_last_angle = this.last_angle < 0.0F ? this.last_angle + 360.0F : this.last_angle;
        float adjusted_curr_angle = newAngle < 0.0F ? newAngle + 360.0F : newAngle;
        float delta_angle = adjusted_curr_angle - adjusted_last_angle;
        this.last_rate = delta_angle;

        int angle_last_direction = 0;
        if (adjusted_curr_angle < adjusted_last_angle) {
            if (delta_angle < -180.0F) {
                angle_last_direction = -1;
            } else {
                angle_last_direction = 1;
            }
        } else if (adjusted_curr_angle > adjusted_last_angle) {
            if (delta_angle > 180.0F) {
                angle_last_direction = -1;
            } else {
                angle_last_direction = 1;
            }
        }

        if (angle_last_direction < 0) {
            if ((adjusted_curr_angle < 0.0F) && (adjusted_last_angle >= 0.0F)) {
                this.zero_crossing_count -= 1;
            }
        } else if ((angle_last_direction > 0) &&
                (adjusted_curr_angle >= 0.0F) && (adjusted_last_angle < 0.0F)) {
            this.zero_crossing_count += 1;
        }

        this.last_angle = newAngle;
    }

    public double getAngle() {
        double accumulated_angle = this.zero_crossing_count * 360.0D;
        double curr_angle = this.last_angle;
        if (curr_angle < 0.0D) {
            curr_angle += 360.0D;
        }
        accumulated_angle += curr_angle;
        return accumulated_angle;
    }

    public double getRate() {
        return this.last_rate;
    }
}


