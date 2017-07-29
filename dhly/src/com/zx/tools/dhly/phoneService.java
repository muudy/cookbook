package com.zx.tools.dhly;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

public class phoneService extends Service {
	private String path;
	public boolean n = false;
	private boolean mdh = false;
	private static AnimationDrawable animationDrawable;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(new PhoneListener(),
				PhoneStateListener.LISTEN_CALL_STATE);
	}

	private void getContactPeople(String incomingNumber) {
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = null;
		// cursor��Ҫ�ŵ��ֶ�����
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };
		// ������绰����ȥ�Ҹ���ϵ��
		cursor = contentResolver.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
				ContactsContract.CommonDataKinds.Phone.NUMBER + "=?",
				new String[] { incomingNumber }, "");
		if (cursor.getCount() == 0) {
			Constant.numberToCall = incomingNumber;
		} else if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			System.out.println(cursor.getString(1));
			Constant.numberToCall = cursor.getString(1);
		}
	}

	private final class PhoneListener extends PhoneStateListener {
		private MediaRecorder mediaRecorder;
		private File file;
		WindowManager wm1;
		Button bb1;

		private Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			try {
				switch (state) {
				case TelephonyManager.CALL_STATE_RINGING: // ����
					getContactPeople(incomingNumber);
					mdh = true;
					break;

				case TelephonyManager.CALL_STATE_OFFHOOK: // ��ͨ�绰
					if (!mdh) {

						getContactPeople(Constant.numberToCall);
					}
					if (Constant.b) {
						dqtime();
						path = Environment.getExternalStorageDirectory()
								+ "/recording";
						File f = new File(path);
						if (!f.exists()) {
							f.mkdirs();
						}
						path = Environment.getExternalStorageDirectory()
								+ "/recording/" + Constant.numberToCall;
						File f1 = new File(path);
						if (!f1.exists()) {
							f1.mkdirs();
						}

						file = new File(path, Constant.str + ".3gp");
						ly();
					} else {
						// ��������
						bb1 = new Button(getApplicationContext());
						wm1 = (WindowManager) getApplicationContext()
								.getSystemService("window");
						WindowManager.LayoutParams wmParams1 = new WindowManager.LayoutParams();
						wmParams1.gravity = Gravity.CENTER | Gravity.LEFT;
						wmParams1.type = 2002; // �����ǹؼ�����Ҳ��������2003
						wmParams1.format = 1;
						wmParams1.flags = 40;
						wmParams1.width = 80;
						wmParams1.height = 80;
						wm1.addView(bb1, wmParams1); // ����View
						n = false;
						bb1.setBackgroundResource(R.drawable.sj);
						final Handler bhandler = new Handler() {
							public void handleMessage(Message msg) {
								switch (msg.what) {
								case 0:
									if (animationDrawable != null) {
										animationDrawable.stop();
									}
									bb1.setBackgroundResource(R.drawable.sj);
									
									break;
								case 1:
									if (bb1 != null) {
										bb1.setBackgroundResource(R.anim.battry_process);
										animationDrawable = (AnimationDrawable) bb1
												.getBackground();
										animationDrawable.start();
									}
									break;

								default:
									break;
								}
								super.handleMessage(msg);
							}
						};
						bb1.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Message message = new Message();
								if (n == false) {
									n = true;
									message.what = 1;
									bhandler.sendMessage(message);
									dqtime();
//									bb1.setBackgroundResource(R.drawable.switch_on);
									path = Environment
											.getExternalStorageDirectory()
											+ "/recording";
									File f = new File(path);
									if (!f.exists()) {
										f.mkdirs();
									}
									path = Environment
											.getExternalStorageDirectory()
											+ "/recording/"
											+ Constant.numberToCall;
									File f1 = new File(path);
									if (!f1.exists()) {
										f1.mkdirs();
									}

									file = new File(path, Constant.str + ".3gp");
									ly();
								} else {
									n = false;
									message.what=0;
									bhandler.sendMessage(message);
//									bb1.setBackgroundResource(R.drawable.switch_off);
									if (mediaRecorder != null) {
										mediaRecorder.stop();
										mediaRecorder.release();
										mediaRecorder = null;
										Toast.makeText(getApplicationContext(),
												R.string.nlyyin,
												Toast.LENGTH_SHORT).show();
										// ��һ��
										vibrator.vibrate(100);
									}

								}

							}
						});
					}
					break;

				case TelephonyManager.CALL_STATE_IDLE: // �Ҷϵ绰
					if (!Constant.b) {
						wm1.removeView(bb1);
					}

					if (mediaRecorder != null) {
						mediaRecorder.stop();
						mediaRecorder.release();
						mediaRecorder = null;
						Toast.makeText(getApplicationContext(),
								R.string.mdianhua, Toast.LENGTH_SHORT).show();
						// ��һ��
						vibrator.vibrate(100);
					}

					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void dqtime() {
			Calendar c = Calendar.getInstance();
			int mYear = c.get(Calendar.YEAR); // ��ȡ��ǰ���
			int mMonth = c.get(Calendar.MONTH);// ��ȡ��ǰ�·�
			int mDay = c.get(Calendar.DAY_OF_MONTH);// ��ȡ��ǰ�·ݵ����ں���
			int mHour = c.get(Calendar.HOUR_OF_DAY);// ��ȡ��ǰ��Сʱ��
			int mMinute = c.get(Calendar.MINUTE);// ��ȡ��ǰ�ķ�����
			int miao = c.get(Calendar.SECOND);
			Constant.str = mMonth + getResources().getString(R.string.yue)
					+ mDay + getResources().getString(R.string.ri) + mHour
					+ getResources().getString(R.string.shi) + mMinute
					+ getResources().getString(R.string.fen) + miao
					+ getResources().getString(R.string.miao);

		}

		private void ly() {
			mediaRecorder = new MediaRecorder();
			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mediaRecorder.setOutputFile(file.getAbsolutePath());
			try {
				mediaRecorder.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mediaRecorder.start();
			Toast.makeText(getApplicationContext(), R.string.luyin,
					Toast.LENGTH_SHORT).show();
			// ��һ��
			vibrator.vibrate(100);
		}
	}

}
