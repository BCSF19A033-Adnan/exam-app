package com.example.examapp;

public class Question {
    String questionvalue;
    String option1;
    String option2;
    String option3;
    String option4;
    String correctOption;

    public Question(String questionvalue, String option1, String option2, String option3, String option4, String correctOption) {
        this.questionvalue = questionvalue;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
    }

    public Question() {
    }


    public String getQuestionvalue() {
        return questionvalue;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setQuestionvalue(String questionvalue) {
        this.questionvalue = questionvalue;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
}
