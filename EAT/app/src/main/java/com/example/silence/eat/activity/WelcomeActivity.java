package com.example.silence.eat.activity;

import java.util.ArrayList;
import java.util.List;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {

	private List<ImageView> imageList;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		button = (Button) findViewById(R.id.button1);
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

		imageView1 = new ImageView(getApplicationContext());
		imageView1.setBackgroundResource(R.drawable.eat);
		imageView2 = new ImageView(getApplicationContext());
		imageView2.setBackgroundResource(R.drawable.eat);
		imageView3 = new ImageView(getApplicationContext());
		imageView3.setBackgroundResource(R.drawable.eat);

		imageList = new ArrayList<ImageView>();
		imageList.add(imageView1);
		imageList.add(imageView2);
		imageList.add(imageView3);

		viewPager.setAdapter(new MyAdapter());
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int selectPage) {
				// TODO Auto-generated method stub
				if (selectPage == imageList.size() - 1)
					button.setVisibility(View.VISIBLE);
				else {
					button.setVisibility(View.GONE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageList.get(position));
			return imageList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}


}
