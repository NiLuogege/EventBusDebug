package org.greenrobot.debug;

import org.greenrobot.eventbus.Subscribe;

public class Subscriber1 {
    @Subscribe
    public void onEvent(String event) {
        throw new RuntimeException("Subscriber1");
    }
}