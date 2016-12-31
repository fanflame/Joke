package com.ran.joke.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ran.joke.R;
import com.ran.joke.activity.adapter.NewRecycleAdpater;
import com.ran.joke.bean.JokeResult;
import com.ran.joke.utils.ToastUtil;
import com.ran.joke.utils.http.HttpFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanyiran on 16/12/31.
 */

public class FragmentNew extends Fragment implements HttpFactory.OnCompleteListener {
    @BindView(R.id.rvJokeRecyclerView)
    RecyclerView rvJokeRecyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private final int PAGE_SIZE = 20;
    private HttpFactory httpFactory;
    private int page;
    private NewRecycleAdpater adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int lastVisibleItem;

    public static FragmentNew getInstance() {
        FragmentNew fragmentNew = new FragmentNew();
        return fragmentNew;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new, container, false);
        ButterKnife.bind(this, root);
        initView();
        getJoke(page);
        return root;
    }

    private void initView() {
        swipeLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (adapter != null)
                            adapter.clear();
                        getJoke(++page);
                    }
                }
        );
        adapter = new NewRecycleAdpater();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvJokeRecyclerView.setLayoutManager(layoutManager);
        rvJokeRecyclerView.setAdapter(adapter);
        rvJokeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 >= adapter.getItemCount() - 5) {
                    swipeLayout.setRefreshing(true);
                    getJoke(++page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
            }
        });
    }

    private void getJoke(int page) {
        swipeLayout.setRefreshing(true);
        if (httpFactory == null)
            httpFactory = HttpFactory.getInstance(getString(R.string.appkey), this);
        httpFactory.getJoke(page, PAGE_SIZE);
    }

    @Override
    public void onFailure() {
        ToastUtil.show("获取失败");
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(JokeResult.JokeData jokeData) {
        swipeLayout.setRefreshing(false);
        adapter.addData(jokeData.getData());
    }
}
