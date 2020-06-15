package org.greenrobot.debug;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 主线程 形参类型为 String
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String msg) {
        Log.e(TAG,"收到消息 "+msg.toString());
    }
}
