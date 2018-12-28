package com.bw.ymy.week2_text1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Nutils {
    //判断网络
    public static boolean newwork(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isAvailable();

    }

}
