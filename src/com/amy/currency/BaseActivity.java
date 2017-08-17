package com.amy.currency;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public static BaseActivity instance=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance=this;
	}
	
	
	
	
	
	public static Toast toast=null;
	public final void showToast(String text) {
		if (toast != null) {
			toast.cancel();
		}
		toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
		toast.show();
	}
	public static final void showToastFromNetwork(final String text) {
		instance.runOnUiThread(new Runnable() {
			public void run() {
				instance.showToast(text);
			}
		});
	}
	
	public void changeActivity(Class<?> activity) {
		startActivity(new Intent(this, activity));
		overridePendingTransition(R.anim.fade_in, R.anim.push_left_out);
		finish();
	}

	public void changeActivityFromThread(final Class<?> activity) {
		runOnUiThread(new Runnable() {
			public void run() {
				instance.startActivity(new Intent(instance, activity));
				overridePendingTransition(R.anim.fade_in, R.anim.push_left_out);
			}
		});
	}
	public static void unbindDrawables(View view) {
		if (view != null) {
			if (view.getBackground() != null) {
				// System.out.println("view:" + view.getClass().getName());
				view.getBackground().setCallback(null);
			}
			if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
				for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
					unbindDrawables(((ViewGroup) view).getChildAt(i));
				}
				((ViewGroup) view).removeAllViews();
			}
		}
	}
	
	
	public Bitmap getBitmapFromDrawable(int R_drawable_) {
		return BitmapFactory.decodeResource(getResources(), R_drawable_);
	}
	public boolean isAppInstalled(String package_name) {
	    try {
	    	getPackageManager().getPackageInfo(package_name, PackageManager.GET_ACTIVITIES);
	        return true;
	    } catch (NameNotFoundException e) {
	        return false;
	    }
	}
	
	
//	public void onMenuClick(View view) {
//		switch (view.getId()) {
////			case R.id.custom_userinfo_help_button:
////				
////				break;
//			default:
//				break;
//		}
//	}
	
	private final static String TAG = "NetworkChecker";
	public boolean isNetworkConnected() {
		final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean val = false;
		Log.d(TAG, "Checking for WI-FI Network");
		final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifi != null && wifi.isAvailable() && wifi.isConnected()) {
			Log.i(TAG, "Found WI-FI Network");
			val = true;
		} else {
			Log.e(TAG, "WI-FI Network not Found");
		}
		Log.d(TAG, "Checking for Mobile Internet Network");
		final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobile != null && mobile.isAvailable() && mobile.isConnected()) {
			Log.i(TAG, "Found Mobile Internet Network");
			val = true;
		} else {
			Log.e(TAG, "Mobile Internet Network not Found");
		}
		return val;
	}
}
