package com.jues.zlibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Intellij IDEA .
 * 作者：jues
 * 日期：2018/1/18
 * 邮箱：15206394364@163.com
 * 介绍：
 * 修订：====================
 */

public class ZBaseFragment extends Fragment implements ZBaseView{
    protected List<Disposable> disposableList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.disposableList = new ArrayList<>();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public List<Disposable> getDisposableList() {
        return disposableList;
    }

    @Override
    public void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
