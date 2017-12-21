package com.example.theodorabendlin.mysmileandroid.events;

/**
 * EventBus event
 * All server related events should extend this class
 */

public abstract class AbstractServerEvent {

    private int callStatus;

    public static final int CALL_SUCCESSFUL = 1;
    public static final int CALL_FAILED     = 2;

    public AbstractServerEvent() { /* default constructor */ }

    public AbstractServerEvent(int callStatus) {
        this.callStatus = callStatus;
    }

    public boolean callFailed() {
        return this.callStatus == CALL_FAILED;
    }

    public boolean callSucceeded() {
        return this.callStatus == CALL_SUCCESSFUL;
    }
}
