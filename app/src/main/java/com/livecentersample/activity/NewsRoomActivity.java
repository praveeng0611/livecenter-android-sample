package com.livecentersample.activity;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.livecenter.core.model.Match;
import com.livecenter.ui.LivecenterType;
import com.livecenter.ui.LivecenterView;
import com.livecenter.ui.LivecenterViewListener;
import com.livecentersample.LiveCenterApp;
import com.livecentersample.R;

public class NewsRoomActivity extends AppCompatActivity
        implements LivecenterViewListener, SwipeRefreshLayout.OnRefreshListener {

    private LivecenterView livecenterView;
    private Match currentMatch;
    private int currentCheckedId;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_room);
        setTitle("Newsroom");
        currentMatch = LiveCenterApp.getInstance().getCurrentMatch();
        livecenterView = (LivecenterView) findViewById(R.id.livecenterView);
        livecenterView.setLivecenterViewListener(this);
        changePreview(R.id.radio_live);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onClick(View view) {
        changePreview(view.getId());
    }

    private void changePreview(int checkedRadioId) {
        if(currentCheckedId == checkedRadioId)
            return;
        switch ( checkedRadioId ){
            case R.id.radio_live:
                currentCheckedId = checkedRadioId;
                livecenterView.loadLiveCenter("" + currentMatch.getId(), LivecenterType.LiveBlog, 10);
                break;
            case R.id.radio_social:
                currentCheckedId = checkedRadioId;
                livecenterView.loadLiveCenter("" + currentMatch.getId(), LivecenterType.Social, 10);
                break;
        }
    }

    @Override
    public void onLiveCenterViewLoadingStart() {
        showProgressDialog("Loading");
    }

    @Override
    public void onLiveCenterViewEmptyPage() {
        hideProgressDialog();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLiveCenterViewLoadingFinished() {
        hideProgressDialog();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLiveCenterRefreshed() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLiveCenterViewLoadingError(int errorCode, String message) {
        hideProgressDialog();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void showProgressDialog(String message){
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void hideProgressDialog(){
        if(progressDialog.isShowing())
            progressDialog.cancel();
    }

    @Override
    public void onRefresh() {
        livecenterView.loadNewFeed();
    }
}
