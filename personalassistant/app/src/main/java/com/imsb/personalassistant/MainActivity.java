package com.imsb.personalassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnFala;
    TextView textTextoCapturado;

    private final int ID_TEXTO_PARA_VOZ = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFala = (Button) findViewById(R.id.escutar);
        textTextoCapturado = (TextView) findViewById(R.id.textoCapturado);

        btnFala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVoz = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                iVoz.putExtra(RecognizerIntent.EXTRA_PROMPT, "Escutando");

                try {
                    startActivityForResult(iVoz, ID_TEXTO_PARA_VOZ);
                } catch (ActivityNotFoundException a){
                    Toast.makeText(getApplicationContext(), "Dispositivo não compativel", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int id, int resultCodeID, Intent dados) {
        super.onActivityResult(id, resultCodeID, dados);

        switch (id) {

            case ID_TEXTO_PARA_VOZ:
                if (resultCodeID == RESULT_OK && null != dados){

                    ArrayList<String> result = dados.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String ditado = result.get(0);
                    Toast.makeText(getApplicationContext(), ditado, Toast.LENGTH_SHORT).show();
                    textTextoCapturado.setText(ditado);
                }
                break;
        }
    }
}
