package com.example.silence.eat.activity;

import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.silence.eat.utils.CameraUtil;

public class RecordActivity extends Activity {

    // 注册一些组件
    private Button startRecord;
    private Button startPlay;
    private Button stopRecord;
    private Button stopPlay;

    private Button bt_save;
    private EditText filename;
    final String mainPath = android.os.Environment.getExternalStorageDirectory() + File.separator + "EAT" + File.separator+ "Audio" + File.separator;
    private static final String TAG = "AudioRecordTest";
    public static int flag = 0;
    private File file;

    private String path;

    // 语音操作对象
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;
    private String filename1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();

    } // onCreate结束

    // 开始录音
    class startRecordListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (flag == 0) {
                // 第一次单击触发的事件
                startRecord.setText("停止");
                mRecorder = new MediaRecorder();
                file = new File(mainPath,filename1);
                Log.e(TAG,"创建文件成功！");
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mRecorder.setOutputFile(file.getAbsolutePath());
                //Log.e("asd:", path);
                try {
                    mRecorder.prepare();
                } catch (IOException e) {
                Log.e(TAG, "prepare() failed");
            }
                mRecorder.start();
                flag = 1;
            } else {
                // 第二次单击button.text改变触发的事件
                startRecord.setText("录制");
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                filename.setText(file.getAbsolutePath());
                flag = 0;
            }

        }// onClick结束

    }


    // 播放录音
    class startPlayListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setDataSource(file.getAbsolutePath());
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                Log.e(TAG, "播放失败");
            }
        }

    }

    // 停止播放录音
    class stopPlayListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            mPlayer.release();
            mPlayer = null;
        }

    }

    private void initView() {
        // 找到这些组件
        startRecord = (Button) findViewById(R.id.startRecord);
        startPlay = (Button) findViewById(R.id.startPlay);
        stopPlay = (Button) findViewById(R.id.stopPlay);
        bt_save = (Button) findViewById(R.id.bt_save);
        filename = (EditText) findViewById(R.id.filename);

        // 绑定监听器
        startRecord.setOnClickListener(new startRecordListener());
        // stopRecord.setOnClickListener(new stopRecordListener());
        startPlay.setOnClickListener(new startPlayListener());
        stopPlay.setOnClickListener(new stopPlayListener());
        //保存按钮。
        bt_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //先保存

                //返回给Activity一个路径。
                Intent intent= RecordActivity.this.getIntent();
                intent.putExtra("audio_path",file.getAbsolutePath());
                RecordActivity.this.setResult(RESULT_OK, intent);
                RecordActivity.this.finish();
            }
        });
        // 设置sdcard的路径
        // path = Environment.getExternalStorageDirectory().getAbsolutePath();

        filename1 = CameraUtil.createFileName(".3GP");
        Log.e(TAG,"音频存放目录:"+mainPath);
        CameraUtil.createDir(mainPath);
        Log.e(TAG,"创建目录成功！");
        Log.e(TAG,"文件名:"+filename1);


    }


}// Activity结束