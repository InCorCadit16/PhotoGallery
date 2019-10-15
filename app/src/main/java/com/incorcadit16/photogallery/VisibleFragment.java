package com.incorcadit16.photogallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public abstract class VisibleFragment extends Fragment {
    private static final String TAG = "VisibleFragment";
    private static final String PERM_PRIVATE = "com.incorcadit16.photogallery.PRIVATE";

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(PollService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mOnShowNotification,filter,PERM_PRIVATE,null);
    }



    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mOnShowNotification);
    }

    private BroadcastReceiver mOnShowNotification = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Если данный интент получен, значит пользователь видит приложение
            Log.i(TAG,"Cancelling notificaiton");
            setResultCode(Activity.RESULT_CANCELED);
        }
    };
}
