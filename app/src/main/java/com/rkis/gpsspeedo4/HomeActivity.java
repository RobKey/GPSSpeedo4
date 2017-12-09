package com.rkis.gpsspeedo4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rkis.gpsspeedo4.config.FilterConfigActivity;
import com.rkis.gpsspeedo4.livedata.AccelerationLiveData;
import com.rkis.gpsspeedo4.prefs.PrefUtils;

import com.rkis.gpsspeedo4.viewmodel.AccelerationViewModel;


public class HomeActivity extends AppCompatActivity {

    private final static String tag = HomeActivity.class.getSimpleName();
    private AccelerationLiveData liveData;
    private static TextView textViewZAxis;
    private static SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViewModel();
        textViewZAxis = (TextView) findViewById(R.id.value_z_axis);

    }




    public void doSettings(View v) {
        Intent intent = new Intent(HomeActivity.this,
                FilterConfigActivity.class);

        startActivity(intent);

    }
    private void initViewModel() {
        AccelerationViewModel model = ViewModelProviders.of(this).get(AccelerationViewModel.class);
        liveData = model.getAccelerationListener();
    }
    private void updateConfiguration() {
        liveData.setSensorFrequency(PrefUtils.getSensorFrequencyPrefs(this));
        liveData.setAxisInverted(PrefUtils.getInvertAxisPrefs(this));

        liveData.enableAndroidLinearAcceleration(PrefUtils.getPrefAndroidLinearAccelerationEnabled(this));
        liveData.enableFSensorComplimentaryLinearAcceleration(PrefUtils.getPrefFSensorComplimentaryLinearAccelerationEnabled(this));
        liveData.enableFSensorKalmanLinearAcceleration(PrefUtils.getPrefFSensorKalmanLinearAccelerationEnabled(this));
        liveData.enableFSensorLpfLinearAcceleration(PrefUtils.getPrefFSensorLpfLinearAccelerationEnabled(this));

        liveData.setFSensorComplimentaryLinearAccelerationTimeConstant(PrefUtils.getPrefFSensorComplimentaryLinearAccelerationTimeConstant(this));
        liveData.setFSensorLpfLinearAccelerationTimeConstant(PrefUtils.getPrefFSensorLpfLinearAccelerationTimeConstant(this));

        liveData.enableMeanFilterSmoothing(PrefUtils.getPrefMeanFilterSmoothingEnabled(this));
        liveData.enableMedianFilterSmoothing(PrefUtils.getPrefMedianFilterSmoothingEnabled(this));
        liveData.enableLpfSmoothing(PrefUtils.getPrefLpfSmoothingEnabled(this));

        liveData.setMeanFilterSmoothingTimeConstant(PrefUtils.getPrefMeanFilterSmoothingTimeConstant(this));
        liveData.setMedianFilterSmoothingTimeConstant(PrefUtils.getPrefMedianFilterSmoothingTimeConstant(this));
        liveData.setLpfSmoothingTimeConstant(PrefUtils.getPrefLpfSmoothingTimeConstant(this));
    }
}
