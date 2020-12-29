package com.example.PinTu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class Gamecontent extends AppCompatActivity {
    Button exit;
    Button pause;

    private GameView gameview;

    private TextView tv_level, tv_time, tv_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamecontent);

        tv_level = (TextView) findViewById(R.id.tv_level);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_score = (TextView) findViewById(R.id.tv_score);
        gameview = (GameView) findViewById(R.id.gameview);
        Button start = (Button) findViewById(R.id.start);
        exit=(Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pause=(Button) findViewById(R.id.Pause);
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onPause();
            }
        });

        //显示时间
        gameview.setTimeEnabled(true);

        gameview.setOnGamemListener(new GameView.GamePintuListener() {
            @Override
            public void nextLevel(final int nextLevel) {

                new AlertDialog.Builder(Gamecontent.this).setTitle("拼图完成").setMessage("点击进入下一关").setPositiveButton("下一关", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameview.nextLevel();
                        tv_level.setText("当前关卡：" + nextLevel);
                    }
                }).show();
            }

            @Override
            public void timechanged(int time) {
                //设置时间
                tv_time.setText("倒计时：" + time);
            }

            @Override
            public void gameOver() {
                new AlertDialog.Builder(Gamecontent.this).setTitle("游戏结束").setMessage("很遗憾没有成功完成！").setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameview.restartGame();
                    }
                }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameview.pauseGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameview.resumeGame();
    }


    //记分的实现：
    public void clearScore(){
        score=0;
        showScore();
    }
    public void showScore(){
        tv_score.setText(score+"");
    }
    public void addScore(int s){
        score+=s;
        showScore();

    }
    private int score=0;
}
