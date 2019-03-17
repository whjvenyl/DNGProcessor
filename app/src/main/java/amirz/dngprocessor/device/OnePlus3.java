package amirz.dngprocessor.device;

import android.util.Rational;
import android.util.SparseArray;

import amirz.dngprocessor.params.SensorParams;
import amirz.dngprocessor.parser.TIFF;
import amirz.dngprocessor.parser.TIFFTag;

public class OnePlus3 extends OnePlus {
    @Override
    public boolean isModel(String model) {
        return model.startsWith("ONEPLUS A3");
    }

    @Override
    public void sensorCorrection(SparseArray<TIFFTag> tags, SensorParams sensor) {
        super.sensorCorrection(tags, sensor);

        // Set a more red neutral point, to blue shift the final image
        if (noLight(tags)) {
            sensor.neutralColorPoint[2] = new Rational(
                    sensor.neutralColorPoint[2].getNumerator() * 13,
                    sensor.neutralColorPoint[2].getDenominator() * 15);
        } else if (lowLight(tags)) {
            sensor.neutralColorPoint[2] = new Rational(
                    sensor.neutralColorPoint[2].getNumerator() * 14,
                    sensor.neutralColorPoint[2].getDenominator() * 15);
        }
    }

    private boolean lowLight(SparseArray<TIFFTag> tags) {
        return exposureAtLeast(tags, 0.05f);
    }

    private boolean noLight(SparseArray<TIFFTag> tags) {
        return exposureAtLeast(tags, 0.1f);
    }

    private boolean exposureAtLeast(SparseArray<TIFFTag> tags, float min) {
        TIFFTag exposure = tags.get(TIFF.TAG_ExposureTime);
        return exposure != null && exposure.getRational().floatValue() >= min;
    }
}
