package com.example.asm2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

public class FlyingPlaneView extends View{

    MainActivity mainActivity;

    private Bitmap bg,resize_bg,gas,resize_gas, rocket, pause, play;
    private Bitmap life[] = new Bitmap[2];
    private Bitmap plane[] = new Bitmap[10];

    private Paint scorePaint = new Paint();
    private Paint clickAlertPaint = new Paint();
    private Paint timerPaint = new Paint();
    private Paint tapPaint = new Paint();


    private int planeX = 50, planeY,planeSpeed;
    private int speedForRocket=10;
    private int canvasWidth, canvasHeight,score,minPlaneY,maxPlaneY;
    private int lifeCounter = 3;
    public int timerCLickStutas;
    private boolean cLickStutas=false;
    private int applyNum = 0, level, targetScor;
    private int timeLimSec;
    private int timeLimMin;
    private int timeLevel;
    private int bgLevel;
    private int seconds, minutes;

    public int totalTime = 0;

    private boolean touchStatus = false;
    public boolean startTouchStatus = false;
    private boolean timerStutas =false;
    public boolean pauseStutas =true;
    private boolean playStutas =false;

    private String clickAlert = "Tap to start";
    private String clickTagAlert = "Score ";
    private String str_targetScor;
    private String levlNum,str_level = "Level ";
    public String timeText ="00.00";
    public String str_timeLlim = "min";
    public String formatTimeLim;
    public String str_score = "Score: ";
    public String str_intinit = "Infinite Mode";

    private SoundEffect sound;

    private double flying_speed_acc;

    SharedPreferences sp;
    int lang_num;

    Boolean isInfinite=false,isTimemode=false;

    private Rocket r1=new Rocket(),r2=new Rocket(),r3=new Rocket(),r4=new Rocket(),r5=new Rocket(),r6=new Rocket(),r7=new Rocket(),r8=new Rocket(),r9=new Rocket(),r10=new Rocket(),r11=new Rocket();
    private Gas g1=new Gas(),g2=new Gas(),g3=new Gas(),g4=new Gas(),g5=new Gas(),g6=new Gas(),g7=new Gas(),g8=new Gas(),g9=new Gas(),g10=new Gas(),g11=new Gas();

    public class Rocket{
        public int rocketSpeed,rocketX,rocketY,startX;
        Boolean isInfinite=false;

        public Rocket(){
            if(level==0){
                this.rocketSpeed=18;
                isInfinite=true;
            }else {
                this.rocketSpeed = speedForRocket;
            }
        }

        public void fly(){
            if(isInfinite){
                this.rocketX = this.rocketX-(int)Math.floor(level*0.35+this.rocketSpeed*0.25);
            }else {
                this.rocketX = this.rocketX - this.rocketSpeed;
            }
            if(hitRocketChecker(this.rocketX,this.rocketY))
            {
                this.rocketX = this.rocketX - 500;
                lifeCounter --;
                sound.playHitSound();
                if(lifeCounter == 0)
                {
                    SharedPreferences.Editor editor = sp.edit();
                    sound.playOverSound();
                    //sp
                    String str_coinNum = Integer.toString(score);
                    editor.putInt("coinNum",score);
                    editor.commit();
                    Log.d("Flysp","though rocket");
                    sound.stopMusic();
                    Intent gameoverIntent = new Intent(getContext(), GameOverActivity.class);
                    getContext().startActivity(gameoverIntent);

                }
            }
            if(this.rocketX< 0)
            {   this.startX=(int) Math.floor((Math.random()*2000)+1);
                this.rocketX = canvasWidth + this.startX;
                this.rocketY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
            }
        }
    }

    public class Gas{
        int gasX,gasY,gasSpeed=18,startX;

        public void fly(){

            this.gasX = this.gasX - this.gasSpeed;

            if(hitRocketChecker(this.gasX,this.gasY))
            {
//                winDetect();
                score = score + 10;
                this.gasX = this.gasX - 500;
                sound.playRewardSound();
            }
            if(this.gasX < 0)
            {   this.startX=(int) Math.floor((Math.random()*2000)+1);
                this.gasX = canvasWidth + this.startX;
                this.gasY = (int) Math.floor((Math.random()*(maxPlaneY - minPlaneY)) + minPlaneY);
            }
        }

    }

