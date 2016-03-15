package com.livecentersample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livecenter.core.model.Goal;
import com.livecentersample.activity.GoalActivity;
import com.livecentersample.R;

import java.util.List;

/**
 *o - - - + - - - - o - + - - - - + - - - o
 *~ - - ~   Developed with love   ~ - - ~       /\_/\
 *~ - -  T H O U G H T   C H I M P ~ - -       ( ^.^ )
 *~ - - ~  www.thoughtchimp.com  ~ - - ~          ˜
 *o - - - + - - - - o - + - - - - + - - - o
 */
public class GoalAdapter extends RecyclerView.Adapter {

    private final List<Goal> goalList;

    public GoalAdapter(List<Goal> goalList, GoalActivity goalActivity) {
        this.goalList = goalList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.element_goal_row, viewGroup, false);
        viewHolder = new ViewHolderGoal(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Goal goal = goalList.get(position);
        ViewHolderGoal viewHolderGoal = (ViewHolderGoal) holder;
        viewHolderGoal.goalOwnerAndTime.setText(goal.getPlayer().getFirstName()+" "
                +goal.getPlayer().getLastName()+" ("+goal.getOnMinute()+")");
        viewHolderGoal.goalType.setText(goal.getType());
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }

    private class ViewHolderGoal extends RecyclerView.ViewHolder {
        TextView goalOwnerAndTime , goalType;
        public ViewHolderGoal(View view) {
            super(view);
            goalOwnerAndTime = (TextView) view.findViewById(R.id.textView_goalOwner_and_time);
            goalType = (TextView) view.findViewById(R.id.textView_goalType);
        }
    }
}
