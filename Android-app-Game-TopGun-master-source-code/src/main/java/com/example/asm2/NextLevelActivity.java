package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.number.IntegerWidth;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NextLevelActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_again, btn_next, btn_menu2;
    ImageView star1, star2, star3;
    TextView getScore,passView,textView;
    Integer level_next, star;
    SharedPreferences sp;
    int lang_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);
        btn_again = findViewById(R.id.btn_again);
        btn_next = findViewById(R.id.btn_next);
        btn_menu2 = findViewById(R.id.btn_menu2);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        btn_next.setOnClickListener(this);
        btn_again.setOnClickListener(this);
        btn_menu2.setOnClickListener(this);
        getScore = findViewById(R.id.getScore);
        passView = findViewById(R.id.passView);
        textView = findViewById(R.id.textView);

        sp = getApplicationContext().getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        int score2 = sp.getInt("coinNum", 0);
        getScore.setText(String.valueOf(score2));
        level_next = sp.getInt("levelNum",0);
        star = sp.getInt("Heart",0);
        if (star.equals(1))
        {
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.emptystar);
            star3.setImageResource(R.drawable.emptystar);
        }
        if (star.equals(2))
        {
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.emptystar);
        }
        if (star.equals(3))
        {
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
        }

        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);

        changeLang();
    }

    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            btn_again.setText("Play again");
            btn_next.setText("Next LEVEL");
            btn_menu2.setText("MAIN MENU");
            passView.setText("You have passed the level!!!");
            textView.setText("Score");

        }else{
            btn_again.setText("再玩一次");
            btn_next.setText("下一關");
            btn_menu2.setText("回主頁");
            passView.setText("你成功過關啦！！！");
            textView.setText("分數");
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_again:
                Double flying_speed_acc=level_next*0.7; //min:100% max:280%
                //put the values
                Intent gameIntent = new Intent(getApplicationContext(),MainActivity.class);
                gameIntent.putExtra("Speed",flying_speed_acc);
                gameIntent.putExtra("levelNum",level_next);
                startActivity(gameIntent);
                break;
            case R.id.btn_next:
                SharedPreferences.Editor editor = sp.edit();
                Double flying_speed_acc2=(level_next+1)*0.7; //min:100% max:280%
                //put the values
                editor.putInt("levelNum",level_next+1);
                Log.d("Level", String.valueOf(level_next));
                editor.commit();
                Intent nextIntent = new Intent(NextLevelActivity.this,MainActivity.class);
                nextIntent.putExtra("Speed",flying_speed_acc2);
                startActivity(nextIntent);

                break;
            case R.id.btn_menu2:
                Intent MenuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(MenuIntent);
                break;
        }
    }

}