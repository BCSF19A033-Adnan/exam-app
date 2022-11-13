package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView questionNo, question;
    Button option1, option2, option3, option4;
    String [] questionList = {"d", "h", "l", "k", "t",
            "b", "f", "", "", ""};
    String [][] optionsForQuestions = {{}, {}, {}, {}, {},
            {}, {}, {}, {}, {}};
    String [] correctAnsList = {"d", "h", "l", "k", "t",
            "b", "f", "", "", ""};
    ArrayList<Integer> wrongAnsList = new ArrayList<>();
    int noOfWrongQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionNo = findViewById(R.id.questionTitle);
        question = findViewById(R.id.questionField);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

    }
}