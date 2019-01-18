package com.lcjian.osc.ui.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lcjian.osc.App;
import com.lcjian.osc.RxBus;
import com.lcjian.osc.data.db.AppDatabase;
import com.lcjian.osc.data.network.RestAPI;
import com.lcjian.osc.data.network.entity.SignInInfo;
import com.lcjian.osc.di.component.AppComponent;

import javax.inject.Inject;
import javax.inject.Named;

public class BaseActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateComponent(App.getInstance().appComponent());
    }

    protected void onCreateComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    protected void putSignInInfo(SignInInfo signInInfo) {
        mUserInfoSp.edit().putString("sign_in_info", new Gson().toJson(signInInfo)).apply();
    }

    protected SignInInfo getSignInInfo() {
        return new Gson().fromJson(mUserInfoSp.getString("sign_in_info", ""), SignInInfo.class);
    }

    public void showProgress() {
        if (mProgressDialog == null)
            mProgressDialog = KProgressHUD.create(this);
        if (!mProgressDialog.isShowing())
            mProgressDialog.show();
    }

    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
