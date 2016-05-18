package org.mort11.util.camera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

public class ImageCalibration {
    public double getBlob(Image image) {
        double numParticles = NIVision.imaqCountParticles(image, 0);
        double largestSize = 0, size;
        int particleIndex = 0;
        if (numParticles > 0) {
            for (int i = 0; i < numParticles; i++) {
                size = NIVision.imaqMeasureParticle(image, i, 0,
                        NIVision.MeasurementType.MT_AREA);
                if (size > largestSize) {
                    particleIndex = i;
                    largestSize = size;
                }
            }
            double loc_x = NIVision.imaqMeasureParticle(
                    image, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
            double loc_y = NIVision.imaqMeasureParticle(
                    image, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_Y);
            return loc_x;
        } else {
            return -1;
        }
    }
}