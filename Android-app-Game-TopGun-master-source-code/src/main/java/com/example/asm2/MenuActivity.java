package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button on1, on2,btn_shop,langbtn;

    int lang_num = 0,level;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        on1 = findViewById(R.id.on1);
        on2 = findViewById(R.id.on2);
        btn_shop = findViewById(R.id.tv1_shop);
        langbtn = findViewById(R.id.langbtn);

        on1.setOnClickListener(this);
        on2.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        langbtn.setOnClickListener(this);

        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        level = sp.getInt("levelNum",0);
        changeLang();

        if(level == 0 ){
            on1.setEnabled(false);
        }else{
            on1.setEnabled(true);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1_shop:
                Intent ShopIntent = new Intent(getApplicationContext(), ShopActivity.class);
                startActivity(ShopIntent);
                break;

            case R.id.on1:
                Intent gameIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(gameIntent);
                break;

            case R.id.on2:
                Intent On2Intent = new Intent(getApplicationContext(), LevelActivity.class);
                startActivity(On2Intent);
                break;
            case R.id.langbtn:
                SharedPreferences.Editor editor = sp.edit();
                if(lang_num == 0){
                    langbtn.setText("Eng");
                    lang_num = 1;
                    editor.putInt("lang_num",lang_num);
                    editor.commit();
                }else{
                    langbtn.setText("中");
                    lang_num = 0;
                    editor.putInt("lang_num",lang_num);
                    editor.commit();
                }
                changeLang();
        }
    }

    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            on1.setText("Continue");
            on2.setText("Levels");
            btn_shop.setText("Shop");
        }else{
            on1.setText("繼續");
            on2.setText("關卡");
            btn_shop.setText("商店");
        }
    }

}