package org.greenrobot.debug;

import org.greenrobot.eventbus.Subscribe;

public class Subscriber2 {
    @Subscribe
    public void onEvent(String event) {
        throw new RuntimeException("Subscriber2");
    }
}