package amirz.dngprocessor.device;

import android.util.SparseArray;

import amirz.dngprocessor.params.ProcessParams;
import amirz.dngprocessor.params.SensorParams;
import amirz.dngprocessor.parser.TIFFTag;

class Generic implements DeviceMap.Device {
    @Override
    public boolean isModel(String model) {
        return true;
    }

    @Override
    public void sensorCorrection(SparseArray<TIFFTag> tags, SensorParams sensor) {
    }

    @Override
    public void processCorrection(SparseArray<TIFFTag> tags, ProcessParams process) {
    }
}
