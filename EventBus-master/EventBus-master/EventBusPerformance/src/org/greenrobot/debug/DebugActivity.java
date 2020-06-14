package org.greenrobot.debug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbusperf.R;

public class DebugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        EventBus.getDefault().register(this);

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("主线程发送，主线程接受，形参为String");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 主线程 形参类型为 String
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventStr(String msg) {
        toast(msg);
    }


//    /**
//     * 主线程 形参类型为 String
//     *
//     * @param msg
//     */
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onEvent(Object msg) {
//
//    }


}
