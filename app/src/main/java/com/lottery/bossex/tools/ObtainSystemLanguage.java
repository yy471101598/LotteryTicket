package com.lottery.bossex.tools;

import android.content.Context;

import java.util.Locale;

/**
 * Created by Administrator on 2018/9/4 0004.
 */

public class ObtainSystemLanguage {
    public static String obainLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        PreferenceHelper.write(context, "numc", "lagavage", language);
        return language;
    }
}
