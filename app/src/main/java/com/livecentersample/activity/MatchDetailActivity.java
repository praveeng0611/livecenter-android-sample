package com.livecentersample.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.livecenter.core.api.LivecenterApi;
import com.livecenter.core.api.LivecenterService;
import com.livecenter.core.model.Match;
import com.livecenter.core.model.MatchData;
import com.livecenter.core.util.Callback;
import com.livecenter.core.util.LivecenterException;
import com.livecenter.core.util.Result;
import com.livecentersample.LiveCenterApp;
import com.livecentersample.R;
import com.livecentersample.adapter.TeamAdapter;
import com.livecentersample.util.ColorUtil;
import com.livecentersample.util.Constant;

import ca.barrenechea.widget.recyclerview.decoration.DividerDecoration;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

/**
 *o - - - + - - - - o - + - - - - + - - - o
 *~ - - ~   Developed with love   ~ - - ~       /\_/\
 *~ - -  T H O U G H T   C H I M P ~ - -       ( ^.^ )
 *~ - - ~  www.thoughtchimp.com  ~ - - ~          ˜
 *o - - - + - - - - o - + - - - - + - - - o
 */
public class MatchDetailActivity extends AppCompatActivity {

    MatchData matchData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        loadMatchData();
    }

    private void loadMatchData() {
        Match currentMatch = LiveCenterApp.getInstance().getCurrentMatch();
        LivecenterService service = (new LivecenterApi()).getLiveCenterService();
        service.getMatchData(""+currentMatch.getId(), new Callback<MatchData>() {
            @Override
            public void success(Result<MatchData> result) {
                matchData = result.data;
                initUi();
            }

            @Override
            public void failure(LivecenterException error, int code) {
                Log.i(Constant.APP_TAG,error.getMessage());
            }
        });
    }

    private void initUi() {
        ((TextView) findViewById(R.id.textView_team1_name)).setText(matchData.getTeamOne().getName());
        ((TextView) findViewById(R.id.textView_team2_name)).setText(matchData.getTeamTwo().getName());
        Glide.with(this)
                .load(matchData.getTeamOne().getLogo())
                .placeholder(R.color.lightGrey)
                .into((ImageView) findViewById(R.id.imageView_team1_flag));
        Glide.with(this)
                .load(matchData.getTeamTwo().getLogo())
                .placeholder(R.color.lightGrey)
                .into((ImageView) findViewById(R.id.imageView_team2_flag));
        ((TextView) findViewById(R.id.textView_team1_score))
                .setText(""+matchData.getMatchScore().getTeamOneScore());

        ((TextView) findViewById(R.id.textView_team1_score))
                .setTextColor(ColorUtil.getContrastColor(matchData.getTeamOne().getColor()));

        findViewById(R.id.textView_team1_score)
                .setBackgroundColor(Color.parseColor(matchData.getTeamOne().getColor()));

        ((TextView) findViewById(R.id.textView_team2_score))
                .setText(""+matchData.getMatchScore().getTeamTwoScore());

        ((TextView) findViewById(R.id.textView_team2_score))
                .setTextColor(ColorUtil.getContrastColor(matchData.getTeamTwo().getColor()));

        findViewById(R.id.textView_team2_score)
                .setBackgroundColor(Color.parseColor(matchData.getTeamTwo().getColor()));

        showPlayerList();
    }

    public void showPlayerList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.lightGrey)
                .build();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(divider);
        final TeamAdapter adapter = new TeamAdapter(this, matchData);
        StickyHeaderDecoration decor = new StickyHeaderDecoration(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(decor, 1);
    }

    public void openNewsRoom(View view) {
        startActivity(new Intent(this, NewsRoomActivity.class));
    }

    public void loadTicker(View view) {
        startActivity(new Intent(this, TickerActivity.class));
    }

    public void loadGoal(View view) {
        startActivity(new Intent(this, GoalActivity.class));
    }
}
