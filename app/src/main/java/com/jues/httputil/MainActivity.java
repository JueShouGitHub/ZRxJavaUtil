package com.jues.httputil;

import android.os.Bundle;
import android.widget.ImageView;

import com.jues.httputil.entity.AdEntity;
import com.jues.httputil.global.Constant;
import com.jues.httputil.service.TestService;
import com.jues.zlibrary.api.BaseObserver;
import com.jues.zlibrary.api.HttpApi;
import com.jues.zlibrary.api.request.ApiSubscribe;
import com.jues.zlibrary.base.ZBaseActivity;

public class MainActivity extends ZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //initData();
    }

    private void initView() {
        ImageView imageView = findViewById(R.id.imageView);
        findViewById(R.id.textView).setOnClickListener(v -> test());
    }

    private void test() {
        TestService service = HttpApi.rxEncryRetrofit().create(TestService.class);
        BaseObserver<AdEntity> observer = new BaseObserver<AdEntity>(mContext, disposableList) {
            @Override
            protected void onExecute(AdEntity adEntity) {
                //
            }
        };
        ApiSubscribe.subscribe(service.getData(Constant.BASE_URL, ""), observer);
    }

}
