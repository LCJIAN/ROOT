package com.lcjian.mmt.ui.base;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lcjian.mmt.App;
import com.lcjian.mmt.RxBus;
import com.lcjian.mmt.data.db.AppDatabase;
import com.lcjian.mmt.data.network.RestAPI;
import com.lcjian.mmt.data.network.entity.SignInInfo;
import com.lcjian.mmt.di.component.AppComponent;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    @Inject
    protected AppDatabase mAppDatabase;
    @Inject
    protected RestAPI mRestAPI;
    @Inject
    protected RxBus mRxBus;
    @Inject
    @Named("user_info")
    protected SharedPreferences mUserInfoSp;
    @Inject
    @Named("setting")
    protected SharedPreferences mSettingSp;

    private KProgressHUD mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateComponent(App.getInstance().appComponent());
    }

    protected void onCreateComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void showProgress() {
        if (getContext() == null) {
            return;
        }
        if (mProgressDialog == null)
            mProgressDialog = KProgressHUD.create(getContext());
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    protected void putSignInInfo(SignInInfo signInInfo) {
        mUserInfoSp.edit().putString("sign_in_info", new Gson().toJson(signInInfo)).apply();
    }

    protected SignInInfo getSignInInfo() {
        return new Gson().fromJson(mUserInfoSp.getString("sign_in_info", ""), SignInInfo.class);
    }
}
