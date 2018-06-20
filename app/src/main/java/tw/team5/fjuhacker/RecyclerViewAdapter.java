package tw.team5.fjuhacker;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tw.team5.Model.Activity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    MainActivity mainActivity;
    ArrayList<Activity> mActivity;

    public RecyclerViewAdapter(MainActivity mainActivity, ArrayList<Activity> mActivity) {
        this.mainActivity = mainActivity;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);

        return new RecyclerViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.mTitle.setText(mActivity.get(position).getAct_name());
        holder.mParticipant.setText("建議參加對象:" + mActivity.get(position).getAct_participant());
        holder.mOrganizer.setText(mActivity.get(position).getAct_organizer());
        holder.mCategory.setText("類型:" + mActivity.get(position).getAct_category());
        holder.mSpeaker.setText("演講者:" + mActivity.get(position).getAct_speaker());

    }

    @Override
    public int getItemCount() {
        return mActivity.size();
    }
}
