package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connect3.R;

public class MainActivity extends AppCompatActivity {
//red:0,yellow:1,empty:2;
    int player=0;
    boolean active = true,tie=false;
    int[] gameStage = {2,2,2,2,2,2,2,2,2};

    int[][] winningstages = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;

        int tapped = Integer.parseInt( counter.getTag().toString());

        if(gameStage[tapped]==2 && active) {
            counter.setTranslationY(-1500);
            gameStage[tapped] = player;

            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);

            String winner = "";
            for (int[] winningstage : winningstages) {
                if (gameStage[winningstage[0]] == gameStage[winningstage[1]] && gameStage[winningstage[1]] == gameStage[winningstage[2]] && gameStage[winningstage[0]] != 2) {
                    if (player == 0)
                        winner = "red";
                    else
                        winner = "yellow";

                    active = false;


                    Button playAgain = (Button) findViewById(R.id.playAgain);

                    TextView winnerName = (TextView) findViewById(R.id.textView);

                    winnerName.setText(winner +" has won!!");
                    winnerName.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);


                }

            }
            tie= true;
            for (int i=0; i<gameStage.length; i++) {

                if(gameStage[i]==1||gameStage[i]==0)
                {tie =true;
                Log.i("fe","in true");}
                else {
                    tie = false;
                    Log.i("fe","in false");
                    break;
                }
            }

            if(tie  && active)
            {
                Button playAgain = (Button) findViewById(R.id.playAgain);

                TextView winnerName = (TextView) findViewById(R.id.textView);

                winnerName.setText("Its a Tie!!");
                winnerName.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }

        }

    }


    public void playAgain (View view)
    {
        Button playAgain = (Button) findViewById(R.id.playAgain);

        TextView winnerName = (TextView) findViewById(R.id.textView);

        winnerName.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        GridLayout myGridlayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<myGridlayout.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridlayout.getChildAt(i);

            counter.setImageDrawable(null);
        }
        player=0;
        active = true;
        for (int i=0; i<gameStage.length; i++) {

            gameStage[i] = 2;
        }
        //for(int i : gameStage)
        //{
            //gameStage[i] = 2;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
