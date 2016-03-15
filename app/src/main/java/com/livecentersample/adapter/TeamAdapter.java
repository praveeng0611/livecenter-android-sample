package com.livecentersample.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livecenter.core.model.MatchData;
import com.livecenter.core.model.Player;
import com.livecentersample.R;
import com.livecentersample.activity.MatchDetailActivity;
import com.livecentersample.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

/**
 * Created by King on 14/03/16.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> implements StickyHeaderAdapter<TeamAdapter.HeaderHolder> {

    private final MatchData matchData;
    private final LayoutInflater mInflater;
    List<Player> playerList;
    int teamOneOnFieldPlayerCount;

    public TeamAdapter(MatchDetailActivity matchDetailActivity, MatchData matchData) {
        this.matchData= matchData;
        mInflater = LayoutInflater.from(matchDetailActivity);
        mergeOnFieldPlayerList();
    }

    private void mergeOnFieldPlayerList() {
        playerList = new ArrayList<>();
        for(Player player : matchData.getTeamOne().getPlayerData().getOnFieldPlayerList()){
            playerList.add(player);
        }
        teamOneOnFieldPlayerCount = playerList.size();
        for(Player player: matchData.getTeamTwo().getPlayerData().getOnFieldPlayerList()){
            playerList.add(player);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.item_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.playerName.setText(player.getFirstName()+" "+player.getLastName()+" ("+player.getNumber()+")");
        holder.playerPosition.setText(""+player.getPosition());
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    @Override
    public long getHeaderId(int position) {
        if(position <teamOneOnFieldPlayerCount){
                return 0;
        }else {
            return 1;
        }
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header_team, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder holder, int position) {
        if(getHeaderId(position) == 0){
            String colorOne  =  matchData.getTeamOne().getColor();
            holder.view.setBackgroundColor(Color.parseColor(colorOne));
            holder.teamName.setText(matchData.getTeamOne().getName());
            holder.teamFormation.setText(matchData.getTeamOne().getFormation());
            holder.teamName.setTextColor(ColorUtil.getContrastColor(colorOne));
            holder.teamFormation.setTextColor(ColorUtil.getContrastColor(colorOne));
        }else {
            String colorTwo  =  matchData.getTeamTwo().getColor();
            holder.view.setBackgroundColor(Color.parseColor(colorTwo));
            holder.teamName.setText(matchData.getTeamTwo().getName());
            holder.teamFormation.setText(matchData.getTeamTwo().getFormation());
            holder.teamName.setTextColor(ColorUtil.getContrastColor(colorTwo));
            holder.teamFormation.setTextColor(ColorUtil.getContrastColor(colorTwo));
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName,playerPosition;
        public ViewHolder(View itemView) {
            super(itemView);
            playerName = (TextView) itemView.findViewById(R.id.textView_team_player_name_and_number);
            playerPosition = (TextView) itemView.findViewById(R.id.textView_team_player_position);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView teamName, teamFormation;
        public View view;
        public HeaderHolder(View itemView) {
            super(itemView);
            teamName = (TextView) itemView.findViewById(R.id.textView_team_name);
            teamFormation = (TextView) itemView.findViewById(R.id.textView_team_formation);
            view = itemView.findViewById(R.id.header_main);
        }
    }
}
