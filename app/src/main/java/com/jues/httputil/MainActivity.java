package com.jues.httputil;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jues.httputil.entity.AdEntity;
import com.jues.httputil.global.Constant;
import com.jues.httputil.request.RequestEntity;
import com.jues.httputil.service.TestService;
import com.jues.zlibrary.api.BaseObserver;
import com.jues.zlibrary.api.HttpApi;
import com.jues.zlibrary.api.ZRequest;
import com.jues.zlibrary.api.request.ApiSubscribe;
import com.jues.zlibrary.base.ZBaseActivity;
import com.jues.zlibrary.callback.BaseCallback;
import com.jues.zlibrary.dialog.CreateProgressBar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends ZBaseActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //initData();
    }

    private void initView() {
        mImageView = findViewById(R.id.imageView);
        RxView.clicks(findViewById(R.id.textView)).subscribe(v -> initData3());
    }

    @Deprecated
    private void initData() {
        TestService service = HttpApi.rxEncryRetrofit().create(TestService.class);
        //EncryptionUtil<RequestEntity> encryptionUtil = new EncryptionUtil<>();
        ZRequest<RequestEntity> zRequest = new ZRequest<>();
        String request = zRequest.getSpecialRequest(new RequestEntity(), "Utils", "1001");
        service.getData(Constant.BASE_URL, request).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<AdEntity>(mContext, disposableList) {
                    @Override
                    protected void onExecute(AdEntity adEntity) {
                        Glide.with(mImageView).load(adEntity.getResult_data().getImg()).into(mImageView);
                    }

                    @Override
                    protected void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initData2() {
        TestService service = HttpApi.rxEncryRetrofit().create(TestService.class);
        //EncryptionUtil<RequestEntity> encryptionUtil = new EncryptionUtil<>();
        ZRequest<RequestEntity> zRequest = new ZRequest<>();
        String request = zRequest.getSpecialRequest(new RequestEntity(), "Utils", "1001");
        BaseObserver<AdEntity> observer = new BaseObserver<AdEntity>(mContext) {
            @Override
            protected void onExecute(AdEntity adEntity) {
                Glide.with(mImageView).load(adEntity.getResult_data().getImg()).into(mImageView);
            }
        };
        ApiSubscribe.subscribe(service.getData(Constant.BASE_URL, request), observer);
    }

    private void initData3(){
        CreateProgressBar.showProgress(mContext, "加载中...");
    }
}
