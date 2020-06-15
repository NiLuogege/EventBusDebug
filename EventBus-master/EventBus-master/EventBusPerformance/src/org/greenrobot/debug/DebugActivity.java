package org.greenrobot.debug;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.debug.model.AEventBean;
import org.greenrobot.debug.model.BEventBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.SubscriberExceptionEvent;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbusperf.R;

public class DebugActivity extends BaseActivity {

    private static final String TAG = "DebugActivity";
    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        eventBus = EventBus.builder()
//                .logSubscriberExceptions(true)
//                .logNoSubscriberMessages(true)
//                .throwSubscriberException(false)
//                .eventInheritance(false)
                .installDefaultEventBus();

        eventBus.register(this);
//        eventBus.register(new BadExceptionSubscriber());

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                eventBus.post("主线程发送，主线程接受，形参为String");
                BEventBean bEventBean = new BEventBean();
                bEventBean.bMsg = "主线程发送，主线程接受，形参为BEventBean";
                eventBus.post(bEventBean);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void log(String msg) {
        Log.e(TAG, msg);
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


    /**
     * 主线程 形参类型为 AEventBean
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventStr(AEventBean msg) {
        log(msg.toString());
    }

    /**
     * 主线程 形参类型为 BEventBean
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventStr(BEventBean msg) {
        toast(msg.toString());
    }


    /**
     * 主线程 形参类型为 SubscriberExceptionEvent
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SubscriberExceptionEvent msg) {

        Log.e(TAG, "订阅函数有异常 " + msg.toString());

    }


}
