package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentDisplay = "";
    private String operator = "";
    private double operand1 = 0;
    private double operand2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtons = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                currentDisplay += button.getText().toString();
                display.setText(currentDisplay);
            }
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtons = {
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentDisplay.isEmpty()) {
                    operand1 = Double.parseDouble(currentDisplay);
                    currentDisplay = "";
                    display.setText("");
                    Button button = (Button) view;
                    operator = button.getText().toString();
                }
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentDisplay.isEmpty() && !operator.isEmpty()) {
                    operand2 = Double.parseDouble(currentDisplay);
                    double result = 0;

                    switch (operator) {
                        case "+":
                            result = operand1 + operand2;
                            break;
                        case "-":
                            result = operand1 - operand2;
                            break;
                        case "*":
                            result = operand1 * operand2;
                            break;
                        case "/":
                            if (operand2 != 0) {
                                result = operand1 / operand2;
                            } else {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }

                    display.setText(String.valueOf(result));
                    currentDisplay = String.valueOf(result);
                    operator = "";
                }
            }
        });

        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDisplay = "";
                display.setText("0");
                operator = "";
            }
        });

        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentDisplay.isEmpty()) {
                    currentDisplay = currentDisplay.substring(0, currentDisplay.length() - 1);
                    display.setText(currentDisplay.isEmpty() ? "0" : currentDisplay);
                }
            }
        });
    }
}
