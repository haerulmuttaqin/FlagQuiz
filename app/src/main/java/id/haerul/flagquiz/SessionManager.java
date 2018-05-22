package id.haerul.flagquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

@SuppressLint("CommitPrefEdits")
public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	// Editor for Shared preferences
	public Editor editor;
	
	// Context
	public Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// nama sharepreference
	private static final String PREF_NAME = "Sesi";
	
	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_NAME = "nama";
	public static final String KEY_TOP = "top";
	public static final String KEY_LAST = "last";
	
	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	/**
	 * Create login session
	 * */
	public void createLoginSession(String nama, String top, String last){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_NAME, nama);
		editor.putString(KEY_TOP, top);
		editor.putString(KEY_LAST, last);
		editor.apply();
	}	
	
	/**
	 * Check login method wil check user login status
	 * If false it will redirect user to login page
	 * Else won't do anything
	 * */
	public void checkLogin(){
		// Check login status
		if(this.isLoggedIn()){
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, MainActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
			((InputNameActivity) _context).finish();
		}
		
	}
	
	
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();

		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		user.put(KEY_LAST, pref.getString(KEY_LAST, null));
		user.put(KEY_TOP, pref.getString(KEY_TOP, null));
		
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
		Intent i = new Intent(_context, InputNameActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		_context.startActivity(i);
	}

	public void resetUser(){
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
	}
	

	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
		
	}
}
