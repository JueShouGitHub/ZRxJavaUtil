package com.jues.zspider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.jues.zspider.entity.AdEntity;
import com.jues.zspider.global.Constant;
import com.jues.zspider.service.TestService;
import com.jues.zlibrary.api.BaseObserver;
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
        TestService service = HttpApi.getRetrofit().create(TestService.class);
        BaseObserver<AdEntity> observer = new BaseObserver<AdEntity>(mContext, disposableList) {
            @Override
            protected void onExecute(AdEntity adEntity) {
                Toast.makeText(MainActivity.this, "嗯~ 哈哈哈", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "嗯~ 呜呜呜", Toast.LENGTH_SHORT).show();
            }
        };
        ApiSubscribe.subscribe(service.getData(Constant.BASE_URL, ""), observer);
    }

}
