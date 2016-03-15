package com.livecentersample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livecenter.core.model.Match;
import com.livecentersample.activity.MainActivity;
import com.livecentersample.R;

import java.util.List;

/**
 *o - - - + - - - - o - + - - - - + - - - o
 *~ - - ~   Developed with love   ~ - - ~       /\_/\
 *~ - -  T H O U G H T   C H I M P ~ - -       ( ^.^ )
 *~ - - ~  www.thoughtchimp.com  ~ - - ~          ˜
 *o - - - + - - - - o - + - - - - + - - - o
 */
public class MatchListAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final MainActivity mainActivity;
    private final List<Match> matchList;

    private static final int LIVE_MATCH=0, OLD_MATCH=1;

    private OnMatchSelected onMatchSelected;

    public interface OnMatchSelected{
        void onMatchSelected(Match match);
    }

    public MatchListAdapter(List<Match> matchList, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.matchList = matchList;
    }

    public void setOnMatchSelected(OnMatchSelected onMatchSelected) {
        this.onMatchSelected = onMatchSelected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch ( viewType ) {
            case LIVE_MATCH:
                View v0 = inflater.inflate(R.layout.element_live_match, viewGroup, false);
                viewHolder = new ViewHolderLiveMatch(v0);
                break;
            case OLD_MATCH:
                View v1 = inflater.inflate(R.layout.element_old_match, viewGroup, false);
                viewHolder = new ViewHolderOldMatch(v1);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch ( holder.getItemViewType() ) {
            case LIVE_MATCH:
                ViewHolderLiveMatch viewHolderLiveMatch = (ViewHolderLiveMatch) holder;
                configureLiveMatch(viewHolderLiveMatch, position);
                break;
            case OLD_MATCH:
                ViewHolderOldMatch viewHolderOldMatch = (ViewHolderOldMatch) holder;
                configureOldMatch(viewHolderOldMatch, position);
                break;
        }
    }

    private void configureOldMatch(ViewHolderOldMatch holder, int position) {
        Match match = matchList.get(position);
        holder.mainView.setOnClickListener(this);
        holder.mainView.setTag(match);
        holder.matchDate.setText(match.getStartTime());
        holder.matchTitle.setText(match.getName());
    }

    private void configureLiveMatch(ViewHolderLiveMatch holder, int position) {
        Match match = matchList.get(position);
        holder.mainView.setOnClickListener(this);
        holder.mainView.setTag(match);
        holder.matchDate.setText(match.getStartTime());
        holder.matchTitle.setText(match.getName());
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(matchList.get(position).getIsLive())
            return LIVE_MATCH;
        else
            return OLD_MATCH;
    }

    @Override
    public void onClick(View v) {
        Match match = (Match) v.getTag();
        if(onMatchSelected != null)
            onMatchSelected.onMatchSelected(match);
    }

    private class ViewHolderOldMatch extends RecyclerView.ViewHolder {
        final TextView matchTitle, matchDate;
        final View mainView;
        public ViewHolderOldMatch(View view) {
            super(view);
            mainView = view.findViewById(R.id.main_view);
            matchDate = (TextView) view.findViewById(R.id.textView_matchDate);
            matchTitle = (TextView) view.findViewById(R.id.textView_matchTitle);
        }
    }

    private class ViewHolderLiveMatch extends RecyclerView.ViewHolder {
        final TextView matchTitle, matchDate;
        final View mainView;
        public ViewHolderLiveMatch(View view) {
            super(view);
            mainView = view.findViewById(R.id.main_view);
            matchDate = (TextView) view.findViewById(R.id.textView_matchDate);
            matchTitle = (TextView) view.findViewById(R.id.textView_matchTitle);
        }
    }

    private class RecyclerViewSimpleTextViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewSimpleTextViewHolder(View v) {
            super(v);
        }
    }
}
