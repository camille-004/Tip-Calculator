package com.example.camilledunning.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText subtotal;
    private TextView tip;
    private TextView total;
    private ToggleButton toggle1;
    private ToggleButton toggle2;
    private Double tipPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        addEventListeners();
    }

    private void bindUI() {
        subtotal = (EditText) findViewById(R.id.editText1);
        tip = (TextView) findViewById(R.id.textView4);
        total = (TextView) findViewById(R.id.textView5);
        toggle1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggle2 = (ToggleButton) findViewById(R.id.toggleButton2);
    }

    private void addEventListeners() {
        addTextChangedListener();
        addToggleButtonListener();
    }

    private void addTextChangedListener() {
        subtotal.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        String s = subtotal.getText().toString();
        String zero = s.length() == 0 ? "0" : s;
        Double input = Double.parseDouble(zero);
        Double tipamt = tipPercentage * input;
        Double totalamt = input + tipamt;
        tip.setText(String.format("$%.2f", tipamt));
        total.setText(String.format("$%.2f", totalamt));
    }

    private void addToggleButtonListener() {
        toggle1.toggle();
        tipPercentage = 0.10;
        toggle1.setText("10%");
        toggle1.setTextOn("10%");
        toggle1.setTextOff("10%");

        toggle2.setText("20%");
        toggle2.setTextOn("20%");
        toggle2.setTextOff("20%");

        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (toggle2.isChecked()) {
                        toggle2.toggle();
                        tipPercentage = 0.10;
                        calculateTip();
                    }
                } else if (!isChecked && !toggle2.isChecked()){
                    toggle1.toggle();
                }
            }
        });

        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (toggle1.isChecked()) {
                        toggle1.toggle();
                        tipPercentage = 0.20;
                        calculateTip();
                    }
                } else if (!isChecked && !toggle1.isChecked()){
                    toggle2.toggle();
                }
            }
        });
    }
}
