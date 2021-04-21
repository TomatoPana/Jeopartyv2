package com.mdlozano.jeopartyv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    int puntaje;
    TextView txtPuntaje;

    List<Boolean> ultimosMovimientos;
    List<String> categoriaUltimosMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtPuntaje = findViewById(R.id.txtPuntaje);

        db = new DatabaseHandler(this);
        createData();

        this.puntaje = 0;
        txtPuntaje.setText(String.format(getResources().getString(R.string.txtPuntaje), puntaje));

        ultimosMovimientos = new ArrayList<>();
        ultimosMovimientos.add(false);
        ultimosMovimientos.add(false);

        categoriaUltimosMovimientos = new ArrayList<>();
        categoriaUltimosMovimientos.add("1");
        categoriaUltimosMovimientos.add("1");

    }

    private void createData(){
        Question question1  = new Question(1,  "1",  "A", "B","C", "D", "1", "1","100");
        Question question2  = new Question(2,  "2",  "A", "B","C", "D", "1", "1","200");
        Question question3  = new Question(3,  "3",  "A", "B","C", "D", "1", "1","300");
        Question question4  = new Question(4,  "4",  "A", "B","C", "D", "1", "1","400");
        Question question5  = new Question(5,  "5",  "A", "B","C", "D", "1", "1","500");
        Question question6  = new Question(6,  "6",  "A", "B","C", "D", "1", "1","600");
        Question question7  = new Question(7,  "7",  "A", "B","C", "D", "1", "2","100");
        Question question8  = new Question(8,  "8",  "A", "B","C", "D", "1", "2","200");
        Question question9  = new Question(9,  "9",  "A", "B","C", "D", "1", "2","300");
        Question question10 = new Question(10, "10", "A", "B","C", "D", "1", "2","400");
        Question question11 = new Question(11, "11", "A", "B","C", "D", "1", "2","500");
        Question question12 = new Question(12, "12", "A", "B","C", "D", "1", "2","600");
        Question question13 = new Question(13, "13", "A", "B","C", "D", "1", "3","100");
        Question question14 = new Question(14, "14", "A", "B","C", "D", "1", "3","200");
        Question question15 = new Question(15, "15", "A", "B","C", "D", "1", "3","300");
        Question question16 = new Question(16, "16", "A", "B","C", "D", "1", "3","400");
        Question question17 = new Question(17, "17", "A", "B","C", "D", "1", "3","500");
        Question question18 = new Question(18, "18", "A", "B","C", "D", "1", "3","600");
        Question question19 = new Question(19, "19", "A", "B","C", "D", "1", "4","100");
        Question question20 = new Question(20, "20", "A", "B","C", "D", "1", "4","200");
        Question question21 = new Question(21, "21", "A", "B","C", "D", "1", "4","300");
        Question question22 = new Question(22, "22", "A", "B","C", "D", "1", "4","400");
        Question question23 = new Question(23, "23", "A", "B","C", "D", "1", "4","500");
        Question question24 = new Question(24, "24", "A", "B","C", "D", "1", "4","600");
        db.addQuestion(question1);
        db.addQuestion(question2);
        db.addQuestion(question3);
        db.addQuestion(question4);
        db.addQuestion(question5);
        db.addQuestion(question6);
        db.addQuestion(question7);
        db.addQuestion(question8);
        db.addQuestion(question9);
        db.addQuestion(question10);
        db.addQuestion(question11);
        db.addQuestion(question12);
        db.addQuestion(question13);
        db.addQuestion(question14);
        db.addQuestion(question15);
        db.addQuestion(question16);
        db.addQuestion(question17);
        db.addQuestion(question18);
        db.addQuestion(question19);
        db.addQuestion(question20);
        db.addQuestion(question21);
        db.addQuestion(question22);
        db.addQuestion(question23);
        db.addQuestion(question24);
    }

    public void onClickBtnCat(View view){
        Pattern p = Pattern.compile("^[a-zA-Z0-9.:/_-]*btnCat([0-9])_([0-9]{3})$");
        Matcher m = p.matcher(view.getResources().getResourceName(view.getId()));

        if(m.matches()) {
            String categoria = m.group(1); // 1,2,3,4
            String puntaje = m.group(2); //100,200,300
            int id = ((Integer.parseInt(categoria) - 1) * 6) + (Integer.parseInt(puntaje) / 100);
            Question question = db.getQuestion(id);

            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("Pregunta", question.getQuestion());
            intent.putExtra("Respuesta1", question.getAnswer1());
            intent.putExtra("Respuesta2", question.getAnswer2());
            intent.putExtra("Respuesta3", question.getAnswer3());
            intent.putExtra("Respuesta4", question.getAnswer4());
            intent.putExtra("RespuestaCorrecta", question.getRightAnswer());
            intent.putExtra("Categoria", question.getCategory());
            intent.putExtra("Puntaje", question.getScore());
            startActivityForResult(intent, 0);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String Categoria = data.getStringExtra("Categoria");
            String Puntaje = data.getStringExtra("Puntaje");
            String button = "com.mdlozano.jeopartyv2:id/btnCat" + Categoria + "_" + Puntaje;
            if(data.getBooleanExtra("RespuestaCorrecta", false)){
                puntaje = puntaje + Integer.parseInt(Puntaje);
                txtPuntaje.setText(String.format(getResources().getString(R.string.txtPuntaje), puntaje));
                int resource = getResources().getIdentifier(button, null, null);
                Button editButton = findViewById(resource);
                editButton.setBackgroundColor(getResources().getColor(R.color.teal_700));
                editButton.setEnabled(false);
                ultimosMovimientos.set(0, ultimosMovimientos.get(1));
                ultimosMovimientos.set(1, true);
                categoriaUltimosMovimientos.set(0, categoriaUltimosMovimientos.get(1));
                categoriaUltimosMovimientos.set(1, Categoria);

            } else {
                int resource = getResources().getIdentifier(button, null, null);
                Button editButton = findViewById(resource);
                if(ultimosMovimientos.contains(false) && categoriaUltimosMovimientos.contains(Categoria)){
                    editButton.setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
                    editButton.setEnabled(false);
                }
                ultimosMovimientos.set(0, ultimosMovimientos.get(1));
                ultimosMovimientos.set(1, false);
                categoriaUltimosMovimientos.set(0, categoriaUltimosMovimientos.get(1));
                categoriaUltimosMovimientos.set(1, Categoria);
            }
        }
    }

}