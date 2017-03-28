package com.example.camilledunning.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private SeekBar sb1;
    private SeekBar sb2;
    private double percentage1;
    private double percentage2;
    private double percentage3;
    private SharedPreferences sharedPreferences;
    public static final String PERCENTAGE_KEY_0 = "percent_key_0";
    public static final String PERCENTAGE_KEY_1 = "percent_key_1";
    public static final String PERCENTAGE_KEY_2 = "percent_key_2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        bindUI();
        addEventListeners();

        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        getDefaultsfromSharedPreferences();
    }

    private void getDefaultsfromSharedPreferences() {
        
    }

    @Override
    protected void onResume() {
        percentage1 = sharedPreferences.getLong(PERCENTAGE_KEY_0, 0.10);
        percentage2 = sharedPreferences.getLong(PERCENTAGE_KEY_1, 0.15);
        percentage3 = sharedPreferences.getLong(PERCENTAGE_KEY_2, 0.20);

    }

    private void bindUI() {
        tv1 = (TextView) findViewById(R.id.settingsTextView0);
        tv2 = (TextView) findViewById(R.id.settingsTextView1);
        sb1 = (SeekBar) findViewById(R.id.settingsSeekBar1);
        sb2 = (SeekBar) findViewById(R.id.settingsSeekBar2);
    }

    private void addEventListeners() {
        seekBarListener1();
        seekBarListener2();
    }

    private void seekBarListener1() {
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    tv1.setText(String.format("%d%%", progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                long val = Double.doubleToLongBits(seekBar.getProgress() / 100.0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(getString(R.string.tip_percentage1_key), val);
                editor.commit();
            }
        });

    }

    private void seekBarListener2() {
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    tv2.setText(String.format("%d%%", progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                long val = Double.doubleToLongBits(seekBar.getProgress() / 100.0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(getString(R.string.tip_percentage2_key), val);
                editor.commit();
            }
        });
    }
}
