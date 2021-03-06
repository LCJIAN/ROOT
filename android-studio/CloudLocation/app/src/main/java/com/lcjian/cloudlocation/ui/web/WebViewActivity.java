package com.lcjian.cloudlocation.ui.web;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebSettingsImpl;
import com.lcjian.cloudlocation.R;
import com.lcjian.cloudlocation.ui.base.BaseActivity;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_nav_back)
    ImageButton btn_nav_back;
    @BindView(R.id.btn_nav_right)
    ImageButton btn_nav_right;
    @BindView(R.id.srl_web)
    SwipeRefreshLayout srl_web;

    private AgentWeb mAgentWeb;

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do your work
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            tv_title.setText(title);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        btn_nav_back.setOnClickListener(v -> onBackPressed());

        mAgentWeb = AgentWeb.with(this) // 传入Activity
                .setAgentWebParent(srl_web,
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT)) // 传入AgentWeb的父控件
                .useDefaultIndicator() // 使用默认进度条
                .setWebChromeClient(mWebChromeClient)
                .setAgentWebWebSettings(new AgentWebSettingsImpl() {
                    @Override
                    public WebSettings getWebSettings() {
                        WebSettings webSettings = super.getWebSettings();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            webSettings.setAllowFileAccessFromFileURLs(true);
                            webSettings.setAllowUniversalAccessFromFileURLs(true);
                        }
                        return webSettings;
                    }
                })
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra("url"));

        srl_web.setColorSchemeResources(R.color.colorPrimary);
        srl_web.setOnRefreshListener(this);
        srl_web.setOnChildScrollUpCallback((parent, child) -> mAgentWeb.getWebCreator().getWebView().getScrollY() > 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mAgentWeb.back()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        mAgentWeb.getUrlLoader().reload();
        srl_web.postDelayed(() -> srl_web.setRefreshing(false), 2000);
    }
}
