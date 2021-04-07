package com.example.study_group;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Kakao SDK 초기화
        KakaoSdk.init(this, "97b72477aa8435863225005bfdb42510");
    }
}