    public FlyingPlaneView(Context context) {
        super(context);
        File imgFile = new  File("/storage/emulated/0/Android/data/com.example.asm2/files/Pictures/plane_custom.jpg");


        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        //sound
        sound = new SoundEffect(context);
        sound.playMusic();

        //Declare Bitmap
        gas = BitmapFactory.decodeResource(getResources(), R.drawable.oil);
        rocket = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rocket), 150, 150 ,false);

        //resize Bitmap
        plane[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane), 200, 200 ,false);
        plane[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane1_1), 200, 200,false);
        plane[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane2), 200, 200 ,false);
        plane[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane2_1), 200, 200,false);
        plane[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane3), 200, 200,false);
        plane[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane3_1), 200, 200,false);
        plane[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane4), 200, 200,false);
        plane[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.plane4_1), 200, 200,false);

        try {
            plane[8] = Bitmap.createScaledBitmap(myBitmap, 200, 200,false);
            plane[9] = Bitmap.createScaledBitmap(myBitmap, 200, 200,false);
        }catch (Exception e){

        }
        resize_gas = Bitmap.createScaledBitmap(gas, 100, 100 ,false);
        life[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 80, 80 ,false);
        life[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.greyheart), 80, 80 ,false);
        pause = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause), 100, 100 ,false);
        play = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play), 60, 60 ,false);

        // Paint style
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        tapPaint.setColor(Color.RED);
        tapPaint.setTextSize(100);
        tapPaint.setTypeface(Typeface.DEFAULT_BOLD);
        tapPaint.setAntiAlias(true);

        clickAlertPaint.setColor(Color.WHITE);
        clickAlertPaint.setTextSize(100);
        clickAlertPaint.setTypeface(Typeface.DEFAULT_BOLD);
        clickAlertPaint.setAntiAlias(true);

        planeY = 250;
        score = 0;
        targetScor = level*10+50;
        str_targetScor = Integer.toString(level*10+50);

        //bottom
        Timer timer2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                bottom();
            }
        };
        timer2.schedule(timerTask2,0,250);

        //SP
        sp = context.getSharedPreferences("ShopSp",Context.MODE_PRIVATE);

        //GetSap
        applyNum = sp.getInt("applyNum",0);
        level = sp.getInt("levelNum",0);

        levlNum = Integer.toString(level);
        bgLevel = level;


        if(level > 50){
            timeLevel = level;
            timeLimSec = 30 + ((timeLevel-51) * 5);
            strLevelChange();
        }
    }

    public void setSpeed(double speed){
        flying_speed_acc = Math.floor(speed);
        speedForRocket=speedForRocket+ (int) flying_speed_acc;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        cLickStutas=false;
        countTar();
        targetScor = level*10+50;

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        if(bgLevel<=10){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg);
        }else if(bgLevel<=20){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg2);
        }else if(bgLevel<=30){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingame9);
        }else if(bgLevel<=40){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg4);
        }else if(bgLevel<=60){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg5);
        }else if(bgLevel<=80){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg6);
        }else if(bgLevel<=100){
            bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingamebg7);
        }
        resize_bg = Bitmap.createScaledBitmap(bg, canvasWidth,canvasHeight,false);

        //BG
        canvas.drawBitmap(resize_bg,0,0,null);
        //Score
        canvas.drawText(str_score  +score,70,100,scorePaint);
        //Display heart
        for( int j = 0 ; j <3 ; j++)
        {
            int heartX = (int) (700 + j*100);
            int heartY = 50;

            if(j < lifeCounter)
            {
                canvas.drawBitmap(life[0], heartX, heartY,null);
            }else
            {
                canvas.drawBitmap(life[1], heartX, heartY,null);
            }
        }

