package com.example.silence.eat.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silence.eat.model.Food;
import com.example.silence.eat.utils.DBManager;
import com.example.silence.eat.utils.OpenHelper;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity implements ViewPager.OnPageChangeListener {
    final String[] columns = {OpenHelper.FIELD_ID, OpenHelper.FIELD_NAME, OpenHelper.FIELD_STYLE,
            OpenHelper.FIELD_MATERIAL, OpenHelper.FIELD_ACCESSORIES, OpenHelper.FIELD_STEPS};
    private OpenHelper helper = new OpenHelper(this);//实例化helper;
    //private SQLiteDatabase database = null;
    private DBManager crudManager = null;
    private List<Food> foodList = null;
    private ListView listView;
    private FloatingActionButton fab;
    private EditText etSearch;
    private ImageView imageView;
    private TextView tvName;
    private TextView tvStyle;
    private TextView tvMeterial;
    final String TAG = "MainActivity";

    private ViewPager viewPager;
    private ImageView[] tips;//装小点的ImageView数组
    private ImageView[] mImageViews;//装ImageView数组
    private int[] imgIdArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        initLunbo();
        initView();//初始化组件并且填充数据
        initEditText();//监听EditText的实时变化并刷新

    }
    //初始化轮播区域
    private void initLunbo() {
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.item01, R.drawable.item02, R.drawable.item03, R.drawable.item04,
                R.drawable.item05};


        //将点点加入到ViewGroup中
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            imageView.setLayoutParams(layoutParams);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            group.addView(imageView);
        }
        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewPager.setAdapter(new LunboAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);
    }
    //轮播适配器
    public class LunboAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
        }



    }
    private void initEditText() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchString = s.toString();
                SelectByName(searchString);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*
    通过输入框内容的变化实时刷新listview
     */
    private void SelectByName(String searchString) {
        foodList = crudManager.queryFood(searchString);
        listView.setAdapter(new ItemAdapter());
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView1);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        etSearch = (EditText) findViewById(R.id.et_search);
        //etSearch.clearFocus();//防止键盘自动弹出（无效。。）
        crudManager = new DBManager(helper);
        //insertFood(food);
        foodList = crudManager.selectFood();//查询所有数据
        listView.setAdapter(new ItemAdapter());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 100);

            }
        });


        //短按相应方法（点击查看详情）
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                int foodid = foodList.get(position).getFid();
                Log.e("idd:", foodid + "");
                Food food = crudManager.queryFoodById(foodid);
                intent.putExtra("data", food);
                startActivity(intent);
            }
        });
        // listview 长按响应方法
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                //分别代表组号、选项ID、排序
                menu.add(0, 1, 0, "删除");
                menu.add(0, 2, 0, "修改");
            }
        });
    }//endInitView

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int) menuInfo.position;
        int foodid = foodList.get(id).getFid();
        //Log.e("ID1:",id+"");
        switch (item.getItemId()) {
            case 1://删除方法
                //Log.e("ID2:",id+"");
                crudManager.deleteFoodById(foodid);
                foodList = crudManager.selectFood();
                listView.setAdapter(new ItemAdapter());
                return true;
            case 2:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                foodid = foodList.get(id).getFid();
                Log.e("idd:", foodid + "");
                Food food = crudManager.queryFoodById(foodid);
                intent.putExtra("data", food);
                startActivityForResult(intent,150);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //重写onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (200 == resultCode) {
            foodList = (List<Food>) data.getSerializableExtra("ResultList");
            listView.setAdapter(new ItemAdapter());
        }else if( 300 == resultCode){//从Edit返回
            foodList = (List<Food>) data.getSerializableExtra("ResultList");
            listView.setAdapter(new ItemAdapter());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //以下为轮播函数
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % mImageViews.length);
    }
    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    private void setImageBackground( int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class ItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return foodList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            //View view;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item, parent, false);
            }

            //一个图片+两个文本+主料
            imageView = (ImageView) convertView.findViewById(R.id.img);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvStyle = (TextView) convertView.findViewById(R.id.tv_style);
            tvMeterial = (TextView) convertView.findViewById(R.id.tv_meterial);
            for (int i = 0; i < foodList.size(); i++) {
                switch (foodList.get(position).getFid()) {
                    case 1:
                        imageView.setImageResource(R.drawable.jidan);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.gali);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.hongtang);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.mapo);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.ganchao);
                        break;
                    default:
                        if (foodList.get(position).getPath_image().toString().equals(""))
                            imageView.setImageResource(R.mipmap.ic_launcher);
                        else
                            imageView.setImageURI(Uri.fromFile(new File(foodList.get(position).getPath_image())));
                        break;
                }

                tvName.setText(foodList.get(position).getName().toString());
                tvStyle.setText(foodList.get(position).getStyle().toString());
                tvMeterial.setText("主料：" + foodList.get(position).getMaterial().toString());
            }

            return convertView;
    }

}

    @Override
    protected void onDestroy() {
        crudManager.CloseDatabase();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        MainActivity.this.finish();
        System.exit(0);
    }
}
