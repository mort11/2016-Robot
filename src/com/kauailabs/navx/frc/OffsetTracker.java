package com.kauailabs.navx.frc;

import java.util.Arrays;


class OffsetTracker {
    float[] value_history;
    int next_value_history_index;
    int history_len;
    double value_offset;

    public OffsetTracker(int history_length) {
        this.history_len = history_length;
        this.value_history = new float[this.history_len];
        Arrays.fill(this.value_history, 0.0F);
        this.next_value_history_index = 0;
        this.value_offset = 0.0D;
    }

    public void updateHistory(float curr_value) {
        if (this.next_value_history_index >= this.history_len) {
            this.next_value_history_index = 0;
        }
        this.value_history[this.next_value_history_index] = curr_value;
        this.next_value_history_index += 1;
    }

    public double getAverageFromHistory() {
        double value_history_sum = 0.0D;
        for (int i = 0; i < this.history_len; i++) {
            value_history_sum += this.value_history[i];
        }
        double value_history_avg = value_history_sum / this.history_len;
        return value_history_avg;
    }

    public void setOffset() {
        this.value_offset = getAverageFromHistory();
    }

    public double getOffset() {
        return this.value_offset;
    }

    public double applyOffset(double value) {
        float offseted_value = (float) (value - this.value_offset);
        if (offseted_value < -180.0F) {
            offseted_value += 360.0F;
        }
        if (offseted_value > 180.0F) {
            offseted_value -= 360.0F;
        }
        return offseted_value;
    }
}


