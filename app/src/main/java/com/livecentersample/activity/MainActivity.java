package com.livecentersample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.livecenter.core.api.LivecenterApi;
import com.livecenter.core.api.LivecenterService;
import com.livecenter.core.model.Match;
import com.livecenter.core.util.Callback;
import com.livecenter.core.util.LivecenterException;
import com.livecenter.core.util.Result;
import com.livecentersample.LiveCenterApp;
import com.livecentersample.R;
import com.livecentersample.adapter.MatchListAdapter;
import com.livecentersample.util.Constant;

import java.util.List;

/**
 *o - - - + - - - - o - + - - - - + - - - o
 *~ - - ~   Developed with love   ~ - - ~       /\_/\
 *~ - -  T H O U G H T   C H I M P ~ - -       ( ^.^ )
 *~ - - ~  www.thoughtchimp.com  ~ - - ~          ˜
 *o - - - + - - - - o - + - - - - + - - - o
 */
public class MainActivity extends AppCompatActivity implements MatchListAdapter.OnMatchSelected {

    List<Match> matchList;
    private MatchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMatchList();
    }

    private void loadMatchList() {
        LivecenterService service = (new LivecenterApi()).getLiveCenterService();
        service.getAllMatches(new Callback<List<Match>>() {
            @Override
            public void success(Result<List<Match>> result) {
                matchList = result.data;
                initMatchListUi();
            }

            @Override
            public void failure(LivecenterException error, int code) {
                Log.i(Constant.APP_TAG,error.getMessage());
            }
        });
    }

    private void initMatchListUi() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MatchListAdapter(matchList, this);
        adapter.setOnMatchSelected(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMatchSelected(Match match) {
        LiveCenterApp.getInstance().setCurrentMatch(match);
        startActivity(new Intent(this,MatchDetailActivity.class));
    }
}
