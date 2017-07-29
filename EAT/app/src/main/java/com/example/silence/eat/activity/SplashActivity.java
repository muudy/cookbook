package com.example.silence.eat.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

import com.example.silence.eat.utils.ShareUtils;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		new Thread() {
			public void run() {

				try {
					sleep(500);
					boolean b = ShareUtils.getBoolleanData(
							getApplicationContext(),  "is_first",true);
					if (b) {
						Intent intent = new Intent(SplashActivity.this,
								WelcomeActivity.class);
						startActivity(intent);
						ShareUtils.saveBoolleanData(
								getApplicationContext(), "is_first",false);
						finish();
					}else {
						Intent intent = new Intent(SplashActivity.this,MainActivity.class);
						startActivity(intent);
						SplashActivity.this.finish();
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

		}.start();
	}
}
