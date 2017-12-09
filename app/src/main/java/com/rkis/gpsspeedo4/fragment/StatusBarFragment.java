package com.rkis.gpsspeedo4.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.rkis.gpsspeedo4.R;
import com.rkis.gpsspeedo4.viewmodel.AccelerationViewModel;

import java.util.Locale;

/*
 * AccelerationExplorer
 * Copyright 2017 Kircher Electronics, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Created by kaleb on 7/7/17.
 */

public class StatusBarFragment extends LifecycleFragment {

    private static final String tag = StatusBarFragment.class.getSimpleName();

    // Text views for real-time output
    private TextView textViewXAxis;
    private TextView textViewYAxis;
    private TextView textViewZAxis;
    private TextView textViewHzFrequency;
    private SeekBar seekBar;

    private Handler handler;
    private Runnable runnable;

    private float[] acceleration;

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AccelerationViewModel model = ViewModelProviders.of(getActivity()).get(AccelerationViewModel.class);

        model.getAccelerationListener().observe(this, new Observer<float[]>() {
            @Override
            public void onChanged(@Nullable float[] floats) {
                acceleration = floats;
            }
        });

        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                handler.postDelayed(this, 20);
                updateAccelerationText();
            }
        };

        acceleration = new float[4];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_bar, container, false);

        textViewXAxis = (TextView) view.findViewById(R.id.value_x_axis);
        textViewYAxis = (TextView) view.findViewById(R.id.value_y_axis);
        textViewZAxis = (TextView) view.findViewById(R.id.value_z_axis);
        textViewHzFrequency = (TextView) view.findViewById(R.id.value_hz_frequency);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar1);

        return view;
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    private void updateAccelerationText()
    {
        // Update the acceleration data
        textViewXAxis.setText(String.format(Locale.getDefault(), "%.2f", acceleration[0]));
        textViewYAxis.setText(String.format(Locale.getDefault(),"%.2f", acceleration[1]));
        textViewZAxis.setText(String.format(Locale.getDefault(),"%.2f", acceleration[2]));
        textViewHzFrequency.setText(String.format(Locale.getDefault(),"%.0f", acceleration[3]));
        seekBar.setProgress((int)(acceleration[2] * 50) + 50);
    }
}
