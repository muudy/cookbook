package com.zx.tools.dhly;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.umeng.analytics.MobclickAgent;
import com.zx.tools.dhly.ListViewAdapter.ListTextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class lvv extends Activity {
	private List<Map<String, Object>> list;
	private ListView listview;
	private ListViewAdapter adapter;
	private String t[];
	private int n[] = new int[] { R.string.lc, R.string.yi, R.string.er,
			R.string.san };
	private String name;
	private String path;
	private TextView bt, lb;
	boolean pushDown = false;
	float x = 0, y = 0;
	File f;
	private Handler handle = new Handler();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.lvv);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.biaoti);
//		final String[] ns=new String[]{getResources().getString(R.string.shanchu),getResources().getString(R.string.quxiao)};
//		for(int i=0;i<ns.length;i++){
//			System.out.println(ns[i]);
//			System.out.println("aaaaaaaaaaaa");
//		}
		bt = (TextView) findViewById(R.id.textView2);
		lb = (TextView) findViewById(R.id.aa);
		bt.setText(R.string.title);
		Intent nowIntent = getIntent();
		Bundle bundle = nowIntent.getExtras();
		name = bundle.getString("name");
		listview = (ListView) findViewById(R.id.listView1);
		adapter = new ListViewAdapter(this, getData());
		listview.setAdapter(adapter);
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				final int nn = arg2;
				if (f.list() != null) {
					String sss[] = f.list();
					if (sss.length != 0) {
						final String s = t[arg2];
						AlertDialog builder = new AlertDialog.Builder(lvv.this)
								.setTitle(R.string.shanchuwj)
								.setMessage(t[arg2])
//								.setItems(R.id.listView1, listener)
//								.setItems(ns, new OnClickListener() {
//									
//									@Override
//									public void onClick(DialogInterface dialog, int which) {
//										// TODO Auto-generated method stub
//										System.out.println("aaaaaaaaaaa");
//										System.out.println(which);
//									}
//								}).create();
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
												System.out
														.println(path + "/"
														+ s);
												ff.delete();
												// 删除listview里的一个元素
												list.remove(nn);
												adapter.notifyDataSetChanged();
											}
										})
								.setNegativeButton(R.string.quxiao,
										new OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												// 发送邮件
												Intent intent = new Intent(
														android.content.Intent.ACTION_SEND);
//												intent.putExtra(
//														android.content.Intent.EXTRA_EMAIL,
//														new String[] { "397100784@qq.com" });
												// intent.putExtra(android.content.Intent.EXTRA_CC,
												// new String[]
												// {"397100784@qq.com"});
												// intent.putExtra(android.content.Intent.EXTRA_BCC,
												// new String[]
												// {"397100784@qq.com"});

//												intent.putExtra(
//														android.content.Intent.EXTRA_SUBJECT,
//														"电话录音");// 标题
//												intent.putExtra(
//														android.content.Intent.EXTRA_TEXT,
//														"电话录音"); // 内容
												intent.putExtra(
														android.content.Intent.EXTRA_STREAM,
														Uri.parse("file://"
																+ path + "/"
																+ s));
												intent.setType("application/octet-stream");
												intent.setType("*/*");
												Intent.createChooser(intent,
														"Choose Email Client");
												startActivity(Intent
														.createChooser(intent,
																"发送"));

												// Intent data=new
												// Intent(Intent.ACTION_SENDTO);
												// intent.putExtra(Intent.EXTRA_EMAIL,new
												// String[]
												// {"397100784@qq.com"});
												// intent.putExtra(android.content.Intent.EXTRA_EMAIL,new
												// String[]
												// {"397100784@qq.com"});
												// intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
												// "电话录音");//标题
												// intent.putExtra(Intent.EXTRA_TEXT,
												// "电话录音"); //内容
												// intent.putExtra(android.content.Intent.EXTRA_STREAM,
												// Uri.parse("file://"+path+"/"+s)
												// );
												// intent.setType("application/octet-stream");
												// intent.setType("*/*");
												// Intent.createChooser(intent,
												// "Choose Email Client");
												// startActivity(Intent.createChooser(intent,
												// "发送"));

											}
										}).create();
						builder.show();// 显示对话框
					}

				}
				adapter.notifyDataSetChanged();
				return false;
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// System.out.println(t[arg2]);

				if (f.list() != null) {
					String ss[] = f.list();
					if (ss.length != 0) {
						Intent intent = new Intent();
						intent.putExtra("name1", t[arg2]);
						intent.putExtra("path1", path);
						intent.setClass(lvv.this, bangfang.class);
						startActivity(intent);
					}

				} else {
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
		Bitmap bf = BitmapFactory.decodeResource(getResources(), R.drawable.bf);
		path = Environment.getExternalStorageDirectory() + "/recording/" + name;
		f = new File(path);
		if (f.list() == null) {
			lb.setText(R.string.mluyin);
			lb.setTextSize(24f);
			for (int i = 0; i < n.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("text", "    " + getResources().getString(n[i]));
				list.add(map);
			}

		} else {
			t = f.list();
			if (t.length == 0) {
				lb.setText(R.string.mluyin);
				lb.setTextSize(24f);
				for (int i = 0; i < n.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("text", "    " + getResources().getString(n[i]));
					list.add(map);
				}

			} else {
				for (int i = 0; i < t.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("text", t[i]);
					map.put("images", bf);
					list.add(map);
				}
			}

		}
		return list;
	}

}
