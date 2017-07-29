package com.example.silence.eat.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.silence.eat.model.Food;
import com.example.silence.eat.utils.CameraUtil;
import com.example.silence.eat.utils.DBManager;
import com.example.silence.eat.utils.OpenHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class AddActivity extends Activity implements View.OnClickListener {

    private final String TAG = "AddActivity";
    private EditText etName;
    private AutoCompleteTextView etStyle;
    private EditText etMeterial;
    private EditText etAccessories;
    private EditText etSteps;
    private Button btnSave;
    private OpenHelper helper;
    private DBManager crudManager = null;
    private List<Food> foodList = null;
    private ImageButton imgbtn1;
    private ImageButton imgbtn2;
    private ImageButton imgbtn3;
    private ImageButton imgbtnStyle;
    private final int ACT_GALLERY = 0;
    private final int ACT_CAMERA = 1;
    private final int ACT_CROP = 2;
    private final int ACT_PERMISSION = 3;
    private final int REQUEST_AUDIO =4;
    private  final int REQUEST_VIDEO = 5;
    private Context context = null;
    private Uri pictureUri = null;
    private String path_audio="";
    private String path_video="";
    String[] styles={"川菜","粤菜","鲁菜","湘菜","浙菜","清真","客家菜"};
    private String filePath = android.os.Environment.getExternalStorageDirectory() + File.separator + "EAT" + File.separator;
    final String mainPath = android.os.Environment.getExternalStorageDirectory() + File.separator + "EAT" + File.separator;
    private ImageButton imgbtnFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.e("路径1：", Environment.getExternalStorageDirectory() + "");
        Log.e("路径2：", File.separator + "");
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        CameraUtil.createDir(filePath);
        initview();
    }

    private void initview() {
        //找组件
        etName = (EditText) findViewById(R.id.et_entername);
        etStyle = (AutoCompleteTextView) findViewById(R.id.et_style);
        etMeterial = (EditText) findViewById(R.id.EditText_meterial);
        etAccessories = (EditText) findViewById(R.id.EditText_accessories);
        imgbtnStyle = (ImageButton)  findViewById(R.id.imgbtn_style);
        etSteps = (EditText) findViewById(R.id.EditText_steps);
        btnSave = (Button) findViewById(R.id.btn_Save);
        imgbtn1 = (ImageButton) findViewById(R.id.imgbtn_1);
        imgbtn2 = (ImageButton) findViewById(R.id.imgbtn_2);
        imgbtn3 = (ImageButton) findViewById(R.id.imgbtn_3);
        imgbtnFood = (ImageButton) findViewById(R.id.imageButton_Food);
        //实例化OpenHelper
        helper = new OpenHelper(this);
        //传入helper得到数据库操作对象
        crudManager = new DBManager(helper);
        btnSave.setOnClickListener(this);
        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
        imgbtn3.setOnClickListener(this);
        imgbtnFood.setOnClickListener(this);
        imgbtnStyle.setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AddActivity.this, android.R.layout.simple_list_item_1,
                styles);
        etStyle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Save:
                if (judegNull()) {
                    String name = etName.getText().toString();
                    String style = etStyle.getText().toString();
                    String meterial = etMeterial.getText().toString();
                    String accessories = etAccessories.getText().toString();
                    String steps = etSteps.getText().toString();
                    String path_image="";
                    if(pictureUri!=null) {
                         path_image = pictureUri.getPath();
                    }else
                        path_image="";
                        Food food = new Food(name, style, meterial, accessories, steps, path_image, path_audio, path_video);
                        Log.e(TAG, "图片插入路径：" + path_image);

                    crudManager.insertFood(food);

                    foodList = crudManager.selectFood();

                    Intent intent = this.getIntent();
                    intent.putExtra("ResultList", (Serializable) foodList);
                    Toast.makeText(AddActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                    this.setResult(200, intent);
                    this.finish();
                }
                break;
            case R.id.imgbtn_3://调用照相机
                startCamera();
                break;
            case R.id.imageButton_Food://调用相册
                startGallery();
                break;
            case R.id.imgbtn_1://跳转到录音机
                Intent intent = new Intent(AddActivity.this,RecordActivity.class);
                startActivityForResult(intent,REQUEST_AUDIO);
                break;
            case R.id.imgbtn_2://跳转到录像机
                Intent intent1 = new Intent(AddActivity.this,VideoActivity.class);
                startActivityForResult(intent1,REQUEST_VIDEO);
                break;
            case R.id.imgbtn_style://跳转到录像机
                etStyle.showDropDown();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACT_GALLERY:// 从相册返回的数据
                    //交给相册回调函数处理
                    galleryBack(data.getData());
                    Log.e(TAG, "相册返回的Uri：" + data.getData());
                    break;
                case ACT_CAMERA: // 从相机返回的数据
                    startCrop(pictureUri);
                    break;
                case ACT_CROP: // 从剪切图片返回的数据
                    cropBack(pictureUri);
                    break;
                case REQUEST_AUDIO://从录音机返回的数据
                    Bundle bundle = data.getExtras();
                    path_audio = bundle.getString("audio_path");
                    Log.e(TAG,"录音机返回的文件路径："+path_audio);
                    break;
                case REQUEST_VIDEO://从录音机返回的数据
                    Bundle bundle1 = data.getExtras();
                    path_video = bundle1.getString("video_path");
                    Log.e(TAG,"录像返回的文件路径："+path_video);
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //启动相册
    private void startGallery() {
        //直接调用系统相册打开
        Intent intent = CameraUtil.openGallery();
        //把返回的intent交给回调函数处理
        startActivityForResult(intent, ACT_GALLERY);
    }

    //开始剪裁inUri是剪裁之前的Uri
    private void startCrop(Uri inUri) {
        //调用剪裁的工具类
        Intent intent = CameraUtil.cropPicture(inUri, pictureUri);
        startActivityForResult(intent, ACT_CROP);
    }

    private void galleryBack(Uri inUri) {
        //根据content的uri获取的实际的文件路径
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(inUri, projection, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Log.e(TAG, "实际的文件路径：" + filePath);
                //选择照片之后调用剪裁
                String fileName = CameraUtil.createFileName(".jpg");
                //创建新文件,
                CameraUtil.createFile(mainPath, fileName);
                //设置新文件的存储路径
                pictureUri = Uri.fromFile(new File(mainPath, fileName));
                startCrop(Uri.fromFile(new File(filePath)));
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    private void cropBack(Uri inUri) {
        if (inUri == null) {
            return;
        }
        try {

            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(inUri));
            imgbtnFood.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCamera() {

        String fileName = CameraUtil.createFileName(".jpg");
        CameraUtil.createFile(filePath, fileName);
        Log.e("路径：", filePath + fileName);
        //照片文件存放路径
        File file = new File(filePath, fileName);
        pictureUri = Uri.fromFile(file);

        //request the camera permission dynamic above android 6.0
        boolean permission = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        if (permission) {
            Intent intent = CameraUtil.openCamera(pictureUri);
            //启动照相机
            startActivityForResult(intent, ACT_CAMERA);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, ACT_PERMISSION);
        }

    }

    /*
    判断是否有空
     */
    public boolean judegNull() {
        String name = etName.getText().toString();
        String style = etStyle.getText().toString();
        String meterial = etMeterial.getText().toString();
        String accessories = etAccessories.getText().toString();
        String steps = etSteps.getText().toString();
        if (name == null || name.equals("")) {
            etName.setError("请填写菜名");
            return false;
        }
        if (style == null || style.equals("")) {
            etStyle.setError("请填写菜系");
            return false;
        }
        if (meterial == null || meterial.equals("")) {
            etMeterial.setError("请填写主料");
            return false;
        }
        if (accessories == null || accessories.equals("")) {
            etAccessories.setError("请填写辅料");
            return false;
        }
        if (steps == null || steps.equals("")) {
            etSteps.setError("请填写步骤");
            return false;
        }
        return true;
    }

}
