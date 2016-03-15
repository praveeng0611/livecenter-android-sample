package com.livecentersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.livecenter.core.api.LivecenterApi;
import com.livecenter.core.api.LivecenterService;
import com.livecenter.core.model.Match;
import com.livecenter.core.model.MatchTicker;
import com.livecenter.core.util.Callback;
import com.livecenter.core.util.LivecenterException;
import com.livecenter.core.util.Result;

import java.util.List;

public class TickerActivity extends AppCompatActivity {

    private Match currentMatch;
    private TickerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);
        loadtickers();
    }

    private void loadtickers() {
        currentMatch = LiveCenterApp.getInstance().getCurrentMatch();
        LivecenterService service = (new LivecenterApi()).getLiveCenterService();
        service.getMatchTicker("" + currentMatch.getId(), new Callback<List<MatchTicker>>() {
            @Override
            public void success(Result<List<MatchTicker>> result) {
                initTickerUi(result.data);
            }

            @Override
            public void failure(LivecenterException error, int code) {

            }
        });
    }

    private void initTickerUi(List<MatchTicker> data) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TickerAdapter(data, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
