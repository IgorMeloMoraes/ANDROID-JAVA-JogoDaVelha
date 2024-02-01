package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView txtHeader;

    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_O;

    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHeader = findViewById(R.id.header_text);
        txtHeader.setText("O Turn");

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Logica para presionar o botão

        if (!isGameActive)
            return;


        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        if (filledPos[clickedTag] != -1){
            return;
        }

        filledPos[clickedTag] = activePlayer;

        if (activePlayer == PLAYER_O){
            clickedBtn.setText("O");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_blue_bright));
            activePlayer = PLAYER_X;
            txtHeader.setText("X Turno");
        }else {
            clickedBtn.setText("X");
            clickedBtn.setBackground(getDrawable(android.R.color.holo_orange_light));
            activePlayer = PLAYER_O;
            txtHeader.setText("O Turno");
        }
        
        checkForWin();

    }

    private void checkForWin() {

        // Verifica quem é o vencedor e mostra na tela

        int[][] winnerPos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        for (int i = 0; i < 8; i++){
            int val0 = winnerPos[i][0];
            int val1 = winnerPos[i][1];
            int val2 = winnerPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if (filledPos[val0] != -1){

                    isGameActive = false;
                    // Declare o Vencedor
                    if (filledPos[val0] == PLAYER_O);
                    showDialog("winner O");
                }else {
                    showDialog("winner X");
                }


            }
        }
    }

    private void showDialog(String winnerText){
        new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Reset Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        }).show();
    }

    private void restartGame(){
        activePlayer = PLAYER_O;
        txtHeader.setText("O Turn");
        filledPos = new int[] {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        btn1.setBackground(getDrawable(android.R.color.darker_gray));
        btn2.setBackground(getDrawable(android.R.color.darker_gray));
        btn3.setBackground(getDrawable(android.R.color.darker_gray));
        btn4.setBackground(getDrawable(android.R.color.darker_gray));
        btn5.setBackground(getDrawable(android.R.color.darker_gray));
        btn6.setBackground(getDrawable(android.R.color.darker_gray));
        btn7.setBackground(getDrawable(android.R.color.darker_gray));
        btn8.setBackground(getDrawable(android.R.color.darker_gray));
        btn9.setBackground(getDrawable(android.R.color.darker_gray));

        isGameActive = true;
    }

}