package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionNo, question;
    Button option1, option2, option3, option4;

    String [] questionList = new String[200];
    String [][] optionsForQuestions = new String[200][4];
    String [] correctAnsList = new String[200];

    int totalQuestion;

//    String [] questionList = {"Which was the king who established rationing system?", "Who robbed the Arabs trade ship?",
//            "How many times did Mahmood Ghaznavi invade on India?", "From where did the Aryans come into South Asia?",
//            "What was called the head of Shaqq?", "What was called the head of the Diwan-e-Arz?",
//            "Whose daughter was Razia Sultan?", "Who were defeated by the Aryans in South Asia?",
//            "Which was the religion, whose founder was Mahaveer?", "From which race Khiljis belonged?"};
//
//
//
//    String [][] optionsForQuestions = {{"Qutubuddin Aibak", "Illtutmish", "Allauddin", "Muhammad Tughlaq"}, {"Makranis", "Robbers", "Pirates", "Fisher men"}, {"Fifteen", "Sixteen", "Seventeen", "Eighteen"},
//            {"Central Asia", "Middle East", "Greece", "China"}, {"Shiqq-Dar", "Soob-e-Dar", "Nazim", "Kotwal"}, {"Sadr-us-sadr", "Arz", "Sahib-e-Diwan", "Wazir"},
//            {"Qutubuddin Aibak", "Sultan Ghias ud Din", "Sultan Iltutmish", "Nasir ud Din"}, {"Sumerians", "Turks", "Dravidians", "Chinese"}, {"Hinduism", "Jainism", "Sikhism", "Buddhism"},
//            {"Turk", "Afghan", "Arab", "Mughal"}};
//
//    String [] correctAnsList = {"Allauddin", "Pirates", "Seventeen", "Central Asia", "Shiqq-Dar",
//            "Arz", "Sultan Iltutmish", "Dravidians", "Jainism", "Turk"};


    ArrayList<Integer> wrongAnsList = new ArrayList<>();
    ArrayList<Integer> indicesOfAskedQuestions = new ArrayList<>();
    int noOfWrongQuestions=0, currentQuestionIndex;
    int currentQuestionNo = 1,i=0;

    DBHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHelper(getApplicationContext());

        questionNo = findViewById(R.id.questionTitle);
        question = findViewById(R.id.questionField);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

//        insertDataToDB();
        loadDataFromDB();

        showQuestion();

    }

    private void insertDataToDB() {

        for(int i=0; i<questionList.length;i++)
        {
            Question q = new Question();
            Log.d("b0", String.valueOf(i));

            q.setQuestionvalue(questionList[i]);
            q.setOption1(optionsForQuestions[i][0]);
            q.setOption2(optionsForQuestions[i][1]);
            q.setOption3(optionsForQuestions[i][2]);
            q.setOption4(optionsForQuestions[i][3]);

            q.setCorrectOption(correctAnsList[i]);

            dbHandler.insert(q);
        }

        ArrayList<Question> qList = dbHandler.getAllData();

        Log.d("bb", String.valueOf(qList.size()));
    }

    private void loadDataFromDB() {
        ArrayList<Question> qList = dbHandler.getAllData();
        totalQuestion=qList.size();
        Log.d("b0", String.valueOf(qList.size()));

        for (int i=0; i<200 && i<qList.size();i++)
        {
            questionList[i] = qList.get(i).questionvalue;
            Log.d("b"+i, qList.get(i).questionvalue);

            optionsForQuestions[i][0] = qList.get(i).option1;
            optionsForQuestions[i][1] = qList.get(i).option2;
            optionsForQuestions[i][2] = qList.get(i).option3;
            optionsForQuestions[i][3] = qList.get(i).option4;

            correctAnsList[i] = qList.get(i).correctOption;
        }

    }

    protected void showQuestion()
    {
        if(indicesOfAskedQuestions.size() == totalQuestion)
        {
            showResult();
            return;
        }
        questionNo.setText("Question #" + Integer.toString(currentQuestionNo));
        currentQuestionNo++;

        Random rnd = new Random();
        ArrayList<String> optionList = new ArrayList<>();

        currentQuestionIndex = rnd.nextInt(totalQuestion);
        while (indicesOfAskedQuestions.contains(currentQuestionIndex))
        {
            currentQuestionIndex = rnd.nextInt(totalQuestion);
        }
        indicesOfAskedQuestions.add(currentQuestionIndex);

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

    protected void showResult()
    {
        //LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

        //View view = inflater.inflate(R.layout.result, null);

        setContentView(R.layout.result);
        TextView noOfWrongQ, noOfRightQ, wrongQuestionField, ansForWrongQuestionField;
        Button nextQuestionBtn = findViewById(R.id.nextBtn), exitBtn;
        nextQuestionBtn.setOnClickListener(this);

        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });

        noOfWrongQ = findViewById(R.id.countOfWrongAns);
        noOfRightQ = findViewById(R.id.countOfRightAns);

        noOfWrongQ.setText("Total wrong Answers: "+Integer.toString(noOfWrongQuestions));
        noOfRightQ.setText("Total Right Answers: "+(totalQuestion-noOfWrongQuestions));

        wrongQuestionField = findViewById(R.id.wrongQuestion);
        ansForWrongQuestionField = findViewById(R.id.correctAns);

        if(!wrongAnsList.isEmpty())
        {
            wrongQuestionField.setText(questionList[wrongAnsList.get(i)]);
            ansForWrongQuestionField.setText(correctAnsList[wrongAnsList.get(i)]);
            i++;
        }
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.option1:
                if (option1.getText() != correctAnsList[currentQuestionIndex])
                {
                    noOfWrongQuestions++;
                    wrongAnsList.add(currentQuestionIndex);
                }
                break;
            case R.id.option2:
                if (option2.getText() != correctAnsList[currentQuestionIndex])
                {
                    noOfWrongQuestions++;
                    wrongAnsList.add(currentQuestionIndex);
                }
                break;
            case R.id.option3:
                if (option3.getText() != correctAnsList[currentQuestionIndex])
                {
                    noOfWrongQuestions++;
                    wrongAnsList.add(currentQuestionIndex);
                }
                break;
            case R.id.option4:
                if (option4.getText() != correctAnsList[currentQuestionIndex])
                {
                    noOfWrongQuestions++;
                    wrongAnsList.add(currentQuestionIndex);
                }
                break;
            case R.id.nextBtn:
                if(i>=wrongAnsList.size())
                {
                    return;
                }
                TextView noOfWrongQ, noOfRightQ, wrongQuestionField, ansForWrongQuestionField;

                wrongQuestionField = findViewById(R.id.wrongQuestion);
                ansForWrongQuestionField = findViewById(R.id.correctAns);

                wrongQuestionField.setText(questionList[wrongAnsList.get(i)]);
                ansForWrongQuestionField.setText(correctAnsList[wrongAnsList.get(i)]);
                i++;
                return;
        }
        showQuestion();
    }


}