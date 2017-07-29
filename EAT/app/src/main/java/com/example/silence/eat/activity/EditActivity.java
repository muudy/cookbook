package com.example.silence.eat.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silence.eat.activity.R;
import com.example.silence.eat.model.Food;
import com.example.silence.eat.utils.CameraUtil;
import com.example.silence.eat.utils.DBManager;
import com.example.silence.eat.utils.OpenHelper;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class EditActivity extends Activity implements View.OnClickListener {
    private final String TAG = "AddActivity";
    private EditText etName;
    private EditText etStyle;
    private EditText etMeterial;
    private EditText etAccessories;
    private EditText etSteps;
    private Button imgbtnSave;
    private ImageButton imgbtnImage;
    private OpenHelper helper;
    private ImageButton imgbtn1;
    private ImageButton imgbtn2;
    private ImageButton imgbtn3;
    private DBManager crudManager = null;
    private List<Food> foodList = null;
    private final int ACT_GALLERY = 0;
    private final int ACT_CAMERA = 1;
    private final int ACT_CROP = 2;
    private final int ACT_PERMISSION = 3;
    private final int REQUEST_AUDIO = 4;
    private Context context = null;
    private Uri pictureUri = null;
    private String path_audio = "";
    private String path_video;
    private int FID ;
    private String filePath = android.os.Environment.getExternalStorageDirectory() + File.separator + "EAT" + File.separator;
    final String mainPath = android.os.Environment.getExternalStorageDirectory() + File.separator + "EAT" + File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initZJ();
        initView();


    }

    private void initView() {
        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("data");
        FID = food.getFid();
        etName.setText(food.getName().toString());
        etStyle.setText(food.getStyle().toString());
        etMeterial.setText(food.getMaterial().toString());
        etAccessories.setText(food.getAccessories().toString());
        etSteps.setText(food.getSteps().toString());
        if (food.getPath_image().toString().equals(""))
            imgbtnImage.setImageResource(R.mipmap.ic_launcher);
        else
            imgbtnImage.setImageURI(Uri.fromFile(new File(food.getPath_image())));

    }

    private void initZJ() {
        //找组件
        etName = (EditText) findViewById(R.id.et_entername);
        etStyle = (EditText) findViewById(R.id.et_style);
        etMeterial = (EditText) findViewById(R.id.EditText_meterial);
        etAccessories = (EditText) findViewById(R.id.EditText_accessories);
        etSteps = (EditText) findViewById(R.id.EditText_steps);
        imgbtnSave = (Button) findViewById(R.id.btn_Save);
        imgbtnImage = (ImageButton) findViewById(R.id.imageButton_Food);
        imgbtn1 = (ImageButton) findViewById(R.id.imgbtn_1);
        imgbtn2 = (ImageButton) findViewById(R.id.imgbtn_2);
        imgbtn3 = (ImageButton) findViewById(R.id.imgbtn_3);
        //实例化OpenHelper
        helper = new OpenHelper(this);
        //传入helper得到数据库操作对象
        crudManager = new DBManager(helper);
        imgbtnSave.setOnClickListener(this);
        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
        imgbtn3.setOnClickListener(this);
        imgbtnImage.setOnClickListener(this);

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
                    String path_image = "";
                    if (pictureUri != null) {
                        path_image = pictureUri.getPath();
                    } else
                        path_image = "";
                    Food food = new Food(name, style, meterial, accessories, steps, path_image, path_audio, "");
                    Log.e(TAG, "图片插入路径：" + path_image);

                    crudManager.updateFood(FID,food);

                    foodList = crudManager.selectFood();
                    Intent intent = this.getIntent();
                    intent.putExtra("ResultList", (Serializable) foodList);
                    Toast.makeText(EditActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    this.setResult(300, intent);
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
                Intent intent = new Intent(EditActivity.this, RecordActivity.class);
                startActivityForResult(intent, REQUEST_AUDIO);
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
                    Log.e(TAG, "录音机返回的文件路径：" + path_audio);
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
            imgbtnImage.setImageBitmap(bitmap);
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

}
