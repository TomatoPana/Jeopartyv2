package com.mdlozano.jeopartyv2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int    DATABASE_VERSION    = 1;
    private static final String DATABASE_NAME       = "Jeopardy";
    private static final String TABLE_NAME          = "Questions";
    private static final String FIELD_ID            = "id";
    private static final String FIELD_QUESTION      = "question";
    private static final String FIELD_ANSWER_1      = "answer1";
    private static final String FIELD_ANSWER_2      = "answer2";
    private static final String FIELD_ANSWER_3      = "answer3";
    private static final String FIELD_ANSWER_4      = "answer4";
    private static final String FIELD_RIGHT_ANSWER  = "rightAnswer";
    private static final String FIELD_CATEGORY      = "category";
    private static final String FIELD_SCORE         = "score";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        String createTableSentence = "CREATE TABLE " + TABLE_NAME + "("
                + FIELD_ID + " INTEGER PRIMARY KEY,"
                + FIELD_QUESTION + " TEXT,"
                + FIELD_ANSWER_1 + " TEXT,"
                + FIELD_ANSWER_2 + " TEXT,"
                + FIELD_ANSWER_3 + " TEXT,"
                + FIELD_ANSWER_4 + " TEXT,"
                + FIELD_RIGHT_ANSWER + " TEXT,"
                + FIELD_CATEGORY + " TEXT,"
                + FIELD_SCORE + " TEXT)";

        db.execSQL(createTableSentence);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addQuestion(Question question){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_QUESTION, question.getQuestion());
        values.put(FIELD_ANSWER_1, question.getAnswer1());
        values.put(FIELD_ANSWER_2, question.getAnswer2());
        values.put(FIELD_ANSWER_3, question.getAnswer3());
        values.put(FIELD_ANSWER_4, question.getAnswer4());
        values.put(FIELD_RIGHT_ANSWER, question.getRightAnswer());
        values.put(FIELD_CATEGORY, question.getCategory());
        values.put(FIELD_SCORE, question.getScore());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    Question getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_NAME,
                new String[] {
                        FIELD_ID,
                        FIELD_QUESTION,
                        FIELD_ANSWER_1,
                        FIELD_ANSWER_2,
                        FIELD_ANSWER_3,
                        FIELD_ANSWER_4,
                        FIELD_RIGHT_ANSWER,
                        FIELD_CATEGORY,
                        FIELD_SCORE
        }, FIELD_ID + "=?", new String[] { String.valueOf(id)}, null, null, null, null );

        if(cursor != null){
            cursor.moveToFirst();
        }

        Question question = new Question(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8));

        db.close();
        return question;
    }
}
