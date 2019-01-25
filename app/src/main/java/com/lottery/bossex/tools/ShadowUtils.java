package com.lottery.bossex.tools;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.lottery.bossex.R;
import com.lottery.bossex.views.ShadowDrawableWrapper;

public class ShadowUtils {
    public static void setShadowBackgroud(Context ac, Resources res, View view) {
        ShadowDrawableWrapper shadowDrawableWrapper = new ShadowDrawableWrapper(ac, res.getDrawable(R.drawable.shap_login_bg), res.getDimension(R.dimen.dp_5), 24, 24);
        view.setBackground(shadowDrawableWrapper);
    }
}
