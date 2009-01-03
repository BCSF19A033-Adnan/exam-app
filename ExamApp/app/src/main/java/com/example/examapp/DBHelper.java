package com.example.examapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    SQLiteDatabase db;
    public DBHelper(Context context ) {
        super(context, "Quiz3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table questionPaper"+
                "(id INTEGER primary key autoincrement," +
                "question Text," +
                "answer1 Text," +
                "answer2 Text," +
                "answer3 Text," +
                "answer4 Text," +
                "correctAnswer Text)"
        );
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public boolean insert(Question q) {

        String question = q.getQuestionvalue(), answer1 = q.getOption1(),
                answer2 = q.getOption2(), answer3 = q.option3 ,answer4 = q.getOption4(),
                correctAnswer = q.getCorrectOption();

        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question);
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        contentValues.put("answer3", answer3);
        contentValues.put("answer4", answer4);
        contentValues.put("correctAnswer", correctAnswer);
        long result = mydb.insert("questionPaper", null, contentValues);
        mydb.close();
        if(result==-1)
        {
            return  false;
        }
        return true;
    }

    public ArrayList<Question> getAllData()
    {
        ArrayList<Question> list=new ArrayList<>();
        SQLiteDatabase mydb=this.getWritableDatabase()  ;
        Cursor cursor=mydb.rawQuery("select question,answer1,answer2,answer3,answer4,correctAnswer from questionPaper",null);
        if(cursor.moveToFirst())
        {
            do
            {
                Question q=new Question();
                q.setQuestionvalue(cursor.getString(0));
                q.setOption1(cursor.getString(1));
                q.setOption2(cursor.getString(2));
                q.setOption3(cursor.getString(3));
                q.setOption4(cursor.getString(4));
                q.setCorrectOption(cursor.getString(5));

                list.add(q);
            }  while(cursor.moveToNext());

        }
        cursor.close();
        mydb.close();
        return list;
    }

    void delDB()
    {
        db.execSQL("DROP Table questionPaper");
    }

}
