package com.livecentersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.livecenter.core.api.LivecenterApi;
import com.livecenter.core.api.LivecenterService;
import com.livecenter.core.model.Goal;
import com.livecenter.core.model.Match;
import com.livecenter.core.util.Callback;
import com.livecenter.core.util.LivecenterException;
import com.livecenter.core.util.Result;

import java.util.List;

public class GoalActivity extends AppCompatActivity {

    private Match currentMatch;
    private GoalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        loadGoals();
    }

    private void loadGoals() {
        currentMatch = LiveCenterApp.getInstance().getCurrentMatch();
        LivecenterService service = (new LivecenterApi()).getLiveCenterService();
        service.getMatchGoals("" + currentMatch.getId(), new Callback<List<Goal>>() {
            @Override
            public void success(Result<List<Goal>> result) {
                initGoalUi(result.data);
            }

            @Override
            public void failure(LivecenterException error, int code) {

            }
        });
    }

    private void initGoalUi(List<Goal> data) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GoalAdapter(data, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
