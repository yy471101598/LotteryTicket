package wy.com.ticket.web;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;

import ren.yale.android.cachewebviewlib.CacheInterceptor;
import ren.yale.android.cachewebviewlib.CacheStatus;
import ren.yale.android.cachewebviewlib.CacheWebView;
import ren.yale.android.cachewebviewlib.WebViewCache;
import wy.com.ticket.R;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by songxiaotao on 2018/5/28.
 * https://github.com/yale8848/CacheWebView
 */

public class WebActivity extends Activity {
private CacheWebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview= (CacheWebView) findViewById(R.id.webview);
        webview.setCacheStrategy(WebViewCache.CacheStrategy.FORCE);
//        默认没有阻塞图片加载，setBlockNetworkImage(true)后。在页面onPageStarted时阻塞图片加载，onPageFinished时打开图片加载
        webview.setBlockNetworkImage(true);
//        是否使用自定义缓存，默认是自定义缓存,如果是false，那就和正常的WebView使用一样
        webview.setEnableCache(true);
        webview.setEncoding("UTF-8");
        webview.setUserAgent("Android");
//        设置缓存拦截器，可以针对每一个url是否拦截缓存
        webview.setCacheInterceptor(new CacheInterceptor() {
            public boolean canCache(String url) {
                return true;
            }
        });
        // 加载需要显示的网页
        webview.loadUrl("http://ip.cn/");
//        获取缓存文件
        CacheStatus cacheStatus =  webview.getWebViewCache().getCacheFile(URL);
        if (cacheStatus.isExist()){
            File file = cacheStatus.getCacheFile();
            String extension = cacheStatus.getExtension();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
    }
}
