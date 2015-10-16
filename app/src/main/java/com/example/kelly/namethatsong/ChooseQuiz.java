package com.example.kelly.namethatsong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ChooseQuiz extends AppCompatActivity implements View.OnClickListener {
    private CardView popCard;
    private CardView rockCard;
    private CardView disneyCard;
    private CardView instruCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        popCard = (CardView)findViewById(R.id.cv1);
        rockCard = (CardView)findViewById(R.id.cv2);
        disneyCard = (CardView)findViewById(R.id.cv3);
        instruCard = (CardView)findViewById(R.id.cv4);

        popCard.setOnClickListener(this);
        rockCard.setOnClickListener(this);
        disneyCard.setOnClickListener(this);
        instruCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent playQuiz = new Intent(this,MainActivity.class);

        switch (v.getId()){

           case R.id.cv1:
               playQuiz.putExtra("type","pop");
               startActivity(playQuiz);
               break;
            case R.id.cv2:
                playQuiz.putExtra("type","rock");
                startActivity(playQuiz);
                break;
            case R.id.cv3:
                playQuiz.putExtra("type","disney");
                startActivity(playQuiz);
                break;
            case R.id.cv4:
                playQuiz.putExtra("type","instru");
                startActivity(playQuiz);
                break;

        }
    }
}
