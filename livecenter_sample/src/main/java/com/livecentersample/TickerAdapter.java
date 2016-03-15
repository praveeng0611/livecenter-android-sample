package com.livecentersample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livecenter.core.model.MatchTicker;

import java.util.List;

/**
 * Created by King on 14/03/16.
 */
public class TickerAdapter extends RecyclerView.Adapter {

    private final List<MatchTicker> matchTickerList;

    public TickerAdapter(List<MatchTicker> matchTickers, TickerActivity tickerActivity) {
        this.matchTickerList= matchTickers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.element_ticker_row, viewGroup, false);
        viewHolder = new ViewHolderTicker(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MatchTicker matchTicker = matchTickerList.get(position);
        ViewHolderTicker viewHolderTicker = (ViewHolderTicker) holder;
        viewHolderTicker.tickerTitle.setText(""+matchTicker.getTitle());
        viewHolderTicker.tickerType.setText(matchTicker.getType());
    }

    @Override
    public int getItemCount() {
        return matchTickerList.size();
    }

    private class ViewHolderTicker extends RecyclerView.ViewHolder {
        TextView tickerTitle , tickerType;
        public ViewHolderTicker(View view) {
            super(view);
            tickerTitle = (TextView) view.findViewById(R.id.textView_tickerTitle);
            tickerType = (TextView) view.findViewById(R.id.textView_tickerType);
        }
    }
}