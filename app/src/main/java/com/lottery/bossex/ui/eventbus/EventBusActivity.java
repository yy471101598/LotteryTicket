package com.lottery.bossex.ui.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.lottery.bossex.bean.event.EventMsg;
import com.lottery.bossex.tools.EventBusUtils;

/**
 * Created by songxiaotao on 2018/5/31.
 */

public class EventBusActivity extends Activity {
    private TextView text1;
    private TextView text2;
    private String tag = "xxLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusUtils.post(new EventMsg());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBusUtils.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }
    /**
     * 判断当前线程是否为主线程
     */
    public boolean isMainThread() {

        return Looper.myLooper() == Looper.getMainLooper();
    }




    /**
    ThreadMode.MAIN          不管从哪个线程发出的事件，MAIN模式都会在UI（主线程）线程执行
    ThreadMode.POSTING       事件从哪个线程发布出来的就会在该线程中运行
    ThreadMode.BACKGROUND    如果发送事件的线程是UI线程，则重新创建新的子线程执行，因此不能执行更新UI的操作
    ThreadMode.ASYNC         不管从哪个线程发出的事件，ASYNC模式都会创建一个新的子线程来执行
    /**
     * 不管从哪个线程发出的事件，MAIN模式都会在UI（主线程）线程执行onMessageEventMain()方法，
     * <p/>
     * 在这里可以更新UI的操作，不可以执行耗时的操作
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void onMessageEventMain(EventMsg event) {
        text2.setText(event.msg);
        Log.v(tag, event.msg + " priority = 1 ……MAIN id = " + Thread.currentThread().getId());
    }

    /**
     * 事件从哪个线程发布出来的，onMessageEventPost()方法就会在该线程中运行，
     * <p/>
     * 如果发送事件的线程是UI线程，则在UI线程执行，
     * 如果发送事件的线程是子线程，则在该子线程中执行
     */
    @Subscribe(threadMode = ThreadMode.POSTING, priority = 2,sticky = true)
    public void onMessageEventPost(EventMsg event) {
        if (isMainThread()) {
            text2.setText(event.msg);
            Log.v(tag, event.msg + " priority = 2 ……UI线程 POSTING id = " + Thread.currentThread().getId());
        } else {
            Log.v(tag, event.msg + " priority = 2 ……非UI线程 POSTING id = " + Thread.currentThread().getId());
        }
    }

    /**
     * 如果发送事件的线程是UI线程，则重新创建新的子线程执行onMessageEventPost()方法，
     * 因此不能执行更新UI的操作
     * <p/>
     * 如果发送事件的线程是子线程，则在该子线程中执行onMessageEventPost()方法
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 3)
    public void onMessageEventBackground(EventMsg event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {

            text2.setText(event.msg);
            Log.v(tag, event.msg + " priority = 3 ……UI线程 BACKGROUND id = " + Thread.currentThread().getId());
        } else {

            Log.v(tag, event.msg + " priority = 3 ……非UI线程 BACKGROUND id = " + Thread.currentThread().getId());
        }
    }

    /**
     * 不管从哪个线程发出的事件，ASYNC模式都会创建一个新的子线程来执行onEventAsync()方法，
     * <p/>
     * 所以在这里不能执行更新UI的操作，可以执行耗时的操作
     */
    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 4)
    public void onMessageEventAsync(EventMsg event) {
//		text2.setText(event.msg);	// 不能在这里执行更新ui的操作
        Log.v(tag, event.msg + " priority = 4 ……Async id = " + Thread.currentThread().getId());

        EventBus.getDefault().removeStickyEvent(event);
    }



}
