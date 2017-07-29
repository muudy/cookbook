package com.example.silence.eat.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;


public class PlayAudioActivity extends Activity {
    private String TAG = "PlayAudioActivity";
    private ImageView animationIV;
    private String file_path = "";
    private AnimationDrawable animationDrawable;
    private MediaPlayer music = null;// 播放器引用
    private Button button1,button2,button3;// 按钮引用
    private MediaPlayer player;//MediaPlayer对象
    private boolean isPause = false;//是否暂停
    private File file;//要播放的音频文件

    private void play(){  //写play方法
        try {
            player.reset();
            player.setDataSource(file_path);//重新设置要播放的音频
            player.prepare();//预加载音频
            player.start();//开始播放
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_play_audio);
        animationIV = (ImageView) findViewById(R.id.animationIV);

        final Button button1 = (Button)findViewById(R.id.button1);//获取“播放”按钮
        final Button button2 = (Button)findViewById(R.id.button2);//获取“暂停/继续”按钮
        final Button button3 = (Button)findViewById(R.id.button3);//获取“停止”按钮
        //取得路径
        Intent intent = PlayAudioActivity.this.getIntent();
        file_path = intent.getStringExtra("path_audio");

        file = new File(file_path);//获取要播放的文件
        Log.e(TAG,"音频路径："+file_path);
        if(file.exists()){
            player = MediaPlayer.create(this, Uri.parse(file_path));//创建MediaPlayer独享
        }else{
            button1.setEnabled(false);
            return;
        }



        button1.setOnClickListener(new OnClickListener()  //播放
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.voiceplayer1);  //动画
                animationDrawable = (AnimationDrawable) animationIV.getDrawable(); //动画
                animationDrawable.start();



                play();
                if(isPause){
                    button2.setText("暂停");
                    isPause = false;//设置暂停标记变量的值为false
                }

                button2.setEnabled(true);//“暂停/继续”按钮可用
                button3.setEnabled(true);//"停止"按钮可用
                button1.setEnabled(false);//“播放”按钮不可用
            }

        });

        button2.setOnClickListener(new OnClickListener()  //暂停播放
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.stop();
                if(player.isPlaying()&&!isPause){
                    player.pause();//暂停播放
                    isPause = true;
                    ((Button)v).setText("继续");

                    button1.setEnabled(true);//“播放”按钮可用
                }else{
                    player.start();//继续播放
                    ((Button)v).setText("暂停");

                    isPause = false;
                    button1.setEnabled(false);//“播放”按钮不可用
                }
            }
        });

        button3.setOnClickListener(new OnClickListener() {  //停止播放
            @Override
            public void onClick(View v) {
                player.stop();//停止播放
                button2.setEnabled(false);//“暂停/继续”按钮不可用
                button3.setEnabled(false);//“停止”按钮不可用
                button1.setEnabled(true);//“播放”按钮可用
            }
        });
    };


}
