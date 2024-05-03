package com.example.asm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton shopBackbtn,shopPlane1,shopPlane2,shopPlane3,shopPlane4,shopPlane5;

    private TextView shopCoinNum;

    private ImageView applyPlaneView;

    private Button shopPlanebtn1,shopPlanebtn2,shopPlanebtn3,shopPlanebtn4,shopPlanebtn5;

    private boolean buyStatus_1 = true , buyStatus_2 = false, buyStatus_3 = false ,buyStatus_4 = false,buyStatus_5 = true;
    private boolean applyStatus_1 = false, applyStatus_2 = false, applyStatus_3 = false, applyStatus_4 = false, applyStatus_5 = false;

    private boolean tooglestutas_1 = true, tooglestutas_2 = true, tooglestutas_3 = true, tooglestutas_4 = true, tooglestutas_5 = true;

    private int coin_num, newCoin,totalCoin;
    public int applyNum = 0;
    int lang_num;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init_();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shopBackbtn:
                SharedPreferences.Editor editor = sp.edit();

                Intent shopintent = new Intent(getApplicationContext(),LevelActivity.class);
                startActivity(shopintent);

                editor.putInt("oldCoinNum",totalCoin);
                editor.commit();

                break;
            case R.id.shopPlane1:
                stuffStatus_2(buyStatus_1, applyStatus_1 ,shopPlanebtn1);
                togglePlane1();
                break;
            case R.id.shopPlanebtn1:
                apply1();
                break;
            case R.id.shopPlane2:
                stuffStatus_2(buyStatus_2, applyStatus_2 ,shopPlanebtn2);
                togglePlane2();
                break;
            case R.id.shopPlanebtn2:
                buyStatus_2 = sp.getBoolean("buyStatus_2",false);
                if(!buyStatus_2){
                    buyPlane2();
                }
                apply2();
                break;
            case R.id.shopPlane3:
                stuffStatus_3(buyStatus_3, applyStatus_3 ,shopPlanebtn3);
                togglePlane3();
                break;
            case R.id.shopPlanebtn3:
                buyStatus_3 = sp.getBoolean("buyStatus_3",false);
                if(!buyStatus_3){
                    buyPlane3();
                }
                apply3();
                break;
            case R.id.shopPlane4:
                stuffStatus_4(buyStatus_4, applyStatus_4 ,shopPlanebtn4);
                togglePlane4();
                break;
            case R.id.shopPlanebtn4:
                buyStatus_4 = sp.getBoolean("buyStatus_4",false);
                if(!buyStatus_4){
                    buyPlane4();
                }
                apply4();
                break;
            case R.id.shopPlanebtn5:
                if(!buyStatus_5){
                    buyPlane4();
                }
                apply5();
                break;
        }
    }


    public void init_(){
        shopBackbtn = findViewById(R.id.shopBackbtn);
        shopPlane1 = findViewById(R.id.shopPlane1);
        shopPlanebtn1 = findViewById(R.id.shopPlanebtn1);
        shopPlane2 = findViewById(R.id.shopPlane2);
        shopPlane3 = findViewById(R.id.shopPlane3);
        shopPlane4 = findViewById(R.id.shopPlane4);
        shopPlanebtn2 = findViewById(R.id.shopPlanebtn2);
        shopPlanebtn3 = findViewById(R.id.shopPlanebtn3);
        shopPlanebtn4 = findViewById(R.id.shopPlanebtn4);
        shopPlanebtn5 = findViewById(R.id.shopPlanebtn5);
        applyPlaneView = findViewById(R.id.applyPlaneView);
        shopCoinNum = findViewById(R.id.shopCoinNum);

        shopBackbtn.setOnClickListener(this);
        shopPlane1.setOnClickListener(this);
        shopPlanebtn1.setOnClickListener(this);
        shopPlane2.setOnClickListener(this);
        shopPlane3.setOnClickListener(this);
        shopPlane4.setOnClickListener(this);
        shopPlanebtn2.setOnClickListener(this);
        shopPlanebtn3.setOnClickListener(this);
        shopPlanebtn4.setOnClickListener(this);
        shopPlanebtn5.setOnClickListener(this);

        //SP
        sp = getSharedPreferences("ShopSp", Context.MODE_PRIVATE);
        newCoin = sp.getInt("coinNum",0);
        lang_num = sp.getInt("lang_num",0);

        setCoinNum();
        changeLang();
    }

    public void changeLang(){
        if(lang_num == 0){
            shopPlanebtn5.setText("Apply Customized Plane");
        }else{
            shopPlanebtn5.setText("應用你的飛機");
        }
    }


    public void stuffStatus_2(Boolean buyStatus, Boolean applyStatus, Button btn){
        SharedPreferences.Editor editor = sp.edit();
        boolean  buyStatus_2_old = sp.getBoolean("buyStatus_2",false);

        if(buyStatus || buyStatus_2_old == true){
            if(lang_num == 0){
                btn.setText("Apply");
            }else{
                btn.setText("應用");
            }
        }else{
            if(lang_num == 0){
                btn.setText("Buy");
            }else{
                btn.setText("購買");
            }
        }
        btn.setVisibility(View.VISIBLE);
    }
    public void stuffStatus_3(Boolean buyStatus, Boolean applyStatus, Button btn){
        SharedPreferences.Editor editor = sp.edit();
        boolean  buyStatus_3_old = sp.getBoolean("buyStatus_3",false);

        if(buyStatus || buyStatus_3_old == true){
            if(lang_num == 0){
                btn.setText("Apply");
            }else{
                btn.setText("應用");
            }
        }else{
            if(lang_num == 0){
                btn.setText("Buy");
            }else{
                btn.setText("購買");
            }
        }
        btn.setVisibility(View.VISIBLE);
    }
    public void stuffStatus_4(Boolean buyStatus, Boolean applyStatus, Button btn){
        SharedPreferences.Editor editor = sp.edit();
        boolean  buyStatus_4_old = sp.getBoolean("buyStatus_4",false);

        if(buyStatus || buyStatus_4_old == true){
            if(lang_num == 0){
                btn.setText("Apply");
            }else{
                btn.setText("應用");
            }
        }else{
            if(lang_num == 0){
                btn.setText("Buy");
            }else{
                btn.setText("購買");
            }
        }
        btn.setVisibility(View.VISIBLE);
    }

    public void buyPlane2(){
        SharedPreferences.Editor editor = sp.edit();

        if(!buyStatus_2 && totalCoin >= 100){
            totalCoin = totalCoin - 100;
            shopCoinNum.setText(Integer.toString(totalCoin));
            buyStatus_2 = true;
            stuffStatus_2(buyStatus_2, applyStatus_2 ,shopPlanebtn2);
            //sp
            editor.putBoolean("buyStatus_2",buyStatus_2);
            editor.commit();
            Log.d("b2",""+buyStatus_2);

        }else{
            if(lang_num == 0){
                Toast.makeText(getApplicationContext(),"Not enought menoy", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"金幣不足", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void buyPlane3(){
        SharedPreferences.Editor editor = sp.edit();

        if(!buyStatus_3 && totalCoin >= 300){
            totalCoin = totalCoin - 300;
            shopCoinNum.setText(Integer.toString(totalCoin));
            buyStatus_3 = true;
            stuffStatus_3(buyStatus_3, applyStatus_3 ,shopPlanebtn3);
            //sp
            editor.putBoolean("buyStatus_3",buyStatus_3);
            editor.commit();
            Log.d("b2",""+buyStatus_3);
            Log.d("buyplane3","pass and buy");

        }else{
            if(lang_num == 0){
                Toast.makeText(getApplicationContext(),"Not enought menoy", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"金幣不足", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void buyPlane4(){
        SharedPreferences.Editor editor = sp.edit();

        if(!buyStatus_4 && totalCoin >= 420){
            totalCoin = totalCoin - 420;
            shopCoinNum.setText(Integer.toString(totalCoin));
            buyStatus_4 = true;
            stuffStatus_4(buyStatus_4, applyStatus_4 ,shopPlanebtn4);
            //sp
            editor.putBoolean("buyStatus_4",buyStatus_4);
            editor.commit();
            Log.d("b2",""+buyStatus_4);

        }else{
            if(lang_num == 0){
                Toast.makeText(getApplicationContext(),"Not enought menoy", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"金幣不足", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void apply1(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_1){
            applyPlaneView.setBackgroundResource(R.drawable.plane);
            editor.putInt("applyNum",0);
            editor.commit();
        }
    }

    public void apply2(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_2){
            applyPlaneView.setBackgroundResource(R.drawable.plane2);
            editor.putInt("applyNum",2);
            editor.commit();
        }
    }
    public void apply3(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_3){
            applyPlaneView.setBackgroundResource(R.drawable.plane3);
            editor.putInt("applyNum",4);
            editor.commit();
            Log.d("buying","test");
        }
    }
    public void apply4(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_4){
            applyPlaneView.setBackgroundResource(R.drawable.plane4);
            editor.putInt("applyNum",6);
            editor.commit();
            Log.d("buying","test");
        }
    }

    public void apply5(){
        SharedPreferences.Editor editor = sp.edit();
        if(buyStatus_5){
            File imgFile = new  File("/storage/emulated/0/Android/data/com.example.asm2/files/Pictures/plane_custom.jpg");

            if(imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                Drawable mDrawable = new BitmapDrawable(getResources(), myBitmap);

                applyPlaneView.setBackground(mDrawable);
                editor.putInt("applyNum", 8);
                editor.commit();
            }
            else {
                Toast.makeText(getApplicationContext(), "Have not draw your own plane yet", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void togglePlane1(){
        if(tooglestutas_1){
            tooglestutas_1 = false;
            shopPlanebtn1.setVisibility(View.VISIBLE);
            shopPlanebtn2.setVisibility(View.INVISIBLE);
            shopPlanebtn3.setVisibility(View.INVISIBLE);
            shopPlanebtn4.setVisibility(View.INVISIBLE);
        }else{
            tooglestutas_1 = true;
            shopPlanebtn1.setVisibility(View.INVISIBLE);
        }
    }

    public void togglePlane2(){
        if(tooglestutas_2){
            tooglestutas_2 = false;
            shopPlanebtn2.setVisibility(View.VISIBLE);
            shopPlanebtn1.setVisibility(View.INVISIBLE);
            shopPlanebtn3.setVisibility(View.INVISIBLE);
            shopPlanebtn4.setVisibility(View.INVISIBLE);
        }else{
            tooglestutas_2 = true;
            shopPlanebtn2.setVisibility(View.INVISIBLE);
        }
    }
    public void togglePlane3(){
        if(tooglestutas_3){
            tooglestutas_3 = false;
            shopPlanebtn3.setVisibility(View.VISIBLE);
            shopPlanebtn1.setVisibility(View.INVISIBLE);
            shopPlanebtn2.setVisibility(View.INVISIBLE);
            shopPlanebtn4.setVisibility(View.INVISIBLE);
        }else{
            tooglestutas_3 = true;
            shopPlanebtn3.setVisibility(View.INVISIBLE);
        }
    }
    public void togglePlane4(){
        if(tooglestutas_4){
            tooglestutas_4 = false;
            shopPlanebtn4.setVisibility(View.VISIBLE);
            shopPlanebtn1.setVisibility(View.INVISIBLE);
            shopPlanebtn2.setVisibility(View.INVISIBLE);
            shopPlanebtn3.setVisibility(View.INVISIBLE);
        }else{
            tooglestutas_4 = true;
            shopPlanebtn4.setVisibility(View.INVISIBLE);
        }
    }

    public void setCoinNum(){
        coin_num = sp.getInt("oldCoinNum",0);
        totalCoin = coin_num + newCoin;
        shopCoinNum.setText(Integer.toString(totalCoin));
    }




}