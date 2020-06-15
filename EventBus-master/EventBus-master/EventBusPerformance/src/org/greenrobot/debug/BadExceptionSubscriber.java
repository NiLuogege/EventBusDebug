package org.greenrobot.debug;

import org.greenrobot.eventbus.Subscribe;

public class BadExceptionSubscriber {
    @Subscribe
    public void onEvent(String event) {
        throw new RuntimeException("Bad");
    }
}