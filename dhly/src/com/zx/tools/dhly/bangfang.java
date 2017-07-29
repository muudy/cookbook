package com.zx.tools.dhly;

import java.io.IOException;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.MediaController;
import android.widget.TextView;

public class bangfang extends Activity implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnInfoListener,
		MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
	private String name;
	private String path,path1;
	private AudioPlayerControl aplayer = null;
	private MediaController controller = null;
	private ViewGroup anchor = null;
	private TextView bt;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.bangfang);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.biaoti);
		bt=(TextView)findViewById(R.id.textView2);
		bt.setText(R.string.title);
		anchor = (ViewGroup) findViewById(R.id.bofang);
		if (aplayer != null) {
			aplayer.destroy();
			aplayer = null;
		}
		if (controller != null) {
			controller = null;
		}
		Intent nowIntent = getIntent();
		Bundle bundle = nowIntent.getExtras();
		name = bundle.getString("name1");
		path1=bundle.getString("path1");
		path = path1 + "/" + name;
		try {
			aplayer = new AudioPlayerControl(path, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			finish();
		}

	}
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onResume(this);
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPause(this);
	}
	public void onDestroy() {
		if (aplayer != null) {
			aplayer.destroy();
			aplayer = null;
		}
		super.onDestroy();
	}

	private class MyMediaController extends MediaController {
		public MyMediaController(Context c, boolean ff) {
			super(c, ff);
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		finish();
		return true;
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		controller = new MediaController(this, true); // enble fast forward

		controller.setMediaPlayer(aplayer);
		controller.setAnchorView(anchor);
		controller.setEnabled(true);
		controller.show(0); // aplayer.getDuration());
	}

}
