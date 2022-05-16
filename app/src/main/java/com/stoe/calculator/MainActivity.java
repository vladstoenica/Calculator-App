package com.stoe.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9
                    ,btnAC, btnDel, btnPlus, btnMinus, btnDiv, btnMulti, btnDot, btnEquals;

    private TextView textViewResult, textViewHistory;

    private String number = null; //il folosim ca sa vedem daca textViewResult e gol sau nu

    double firstNumber = 0, lastNumber = 0;

    String status = null;
    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("######.######");

    String history, currentResult;

    boolean dot = true;  //verifica sa nu fie mai multe virgule intr-un nr
    boolean btnACcontrol = true;
    boolean btnEqualsControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDiv = findViewById(R.id.btnDiv);
        btnMulti = findViewById(R.id.btnMulti);
        btnDot = findViewById(R.id.btnDot);
        btnEquals = findViewById(R.id.btnEqual);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("9");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = null;
                status = null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNumber = 0;
                lastNumber = 0;
                dot = true;
                btnACcontrol = true;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnACcontrol == true){
                    textViewResult.setText("0");
                } else {
                    number =  number.substring(0, number.length()-1);   //return the desired length of a string expression, adica returneaza stringul plecand de la 0 si se opreste la length -1
                    if(number.length() == 0) { //asa verifica daca nr e 0 aparent
                        btnDel.setClickable(false);
                    } else if (number.contains(".")){
                        dot = false;
                    } else {
                        dot = true;
                    }
                    textViewResult.setText(number);
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = textViewHistory.getText().toString();    //ca sa afiseze real time tot
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "+");

                if (operator){
                    if (status == "multiplication"){
                        multiply();
                    } else if (status == "division"){
                        divide();
                    } else if (status == "subtraction"){
                        minus();
                    } else
                        plus();
                }

                status = "sum";
                operator = false;
                number = null;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "-");

                if (operator){
                    if (status == "multiplication"){
                        multiply();
                    } else if (status == "division"){
                        divide();
                    } else if (status == "sum"){
                        plus();
                    } else
                        minus();
                }

                status = "subtraction";
                operator = false;
                number = null;
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "x");

                if (operator){
                    if (status == "sum"){
                        plus();
                    } else if (status == "division"){
                        divide();
                    } else if (status == "subtraction"){
                        minus();
                    } else
                        multiply();
                }

                status = "multiplication";
                operator = false;
                number = null;
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "/");

                if (operator){
                    if (status == "multiplication"){
                        multiply();
                    } else if (status == "sum"){
                        plus();
                    } else if (status == "subtraction"){
                        minus();
                    } else
                        divide();
                }

                status = "division";
                operator = false;
                number = null;
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dot == true){
                    if (number == null){
                        number = "0.";
                    } else {
                        number = number + ".";
                    }
                }
                textViewResult.setText(number);
                dot = false;
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operator){
                    if (status == "sum"){
                        plus();
                    } else if (status == "subtraction"){
                        minus();
                    } else if (status == "multiplication"){
                        multiply();
                    } else if (status == "division"){
                        divide();
                    } else
                        firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }

                operator = false;
                btnEqualsControl = true;
            }
        });


    }

    public void numberClick(String value){
        if (number == null){
            number = value;
        } else if (btnEqualsControl == true){
            firstNumber = 0;
            lastNumber = 0;
            number = value;
        }
        else {
            number = number + value;
        }
        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualsControl = false;
    }

    public void plus(){
        lastNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber = firstNumber + lastNumber;
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void minus(){
        if (firstNumber == 0){
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber - lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void multiply(){
        if (firstNumber == 0){
            firstNumber = 1;
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber * lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }

    public void divide(){
        if (firstNumber == 0){
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = lastNumber / 1;
        } else {
            lastNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber = firstNumber / lastNumber;
        }
        textViewResult.setText(myFormatter.format(firstNumber));
        dot = true;
    }



}