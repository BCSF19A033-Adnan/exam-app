package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionNo, question;
    Button option1, option2, option3, option4;
    String [] questionList = {"Which was the king who established rationing system?", "Who robbed the Arabs trade ship?",
            "How many times did Mahmood Ghaznavi invade on India?", "From where did the Aryans come into South Asia?",
            "What was called the head of Shaqq?", "What was called the head of the Diwan-e-Arz?",
            "Whose daughter was Razia Sultan?", "Who were defeated by the Aryans in South Asia?",
            "Which was the religion, whose founder was Mahaveer?", "From which race Khiljis belonged?"};

    String [][] optionsForQuestions = {{"Qutubuddin Aibak", "Illtutmish", "Allauddin", "Muhammad Tughlaq"}, {"Makranis", "Robbers", "Pirates", "Fisher men"}, {"Fifteen", "Sixteen", "Seventeen", "Eighteen"},
            {"Central Asia", "Middle East", "Greece", "China"}, {"Shiqq-Dar", "Soob-e-Dar", "Nazim", "Kotwal"}, {"Sadr-us-sadr", "Arz", "Sahib-e-Diwan", "Wazir"},
            {"Qutubuddin Aibak", "Sultan Ghias ud Din", "Sultan Iltutmish", "Nasir ud Din"}, {"Sumerians", "Turks", "Dravidians", "Chinese"}, {"Hinduism", "Jainism", "Sikhism", "Buddhism"},
            {"Turk", "Afghan", "Arab", "Mughal"}};

    String [] correctAnsList = {"Allauddin", "Pirates", "Seventeen", "Central Asia", "Shiqq-Dar",
            "Arz", "Sultan Iltutmish", "Dravidians", "Jainism", "Turk"};
    ArrayList<Integer> wrongAnsList = new ArrayList<>();
    int noOfWrongQuestions=0, currentQuestionIndex;

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


        showQuestion();
    }

    protected void showQuestion()
    {
        int no;
        Random rnd = new Random();
        ArrayList<String> optionList = new ArrayList<>();

        currentQuestionIndex = rnd.nextInt(questionList.length);
        question.setText(questionList[currentQuestionIndex]);

        optionList.add(optionsForQuestions[currentQuestionIndex][0]);
        optionList.add(optionsForQuestions[currentQuestionIndex][1]);
        optionList.add(optionsForQuestions[currentQuestionIndex][2]);
        optionList.add(optionsForQuestions[currentQuestionIndex][3]);

        Collections.shuffle(optionList);

        option1.setText(optionList.get(0));
        option2.setText(optionList.get(1));
        option3.setText(optionList.get(2));
        option4.setText(optionList.get(3));
    }
}