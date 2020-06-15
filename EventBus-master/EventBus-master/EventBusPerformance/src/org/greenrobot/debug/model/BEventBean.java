package org.greenrobot.debug.model;

public class BEventBean extends AEventBean {
    public String bMsg;

    @Override
    public String toString() {
        return "BEventBean{" +
                "bMsg='" + bMsg + '\'' +
                ", aMsg='" + aMsg + '\'' +
                '}';
    }
}
