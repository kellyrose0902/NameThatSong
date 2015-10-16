package com.example.kelly.namethatsong;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Main Activity";
    private Timer timer;
    private DonutProgress donut;
    private Button play_button;
    private MediaPlayer mp;
    private boolean playFlag;
    private ProgressBar progress;
    private  String genre;
    private List<String> fileNameList;
    private List<String> quizList;
    private String correctAnswer;
    private int rightGuess;
    private int totalQuestion = 4;
    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        genre = intent.getStringExtra("type");

        fileNameList = new ArrayList<String>();
        quizList = new ArrayList<String>();
        random = new Random();

        Toast.makeText(this,genre,Toast.LENGTH_SHORT).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progress=(ProgressBar)findViewById(R.id.progress);
        progress.setProgress(5);

        playFlag = true;
        donut = (DonutProgress)findViewById(R.id.TimeCircle);
        play_button = (Button)findViewById(R.id.play_button);
        play_button.setOnClickListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onPause(){
        super.onPause();
        stopSong();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetQuiz(){

        AssetManager assets = getAssets();
        fileNameList.clear();
        try{
            String[]paths = assets.list(genre);
            for(String path : paths){
                fileNameList.add(path.replace(".mp3",""));
            }
        }
        catch (IOException e){
            Log.e(TAG,"Error loading music file names",e);
        }
        quizList.clear();
        rightGuess = 0;

        int songCounter = 1;
        int numberOfSongs = fileNameList.size();
        while (songCounter<=totalQuestion){
            int randomIndex = random.nextInt(numberOfSongs);
            String filename = fileNameList.get(randomIndex);
            if(!quizList.contains(filename)){
                quizList.add(filename);
                ++songCounter;
            }

            loadNextSong();
        }

    }
    private void loadNextSong(){


    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){

        case R.id.play_button:

            if(playFlag) {
                playFlag = false;
                getSong(R.raw.not_end_well);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                donut.setProgress(donut.getProgress() + 1);
                                if (donut.getProgress() == 100) {
                                    timer.cancel();
                                    stopSong();
                                }
                            }
                        });
                    }
                }, 1, 250);
            }
            break;



    }
    }
    void stopSong(){
        if(mp!=null)
        {
            if(mp.isPlaying())
                mp.stop();

            mp.release();//It requires again setDataSource for player object.

        }
    }
    void getSong(int id){
        mp = MediaPlayer.create(this,id);
        mp.seekTo(40000);

        mp.start();
    }
}
