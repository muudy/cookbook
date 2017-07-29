package com.example.silence.eat.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silence.eat.model.Food;

import java.io.File;

public class DetailActivity extends Activity implements View.OnClickListener {
    private TextView tv_TextView1;
    private TextView tv_TextView2;
    private TextView tv_TextView3;
    private TextView tv_TextView4;
    private TextView tv_TextView5;
    private ImageView iv_imageView1;
    private ImageView iv_imageView2;
    private ImageView iv_imageView3;
    private Food food;
    String[] styles={"川菜","粤菜","鲁菜","湘菜","浙菜","清真","客家菜"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initZJ();
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        food = (Food) intent.getSerializableExtra("data");
        tv_TextView1.setText("菜名：" + food.getName().toString());
        tv_TextView2.setText("菜系：" + food.getStyle().toString());
        tv_TextView3.setText(food.getMaterial().toString());
        tv_TextView4.setText(food.getAccessories().toString());
        tv_TextView5.setText(food.getSteps().toString());
        if (food.getPath_image().toString().equals(""))
            iv_imageView3.setImageResource(R.mipmap.ic_launcher);
        else
            iv_imageView3.setImageURI(Uri.fromFile(new File(food.getPath_image())));
    }

    private void initZJ() {
// 找到这些组件
        tv_TextView1 = (TextView) findViewById(R.id.tv_TextView1);
        tv_TextView2 = (TextView) findViewById(R.id.tv_TextView2);
        tv_TextView3 = (TextView) findViewById(R.id.tv_TextView3);
        tv_TextView4 = (TextView) findViewById(R.id.tv_TextView4);
        tv_TextView5 = (TextView) findViewById(R.id.tv_TextView5);
        iv_imageView1 = (ImageView) findViewById(R.id.iv_imageView1);
        iv_imageView2 = (ImageView) findViewById(R.id.iv_imageView2);
        iv_imageView3 = (ImageView) findViewById(R.id.iv_imageView3);
        //文字太多设置滚动
        tv_TextView5.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv_TextView1.setOnClickListener(this);
        tv_TextView2.setOnClickListener(this);
        tv_TextView3.setOnClickListener(this);
        tv_TextView4.setOnClickListener(this);
        tv_TextView5.setOnClickListener(this);
        iv_imageView1.setOnClickListener(this);
        iv_imageView2.setOnClickListener(this);
        iv_imageView3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_TextView1:
                // method
                break;
            case R.id.tv_TextView2:
                // method
                break;
            case R.id.tv_TextView3:
                // method
                break;
            case R.id.tv_TextView4:
                // method
                break;
            case R.id.tv_TextView5:
                // method
                break;

            case R.id.iv_imageView1://音频播放
                if (food.getPath_audio().equals(""))
                    Toast.makeText(DetailActivity.this, "没有录制音频！", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(DetailActivity.this, PlayAudioActivity.class);
                    intent.putExtra("path_audio", food.getPath_audio());
                    startActivity(intent);
                }
                break;

            case R.id.iv_imageView2://视频播放
                if (food.getPath_video().equals(""))
                    Toast.makeText(DetailActivity.this, "没有录制视频！", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent1 = new Intent(DetailActivity.this, PlayVideoActivity.class);
                    intent1.putExtra("path_video", food.getPath_video());
                    startActivity(intent1);
                }
                break;

            case R.id.iv_imageView3://查看大图
                // method
                break;

            default:

                break;
        }

    }
}


