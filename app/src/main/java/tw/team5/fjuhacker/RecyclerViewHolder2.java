package tw.team5.fjuhacker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecyclerViewHolder2 extends RecyclerView.ViewHolder{

    public TextView mPost_ID, mPost_content, mPost_createTime;

    public RecyclerViewHolder2(View itemView) {
        super(itemView);

        mPost_ID = itemView.findViewById(R.id.tvID);
        mPost_content = itemView.findViewById(R.id.tvContent);
        mPost_createTime = itemView.findViewById(R.id.tvCreateTime);

    }
}
