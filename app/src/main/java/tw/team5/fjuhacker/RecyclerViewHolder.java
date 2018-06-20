package tw.team5.fjuhacker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    public TextView mTitle, mCategory, mSpeaker, mOrganizer, mParticipant;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.tvTitle);
        mCategory = itemView.findViewById(R.id.tvCategory);
        mSpeaker = itemView.findViewById(R.id.tvSpeaker);
        mOrganizer = itemView.findViewById(R.id.tvOrganizer);
        mParticipant = itemView.findViewById(R.id.tvParticipant);
    }
}
