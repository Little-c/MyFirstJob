package Util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
	

	/**
	 * 保存到Preference
	 */
	public static void setUserID(Context context, String key, String value) {
		// 得到SharedPreferences
		SharedPreferences preferences = context.getSharedPreferences(
				"preference", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 从Preference取出数据
	 */
	public static String getUserID(Context context, String key) {
		SharedPreferences preferences = context.getSharedPreferences(
				"preference", Context.MODE_PRIVATE);
		// 返回key值，key值默认值是false
		return preferences.getString(key, "");

	}
}
