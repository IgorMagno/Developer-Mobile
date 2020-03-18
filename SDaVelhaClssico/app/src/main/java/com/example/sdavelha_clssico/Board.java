package com.example.sdavelha_clssico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Board<clicks> extends AppCompatActivity {
    Button[] idCellBoard = new Button[9];
    String[] textCell = new String[9];
    String PlayWinner = "";
    String turnPlayer = "X";
    boolean Winner = false;
    int bootPlays = 0;
    int countClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        //
        Winner = false;
        PlayWinner = "";
        turnPlayer = "X";
        bootPlays = 0;
        countClicks = 0;
        InitGame();

        idCellBoard[0] = findViewById(R.id.cell0x0);
        idCellBoard[1] = findViewById(R.id.cell0x1);
        idCellBoard[2] = findViewById(R.id.cell0x2);
        idCellBoard[3] = findViewById(R.id.cell1x0);
        idCellBoard[4] = findViewById(R.id.cell1x1);
        idCellBoard[5] = findViewById(R.id.cell1x2);
        idCellBoard[6] = findViewById(R.id.cell2x0);
        idCellBoard[7] = findViewById(R.id.cell2x1);
        idCellBoard[8] = findViewById(R.id.cell2x2);

        for(int i = 0; i < 9; i++){
            idCellBoard[i].setOnClickListener(clickListener);
        }
        //
    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button cellClicked = (Button) v;
            TextTransfer();

            if (cellClicked.getText() == "" && turnPlayer.equals("X") && !Winner) {
                cellClicked.setText("X");
                cellClicked.setClickable(false);
                countClicks++;
                turnPlayer = "O";
                updateRunPlaySet();
            }

            Boot();
            EndGame();
        }
    };

    public void PassTheThurn(View view) {
        turnPlayer = "O";
        updateRunPlaySet();
        Boot();
        EndGame();
    }

    private void EndGame() {
        TextTransfer();
        TheWinner();
        if (!Winner && PlayWinner.equals("VELHA")) {
            for (int i = 0; i < 9; i++) {
                idCellBoard[i].setEnabled(false);
                idCellBoard[i].setClickable(false);
            }
            Toast.makeText(getApplicationContext(),"DEU VELHA",Toast.LENGTH_SHORT).show();
        }
        if (Winner) {
            for (int i = 0; i < 9; i++) {
                idCellBoard[i].setEnabled(false);
                idCellBoard[i].setClickable(false);
            }
            Toast.makeText(getApplicationContext(),"Vencedor "+PlayWinner,Toast.LENGTH_SHORT).show();
        }
    }

    private void TheWinner() {
        TextTransfer();

        if(countClicks <= 9){
            for (int i = 1; i < 8; i++) {

                if (((i == 1) || (i == 4) || (i == 7)) && (textCell[i].equals(textCell[i - 1])) && (textCell[i].equals(textCell[i + 1])) && (textCell[i].equals("X") || textCell[i].equals("O"))) {
                    Winner = true;
                    PlayWinner = textCell[i];
                    break;
                } else {
                    if (((i == 3) || (i == 4) || (i == 5)) && (textCell[i].equals(textCell[i - 3])) && (textCell[i].equals(textCell[i + 3])) && (textCell[i].equals("X") || textCell[i].equals("O"))) {
                        Winner = true;
                        PlayWinner = textCell[i];
                        break;
                    } else {
                        if ((i == 4) && (textCell[i].equals(textCell[i - 2])) && (textCell[i].equals(textCell[i + 2])) && (textCell[i].equals("X") || textCell[i].equals("O"))) {
                            Winner = true;
                            PlayWinner = textCell[i];
                            break;
                        } else {
                            if ((i == 4) && (textCell[i].equals(textCell[i - 4])) && (textCell[i].equals(textCell[i + 4])) && (textCell[i].equals("X") || textCell[i].equals("O"))) {
                                Winner = true;
                                PlayWinner = textCell[i];
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(!Winner && countClicks == 9) {
            PlayWinner = "VELHA";
        }
    }

    private void Boot() {
        TextTransfer();
        TheWinner();
        if (!Winner) {

            for (int i = 8; i > 0; i = i-2) {
                if (textCell[i].equals("") && turnPlayer.equals("O") && bootPlays < 2) {
                    idCellBoard[i].setText("O");
                    countClicks++;
                    bootPlays++;
                    turnPlayer = "X";
                    updateRunPlaySet();
                    break;
                } else {
                    if(textCell[4].equals("") && turnPlayer.equals("O") && bootPlays == 2){
                        idCellBoard[4].setText("O");
                        countClicks++;
                        bootPlays++;
                        turnPlayer = "X";
                        updateRunPlaySet();
                        break;
                    }else {

                        for (int f = 0; f < 9; f++){
                            if(textCell[f].equals("") && turnPlayer.equals("O")) {
                                idCellBoard[f].setText("O");
                                countClicks++;
                                bootPlays++;
                                turnPlayer = "X";
                                updateRunPlaySet();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void TextTransfer() {


        for (int i = 0; i < 9; i++) {
            textCell[i] = (String) idCellBoard[i].getText();
        }
    }

    private void InitGame() {
        for (int i = 0; i < 9; i++) {
            textCell[i] = "";
        }
    }

    public void updateRunPlaySet(){
        TextView runPlayer = (TextView)findViewById(R.id.view_rum_player);
        runPlayer.setText(turnPlayer);
    }

}