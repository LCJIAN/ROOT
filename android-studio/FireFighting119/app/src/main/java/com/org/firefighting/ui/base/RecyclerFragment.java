package com.org.firefighting.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lcjian.lib.recyclerview.EmptyAdapter;
import com.lcjian.lib.recyclerview.LoadMoreAdapter;
import com.org.firefighting.App;
import com.org.firefighting.R;
import com.org.firefighting.data.entity.PageResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public abstract class RecyclerFragment<T> extends BaseFragment {

    @BindView(R.id.recycler_view)
    protected RecyclerView recycler_view;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipe_refresh_layout;

    private EmptyAdapter mEmptyAdapter;
    private LoadMoreAdapter mLoadMoreAdapter;
    private RecyclerView.Adapter<? extends RecyclerView.ViewHolder> mAdapter;

    private List<T> mData;
    private List<T> mPrePageData;

    private Observable<PageResult<T>> mObservable;

    private Disposable mDisposable;

    private int mCurrentPage;

    private int mPrePage;

    private Unbinder mUnBinder;

    public abstract RecyclerView.Adapter<? extends RecyclerView.ViewHolder> onCreateAdapter(List<T> data);

    public abstract Observable<PageResult<T>> onCreatePageObservable(int currentPage);

    public abstract void notifyDataChanged(List<T> data);

    protected int getLayoutResource() {
        return R.layout.fragment_recycler;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new ArrayList<>();
        mCurrentPage = 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAdapter = onCreateAdapter(new ArrayList<>());
        mLoadMoreAdapter = new LoadMoreAdapter(mAdapter);

        swipe_refresh_layout.setOnRefreshListener(this::refresh);

        View loadFooter = LayoutInflater.from(getActivity()).inflate(R.layout.loading_footer, recycler_view, false);
        View errorFooter = LayoutInflater.from(getActivity()).inflate(R.layout.error_footer, recycler_view, false);
        mLoadMoreAdapter.setLoadingView(loadFooter).setErrorView(errorFooter);
        mLoadMoreAdapter.setThreshold(2);
        mLoadMoreAdapter.setOnLoadMoreListener(() -> {
            createNewObservable();
            getData();
        });
        onLoadMoreAdapterCreated(mLoadMoreAdapter);
        mEmptyAdapter = new EmptyAdapter(mLoadMoreAdapter);
        onEmptyAdapterCreated(mEmptyAdapter);
        mEmptyAdapter.hideEmptyView();
        recycler_view.setAdapter(mEmptyAdapter);
        recycler_view.setHasFixedSize(true);

        if (mObservable == null) {
            createNewObservable();
        }
        getData();
    }

    protected void onEmptyAdapterCreated(EmptyAdapter emptyAdapter) {

    }

    protected void onEmptyViewShow(boolean error) {

    }

    protected void onLoadMoreAdapterCreated(LoadMoreAdapter loadMoreAdapter) {

    }

    private void createNewObservable() {
        mObservable = onCreatePageObservable(mCurrentPage);
        mPrePage = mCurrentPage;
    }

    private void getData() {
        if (mObservable == null) {
            return;
        }
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        if (mCurrentPage == 1) {
            setRefreshing(true);
            mLoadMoreAdapter.setState(LoadMoreAdapter.STATE_DEFAULT);
            mLoadMoreAdapter.setEnabled(false);
        } else {
            mLoadMoreAdapter.setState(LoadMoreAdapter.STATE_LOADING);
        }
        mDisposable = mObservable
                .subscribe(tPageResult -> {
                    mLoadMoreAdapter.setState(mPrePage < tPageResult.total_pages ? LoadMoreAdapter.STATE_DEFAULT : LoadMoreAdapter.STATE_END);
                    if (mCurrentPage == 1) {
                        setRefreshing(false);
                    }

                    if (tPageResult.total_pages == 0) {
                        mData.clear();
                    } else if (mCurrentPage <= tPageResult.total_pages && mCurrentPage == mPrePage) {
                        if (mCurrentPage == 1) {
                            mData.clear();
                            mData.addAll(tPageResult.elements);
                            mLoadMoreAdapter.setEnabled(true);
                        } else {
                            mData.addAll(tPageResult.elements);
                        }

                        mPrePageData = tPageResult.elements;
                        mCurrentPage++;
                    } else if (mPrePage == tPageResult.page_number) {
                        if (mPrePageData != null) {
                            mData.removeAll(mPrePageData);
                            mData.addAll(tPageResult.elements);
                        }
                        mPrePageData = tPageResult.elements;
                    }
                    notifyDataChanged(new ArrayList<>(mData));
                    onEmptyViewShow(false);
                    mEmptyAdapter.showEmptyView();
                }, throwable -> {
                    if (mCurrentPage == 1) {
                        setRefreshing(false);
                        mData.clear();
                        notifyDataChanged(new ArrayList<>(mData));
                        onEmptyViewShow(true);
                        mEmptyAdapter.showEmptyView();
                        mObservable = null;
                    } else {
                        mLoadMoreAdapter.setState(LoadMoreAdapter.STATE_ERROR);
                    }
                    Toast.makeText(App.getInstance(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    protected void refresh() {
        mCurrentPage = 1;
        createNewObservable();
        getData();
    }

    protected void setRefreshing(final boolean refreshing) {
        if (swipe_refresh_layout.isEnabled()) {
            swipe_refresh_layout.post(() -> {
                if (swipe_refresh_layout != null) {
                    swipe_refresh_layout.setRefreshing(refreshing);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        mEmptyAdapter = null;
        mLoadMoreAdapter = null;
        mAdapter = null;
        recycler_view.setAdapter(null);
        mUnBinder.unbind();
    }
}
