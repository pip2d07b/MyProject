package com.example.asm2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundEffect {
    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int rewardSound;
    private static int botoomSound;
    private MediaPlayer mediaPlayer;

    public SoundEffect(Context context) {

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);

        hitSound = soundPool.load(context,R.raw.hit,1);
        overSound = soundPool.load(context,R.raw.game_over,1);
        rewardSound = soundPool.load(context,R.raw.reward,1);
        botoomSound = soundPool.load(context,R.raw.bottom,1);


        mediaPlayer = MediaPlayer.create(context,R.raw.flymusic2);
        mediaPlayer.setLooping(true);

    }




    public void playHitSound(){
        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);

    }

    public void playOverSound(){
        soundPool.play(overSound,1.0f,1.0f,1,0,1.0f);

    }

    public void playRewardSound(){
        soundPool.play(rewardSound,1.0f,1.0f,1,0,1.0f);

    }

    public void playBottomSound(){
        soundPool.play(botoomSound,1.0f,1.0f,1,0,1.0f);

    }

    public void playMusic(){
        mediaPlayer.setVolume(0.5f,0.5f);
        mediaPlayer.start();

    }


    public void stopMusic(){
        mediaPlayer.stop();
    }



}
