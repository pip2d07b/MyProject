package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_retry, btn_menu;
    TextView getScore_over,passView,textView5,score_title;

    Integer level;

    int lang_num;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        btn_retry = findViewById(R.id.btn_retry);
        btn_menu = findViewById(R.id.btn_menu);
        passView = findViewById(R.id.passView);
        textView5 = findViewById(R.id.textView5);
        score_title = findViewById(R.id.score_title);



        btn_retry.setOnClickListener(this);
        btn_menu.setOnClickListener(this);

        getScore_over = findViewById(R.id.getScore_over);

        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);

        int score = sp.getInt("coinNum", 0);

        getScore_over.setText(String.valueOf(score));

        level = sp.getInt("levelNum",0);


        changeLang();
    }

    public void change(){
        passView.setText("You have pass level "+ level);
    }



    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            btn_retry.setText("Retry");
            btn_menu.setText("Main Menu");
            textView5.setText("Please Try Again!");
            score_title.setText("Score");

        }else{
            btn_retry.setText("再試一次");
            btn_menu.setText("返回主頁");
            textView5.setText("請再試一次");
            score_title.setText("分數");
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retry:
                //setting speed
                Double flying_speed_acc=level*0.7;//min:100% max:280%
                //put the values
                Intent gameIntent = new Intent(getApplicationContext(),MainActivity.class);
                gameIntent.putExtra("Speed",flying_speed_acc);
                gameIntent.putExtra("levelNum",level);
                startActivity(gameIntent);
                break;

            case R.id.btn_menu:
                Intent MenuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(MenuIntent);
                break;
        }
    }
}