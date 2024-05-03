package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {


    TextView btn_shop,btn_custom,tv_infinite,tv_scoremode ,tv_timemode;

    int lang_num;




    TextView[] levelsView=new TextView[101];

    int[] tv_ids={-1,R.id.l1,R.id.l2,R.id.l3,R.id.l4,R.id.l5,R.id.l6,R.id.l7,R.id.l8,R.id.l9,R.id.l10,R.id.l11,R.id.l12,R.id.l13,R.id.l14,R.id.l15,R.id.l16,R.id.l17,R.id.l18,R.id.l19,R.id.l20,R.id.l21,R.id.l22,R.id.l23,R.id.l24,R.id.l25,
            R.id.l26,R.id.l27,R.id.l28,R.id.l29,R.id.l30,R.id.l31,R.id.l32,R.id.l33,R.id.l34,R.id.l35,R.id.l36,R.id.l37,R.id.l38,R.id.l39,R.id.l40,R.id.l41,R.id.l42,R.id.l43,R.id.l44,R.id.l45,R.id.l46,R.id.l47,R.id.l48,R.id.l49,R.id.l50,
            R.id.l51,R.id.l52,R.id.l53,R.id.l54,R.id.l55,R.id.l56,R.id.l57,R.id.l58,R.id.l59,R.id.l60,R.id.l61,R.id.l62,R.id.l63,R.id.l64,R.id.l65,R.id.l66,R.id.l67,R.id.l68,R.id.l69,R.id.l70,R.id.l71,R.id.l72,R.id.l73,R.id.l74,R.id.l75,
            R.id.l76,R.id.l77,R.id.l78,R.id.l79,R.id.l80,R.id.l81,R.id.l82,R.id.l83,R.id.l84,R.id.l85,R.id.l86,R.id.l87,R.id.l88,R.id.l89,R.id.l90,R.id.l91,R.id.l92,R.id.l93,R.id.l94,R.id.l95,R.id.l96,R.id.l97,R.id.l98,R.id.l99,R.id.l100};

    SharedPreferences sp;

    public Integer level_Num;
    Integer player_max_levels_scoremode=1,player_max_levels_timemode=1;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        initObject();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.tv1_shop:
                shopclicked();
                break;
            case R.id.tv_custom:
                goDraw();
                break;
            case R.id.btn_back:
                goBack();
                Log.d("backLevel","back");
                break;
            case R.id.tv_infinite:
                goInfinite();
                break;
            case R.id.l1:
            case R.id.l2:
            case R.id.l3:
            case R.id.l4:
            case R.id.l5:
            case R.id.l6:
            case R.id.l7:
            case R.id.l8:
            case R.id.l9:
            case R.id.l10:
            case R.id.l11:
            case R.id.l12:
            case R.id.l13:
            case R.id.l14:
            case R.id.l15:
            case R.id.l16:
            case R.id.l17:
            case R.id.l18:
            case R.id.l19:
            case R.id.l20:
            case R.id.l21:
            case R.id.l22:
            case R.id.l23:
            case R.id.l24:
            case R.id.l25:
            case R.id.l26:
            case R.id.l27:
            case R.id.l28:
            case R.id.l29:
            case R.id.l30:
            case R.id.l31:
            case R.id.l32:
            case R.id.l33:
            case R.id.l34:
            case R.id.l35:
            case R.id.l36:
            case R.id.l37:
            case R.id.l38:
            case R.id.l39:
            case R.id.l40:
            case R.id.l41:
            case R.id.l42:
            case R.id.l43:
            case R.id.l44:
            case R.id.l45:
            case R.id.l46:
            case R.id.l47:
            case R.id.l48:
            case R.id.l49:
            case R.id.l50:
                try {
                    SharedPreferences.Editor editor = sp.edit();

                    TextView selected_level=findViewById(view.getId());
                    level_Num = Integer.parseInt(selected_level.getText().toString());
                    goLevel(level_Num);

                    editor.putInt("levelNum",level_Num);
                    editor.commit();

                } catch (Exception e) {
                    Log.e("Oh no", e.getMessage(),e);
                }
                break;
            case R.id.l51:
            case R.id.l52:
            case R.id.l53:
            case R.id.l54:
            case R.id.l55:
            case R.id.l56:
            case R.id.l57:
            case R.id.l58:
            case R.id.l59:
            case R.id.l60:
            case R.id.l61:
            case R.id.l62:
            case R.id.l63:
            case R.id.l64:
            case R.id.l65:
            case R.id.l66:
            case R.id.l67:
            case R.id.l68:
            case R.id.l69:
            case R.id.l70:
            case R.id.l71:
            case R.id.l72:
            case R.id.l73:
            case R.id.l74:
            case R.id.l75:
            case R.id.l76:
            case R.id.l77:
            case R.id.l78:
            case R.id.l79:
            case R.id.l80:
            case R.id.l81:
            case R.id.l82:
            case R.id.l83:
            case R.id.l84:
            case R.id.l85:
            case R.id.l86:
            case R.id.l87:
            case R.id.l88:
            case R.id.l89:
            case R.id.l90:
            case R.id.l91:
            case R.id.l92:
            case R.id.l93:
            case R.id.l94:
            case R.id.l95:
            case R.id.l96:
            case R.id.l97:
            case R.id.l98:
            case R.id.l99:
            case R.id.l100:
                try {
                    SharedPreferences.Editor editor = sp.edit();

                    TextView selected_level=findViewById(view.getId());
                    level_Num = Integer.parseInt(selected_level.getText().toString());
                    goLevel(level_Num);

                    editor.putInt("levelNum",level_Num+50);
                    editor.commit();

                } catch (Exception e) {
                    Log.e("Oh no", e.getMessage(),e);
                }
        }
    }

    public void shopclicked(){
        Intent shopIntent = new Intent(LevelActivity.this, ShopActivity.class);
        startActivity(shopIntent);
    }

    public void goLevel(Integer level) {
        //setting speed
        Double flying_speed_acc;
        if(level==0){
            flying_speed_acc = 1.0;
        }else if(level<50){
            flying_speed_acc = level * 0.7; // 1lv+ 0.7 speed
        }else {
            flying_speed_acc=(level-50)*0.7;
        }
        //put the values
        Intent gameIntent = new Intent(LevelActivity.this,MainActivity.class);
        gameIntent.putExtra("Speed",flying_speed_acc);
        startActivity(gameIntent);
    }

    public void goInfinite(){
        Intent gameIntent = new Intent(LevelActivity.this,MainActivity.class);
        gameIntent.putExtra("Speed",1);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("levelNum",0);
        editor.commit();

        startActivity(gameIntent);
    }

    public void goDraw() {
        Intent DrawIntent = new Intent(LevelActivity.this, canvasActivity.class);
        startActivity(DrawIntent);
    }

    public void goBack(){
        Intent LastIntent = new Intent(LevelActivity.this,MenuActivity.class);
        startActivity(LastIntent);
    }


    public void initObject(){
        levelsView[0]=null;

        for(int i=1;i<=100;i++){
            levelsView[i]=findViewById(tv_ids[i]);
        }

        tv_infinite=findViewById(R.id.tv_infinite);
        tv_infinite.setOnClickListener(this);

        btn_shop=findViewById(R.id.tv1_shop);
        btn_custom=findViewById(R.id.tv_custom);
        tv_scoremode = findViewById(R.id.tv_scoremode);
        tv_timemode = findViewById(R.id.tv_timemode);

        btn_shop.setOnClickListener(this);
        btn_custom.setOnClickListener(this);

        btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        //sp
        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        Integer unlocklevels_scoremode=sp.getInt("unlockLevelScore",1);
        Integer unlocklevels_timemode=sp.getInt("unlockLevelTime",1);
//        player_max_levels_scoremode=Math.max(unlocklevels_scoremode,player_max_levels_scoremode);
//        player_max_levels_timemode=Math.max(unlocklevels_timemode,player_max_levels_timemode)+50;
        player_max_levels_scoremode=100;
        player_max_levels_timemode=100;
        for(int i=50;i>player_max_levels_scoremode;i--){
            levelsView[i].setBackgroundResource(R.drawable.level_locked_ic);
        }
        for(int i=1;i<=player_max_levels_scoremode;i++){
            levelsView[i].setOnClickListener(this);
        }
        for(int i=100;i>player_max_levels_timemode;i--){
            levelsView[i].setBackgroundResource(R.drawable.level_locked_ic);
        }
        for(int i=51;i<=player_max_levels_timemode;i++){
            levelsView[i].setOnClickListener(this);
        }
        for(int i=51;i<=100;i++){
            levelsView[i].setText(Integer.toString(i-50));
        }
        changeLang();
    }

    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            btn_custom.setText("Draw");
            btn_shop.setText("Shop");
            tv_infinite.setText("Infinite");
            tv_scoremode.setText("Score Mode");
            tv_timemode.setText("Time Mode");
        }else{
            btn_custom.setText("繪畫");
            btn_shop.setText("商店");
            tv_infinite.setText("  無限");
            tv_scoremode.setText("分數模式");
            tv_timemode.setText("時間模式");
        }
    }
}

//