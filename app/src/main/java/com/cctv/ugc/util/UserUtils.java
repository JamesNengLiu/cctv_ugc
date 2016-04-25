package com.cctv.ugc.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangyang on 16/4/24.
 */
public class UserUtils {

    public static final String PREF_NAME = "pref_user";

    public static final String KEY_USER_NAME = "key_user_name";

    public static final String KEY_USER_ID = "key_user_id";

    public static final String KEY_UPLOAD_PHONENUM = "key_upload_phonenum";


    private static SharedPreferences sSharedPreferences;

    private static Context sContext;

    public static void init(Context context){
        sContext = context.getApplicationContext();
        if(sSharedPreferences == null){
            sSharedPreferences = sContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static String getUserName() {
        return sSharedPreferences.getString(KEY_USER_NAME, "");
    }

    public static void saveUserNmae(String username) {
        sSharedPreferences.edit().putString(KEY_USER_NAME, username).commit();
    }

    public static String getUserId() {
        return sSharedPreferences.getString(KEY_USER_ID, "");
    }

    public static void saveUserId(String username) {
        sSharedPreferences.edit().putString(KEY_USER_ID, username).commit();
    }

    public static void saveUploadPhoneNum(String phoneNum){
        sSharedPreferences.edit().putString(KEY_UPLOAD_PHONENUM, phoneNum).commit();
    }
    public static String getSavedPhoneNum(){
        return sSharedPreferences.getString(KEY_UPLOAD_PHONENUM,null);
    }

}