//      Display timer
        canvas.drawText(timeText,450,200,scorePaint);

        //Display alert
        canvas.drawText(clickAlert,300,700,tapPaint);

        if(level >= 1 && level <= 50){
            canvas.drawText(clickTagAlert + str_targetScor,340,900,clickAlertPaint);
            canvas.drawText(str_level + levlNum,370,800,clickAlertPaint);
        }else if(level > 50 ){
            canvas.drawText(getTimeLim() + str_timeLlim,330,900,clickAlertPaint);
            canvas.drawText(str_level + levlNum,370,800,clickAlertPaint);
        }else if(level == 0 ){
            canvas.drawText(str_intinit,290,830,clickAlertPaint);
        }

        //start the game
        if(startTouchStatus && pauseStutas) {

            clickAlert = "";
            clickTagAlert = "";
            str_targetScor = "";
            str_level ="";
            levlNum = "";
            formatTimeLim = "";
            str_timeLlim = "";
            str_intinit = "";
            minPlaneY = plane[applyNum].getHeight();
            maxPlaneY = canvasHeight - plane[applyNum].getHeight() * 2;
            planeY = planeY + planeSpeed;

            planeSpeed = planeSpeed + 2;

            // plane position
            if(planeY < minPlaneY)
            {
                planeY = minPlaneY;
            }
            if(planeY > maxPlaneY)
            {
                planeY = maxPlaneY;
            }

            if (touchStatus) {
                canvas.drawBitmap(plane[applyNum+1], planeX, planeY, null);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touchStatus = false;
                    }
                }, 200);
            } else {
                canvas.drawBitmap(plane[applyNum], planeX, planeY, null);
            }

            switch(level){
                case 100:
                    r11.fly();
                    canvas.drawBitmap(rocket, r11.rocketX, r11.rocketY, null);
                case 99:
                case 98:
                case 97:
                case 96:
                case 95:
                case 94:
                case 93:
                case 92:
                case 91:
                case 90:

                    r10.fly();
                    canvas.drawBitmap(rocket, r10.rocketX, r10.rocketY, null);
                case 89:
                case 88:
                case 87:
                case 86:
                case 85:
                case 84:
                case 83:
                case 82:
                case 81:
                case 80:

                    r9.fly();
                    canvas.drawBitmap(rocket, r9.rocketX, r9.rocketY, null);
                case 79:
                case 78:
                case 77:
                case 76:
                case 75:
                case 74:
                case 73:
                case 72:
                case 71:
                case 70:

                    r8.fly();
                    canvas.drawBitmap(rocket, r8.rocketX, r8.rocketY, null);
                case 69:
                case 68:
                case 67:
                case 66:
                case 65:
                case 64:
                case 63:
                case 62:
                case 61:
                case 60:

                    r7.fly();
                    canvas.drawBitmap(rocket, r7.rocketX, r7.rocketY, null);
                case 59:
                case 58:
                case 57:
                case 56:
                case 55:
                case 54:
                case 53:
                case 52:
                case 51:
                    if(!isInfinite) {
                        isTimemode = true;
                        level = level - 50;
                    }
                    r6.fly();
                    canvas.drawBitmap(rocket, r6.rocketX, r6.rocketY, null);
                case 50:
                    g6.fly();
                    canvas.drawBitmap(resize_gas, g6.gasX, g6.gasY, null);
                    r6.fly();
                    canvas.drawBitmap(rocket, r6.rocketX, r6.rocketY, null);
                case 49:
                case 48:
                case 47:
                case 46:
                case 45:
                case 44:
                case 43:
                case 42:
                case 41:
                case 40:
                    g5.fly();
                    canvas.drawBitmap(resize_gas, g5.gasX, g5.gasY, null);
                    r5.fly();
                    canvas.drawBitmap(rocket, r5.rocketX, r5.rocketY, null);
                case 39:
                case 38:
                case 37:
                case 36:
                case 35:
                case 34:
                case 33:
                case 32:
                case 31:
                case 30:
                    g4.fly();
                    canvas.drawBitmap(resize_gas, g4.gasX, g4.gasY, null);
                    r4.fly();
                    canvas.drawBitmap(rocket, r4.rocketX, r4.rocketY, null);
                case 29:
                case 28:
                case 27:
                case 26:
                case 25:
                case 24:
                case 23:
                case 22:
                case 21:
                case 20:
                    g3.fly();
                    canvas.drawBitmap(resize_gas, g3.gasX, g3.gasY, null);
                    r3.fly();
                    canvas.drawBitmap(rocket, r3.rocketX, r3.rocketY, null);
                case 19:
                case 18:
                case 17:
                case 16:
                case 15:
                case 14:
                case 13:
                case 12:
                case 11:
                case 10:
                    g2.fly();
                    canvas.drawBitmap(resize_gas, g2.gasX, g2.gasY, null);
                    r2.fly();
                    canvas.drawBitmap(rocket, r2.rocketX, r2.rocketY, null);
                case 9:
                case 8:
                case 7:
                case 6:
                case 5:
                case 4:
                case 3:
                case 2:
                case 1:
                    g1.fly();
                    canvas.drawBitmap(resize_gas, g1.gasX, g1.gasY, null);
                    r1.fly();
                    canvas.drawBitmap(rocket, r1.rocketX, r1.rocketY, null);
                    break;
                case 0:
                    isInfinite=true;
                    break;
            }
            if(isInfinite){
                bgLevel=Math.min(totalTime,100);
                level=Math.min(totalTime,100);
            }

            if(pauseStutas){
                canvas.drawBitmap(pause,860,130,null);
            }else{
                canvas.drawBitmap(play,890,150,null);
            }
        }else{
            canvas.drawBitmap(play,890,150,null);
        }
        if(timerCLickStutas == 0){
            changeLang();
        }

    }

    public void timemodeDetect(){
        if(minutes >= timeLimMin && seconds >= timeLimSec)
        {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Heart",lifeCounter);
            editor.putInt("coinNum",score);
            editor.putInt("unlockLevelTime",level+1);
            editor.commit();
            stopMusic();
            Intent winIntent = new Intent(getContext(), NextLevelActivity.class);
            getContext().startActivity(winIntent);
            Log.d("checkmode","true");
        }else{
            Log.d("checkmode","false");
        }
    }



    public String getTimeLim(){

        if(!startTouchStatus){
            while( timeLimSec >= 60){
                timeLimSec = timeLimSec - 60;
                timeLimMin ++;
            }
            Log.d("timelim",timeLimSec + " " + timeLimMin );
            formatTimeLim = timeLimMin + " : " + timeLimSec;
        }else{
            formatTimeLim = " ";
        }

        return  formatTimeLim;
    }
    public void strLevelChange(){
        if(level > 50){
            levlNum = Integer.toString(timeLevel - 50);
        }
    }

    public void changeLang(){
        lang_num = sp.getInt("lang_num",0);
        if(lang_num == 0){
            clickAlert = "Tap to start";
            clickTagAlert = "Score ";
            str_level = "Level ";
            str_timeLlim ="min";
            str_score = "Score: ";
            str_intinit = "Infinite Mode";
        }else{
            clickAlert = "點擊屏幕";
            clickTagAlert = "分數 ";
            str_level = "關卡 ";
            str_timeLlim = "分鐘";
            str_score = "分數： ";
            str_intinit = "無限模式";
        }
    }


    public void startTimer(){
        Timer timer = new Timer();
        if(timerCLickStutas == 1 && pauseStutas){
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(pauseStutas){
                        totalTime++;
                        timeText = getTimerText(totalTime);
                    }
                }
            };
            timer.schedule(timerTask,0,1000);
        }
    }

    public String getTimerText(int time){


        seconds = time %  60;
        minutes = (time % 3600) / 60;

        Log.d("timer2"," m: "+ minutes +" s: "+ seconds);


        return  formatTime(seconds,minutes);
    }

    public String formatTime(int seconds,int minutes){
        return String.format("%02d",minutes) +" : "+ String.format("%02d",seconds);
    }

    public void pauseChecker(float eventX, float eventY){
        if (eventX > 890 && eventX < 860 + 100 && eventY > 150 && eventY < 150 + 100 ){
            if(pauseStutas){
                pauseStutas = false;
            }else{
                pauseStutas = true;
            }
        }
    }

    public String countTar(){
        if(timerCLickStutas== 0){
            str_targetScor = Integer.toString(targetScor);
        }
        return str_targetScor;
    }

    public void winDetect(){
        if(score >=targetScor){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("Heart",lifeCounter);
            editor.putInt("coinNum",score);
            editor.putInt("unlockLevelScore",level+1);
            editor.commit();
            stopMusic();
            Intent winIntent = new Intent(getContext(), NextLevelActivity.class);
            getContext().startActivity(winIntent);
        }
    }

    public void bottom(){

        if(planeY==maxPlaneY && score>=10 && cLickStutas==false)
        {
            score = score - 10;

            sound.playBottomSound();
        }
    }

    public boolean hitRocketChecker(int x, int y)
    {
        if((planeX < x && x < (planeX + plane[applyNum].getWidth()) && planeY < y && y < (planeY + plane[applyNum].getHeight())) || (planeX < x && x < (planeX + plane[applyNum+1].getWidth()) && planeY < y && y < (planeY + plane[applyNum+1].getHeight())))
        {
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventX = (int)event.getX();
        int eventY = (int)event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchStatus = true;
            startTouchStatus = true;
            planeSpeed = -22;
            timerCLickStutas++;
            cLickStutas=true;
            startTimer();
            pauseChecker(eventX,eventY);

            if(timeLevel <= 50){
                winDetect();
                Log.d("mod_change","sciremode"+" " + timeLevel);
            }else{
                timemodeDetect();
                Log.d("mod change ","timemode" + " " + timeLevel);
            }
        }

        return super.onTouchEvent(event);
    }

    public void stopMusic(){
        sound.stopMusic();

    }

}


