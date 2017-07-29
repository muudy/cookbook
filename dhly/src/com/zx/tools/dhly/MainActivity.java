package com.zx.tools.dhly;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.umeng.analytics.MobclickAgent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private GridView gridview;
//	private DisplayMetrics dm;
	private List<Map<String, Object>> list;
	private GridViewAdapter adapter;
	private Button but;
	File f;
	private TextView lb,bt;
	private String t[];
	private String path;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		but=(Button)findViewById(R.id.button1);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.biaoti);
		bt=(TextView)findViewById(R.id.textView2);
		bt.setText(R.string.title);
		//屏幕大小
//		dm=new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		Constant.wP=dm.widthPixels;
//		Constant.hP=dm.heightPixels;
		
		lb=(TextView)findViewById(R.id.textView1);
		lb.setTextSize(24f);
		path = Environment
				.getExternalStorageDirectory()
				+ "/recording";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		Intent it=new Intent(MainActivity.this,phoneService.class);
		startService(it);
		gridview=(GridView)findViewById(R.id.gridView1);
		adapter = new GridViewAdapter(this, getData());
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(f.list()!=null){
					Intent it = new Intent();
					it.putExtra("name", t[arg2]);
					it.setClass(MainActivity.this, lvv.class);
					startActivity(it);
				}else{}
				
			}
		});
		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				final int nn = arg2;
				if (f.list() != null) {
					String sss[] = f.list();
					if (sss.length != 0) {
						final String s = t[arg2];
						AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
								.setTitle(R.string.sc)
								.setMessage(t[arg2])
								.setPositiveButton(R.string.shanchu,
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int i) {
												// TODO Auto-generated method
												// stub
												// 删除文件
												File ff = new File(path + "/"
														+ s);
												ff.delete();
												// 删除listview里的一个元素
												list.remove(nn);
												adapter.notifyDataSetChanged();
											}
										})
								.setNegativeButton(R.string.qx,
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												// 发送邮件
//												Intent intent = new Intent(
//														android.content.Intent.ACTION_SEND);
//												intent.putExtra(
//														android.content.Intent.EXTRA_STREAM,
//														Uri.parse("file://"
//																+ path + "/"
//																+ s));
//												intent.setType("application/octet-stream");
//												intent.setType("*/*");
//												Intent.createChooser(intent,
//														"Choose Email Client");
//												startActivity(Intent
//														.createChooser(intent,
//																"发送"));
											}
										}).create();
						builder.show();// 显示对话框
					}

				}
				adapter.notifyDataSetChanged();
				return false;
			}
		});
		if(!Constant.b){
			but.setText(R.string.bqly);
		}else{
			but.setText(R.string.qly);
		}
		
		but.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Constant.b){
					Constant.b=false;
					but.setText(R.string.bqly);
				}else{
					Constant.b=true;
					but.setText(R.string.qly);
				}
				
				
				
			}
		});
	}
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onResume(this);
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPause(this);
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		list = new ArrayList<Map<String, Object>>();
		Bitmap bitmaps= BitmapFactory.decodeResource(getResources(),
				R.drawable.wjj);
		f = new File(Environment.getExternalStorageDirectory() + "/recording");
		if (f.list() == null) {
			
			lb.setText(R.string.mluyin);
			lb.setTextSize(24f);
			lb.setGravity(Gravity.CENTER);
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("text", getResources().getString(R.string.mluyin));
//			list.add(map);
		} else {
			t = f.list();
			if(t.length==0){
				lb.setText(R.string.mluyin);
				lb.setTextSize(24f);
				lb.setGravity(Gravity.CENTER);
			}else{
				lb.setText(R.string.liebiao);
				for (int i = 0; i < t.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("text", t[i]);
					map.put("images", bitmaps);
					list.add(map);
				}
			}
			
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
