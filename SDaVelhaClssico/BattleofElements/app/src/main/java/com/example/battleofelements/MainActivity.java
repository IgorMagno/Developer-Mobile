package com.example.battleofelements;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startGame(View view) {

        Toast.makeText(MainActivity.this, "Click ok", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_board);

    }
}
