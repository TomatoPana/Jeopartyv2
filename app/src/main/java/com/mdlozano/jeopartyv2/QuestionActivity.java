package com.mdlozano.jeopartyv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionActivity extends AppCompatActivity {

    TextView txtPregunta;
    Button btnRespuesta1;
    Button btnRespuesta2;
    Button btnRespuesta3;
    Button btnRespuesta4;

    String respuestaCorrecta;
    String categoria;
    String puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        txtPregunta = findViewById(R.id.txtPregunta);
        btnRespuesta1 = findViewById(R.id.btnRespuesta1);
        btnRespuesta2 = findViewById(R.id.btnRespuesta2);
        btnRespuesta3 = findViewById(R.id.btnRespuesta3);
        btnRespuesta4 = findViewById(R.id.btnRespuesta4);

        Intent activityInfo = getIntent();

        this.txtPregunta.setText(activityInfo.getStringExtra("Pregunta"));
        this.btnRespuesta1.setText(activityInfo.getStringExtra("Respuesta1"));
        this.btnRespuesta2.setText(activityInfo.getStringExtra("Respuesta2"));
        this.btnRespuesta3.setText(activityInfo.getStringExtra("Respuesta3"));
        this.btnRespuesta4.setText(activityInfo.getStringExtra("Respuesta4"));
        this.respuestaCorrecta = activityInfo.getStringExtra("RespuestaCorrecta");
        this.categoria = activityInfo.getStringExtra("Categoria");
        this.puntaje = activityInfo.getStringExtra("Puntaje");

    }

    public void onClickResponse(View view){
        String element = view.getResources().getResourceName(view.getId());

        Intent sendData = new Intent(this, MainActivity.class);

        Pattern p = Pattern.compile("^[a-zA-Z0-9.:/_-]*btnRespuesta([0-9])$");
        Matcher m = p.matcher(element);

        if(m.matches()){
            if(m.group(1).equals(this.respuestaCorrecta)){
                Toast.makeText(this, "Respuesta Correcta", Toast.LENGTH_SHORT).show();
                sendData.putExtra("RespuestaCorrecta", true);
            } else {
                Toast.makeText(this, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
                sendData.putExtra("RespuestaCorrecta", false);
            }
            sendData.putExtra("Categoria", categoria);
            sendData.putExtra("Puntaje", puntaje);
            setResult(Activity.RESULT_OK, sendData);
            finish();
        }
    }
}