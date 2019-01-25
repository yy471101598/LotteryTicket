package com.lottery.bossex;

import android.app.Application;

import com.bandeng.MyEventBusIndex;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import ren.yale.android.cachewebviewlib.CacheWebView;

/**
 * Created by songxiaotao on 2018/5/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        修改缓存路径和大小,最好在Application中初始化，初始化没有耗时操作
        File cacheFile = new File(this.getCacheDir(), "cache_path_name");
        CacheWebView.getCacheConfig().init(this, cacheFile.getAbsolutePath(), 1024 * 1024 * 100, 1024 * 1024 * 10)
                .enableDebug(true);//100M 磁盘缓存空间,10M 内存缓存空间
        // 启用EventBus3.0加速功能
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                // other setters
                .setRequestListeners(requestListeners)
                .build();
        Fresco.initialize(this, config);
    }
}
