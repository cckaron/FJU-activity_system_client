package tw.team5.fjuhacker;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tw.team5.Model.Activity;
import tw.team5.Model.Facebook;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewHolder2>{

    MainActivity2 mainActivity;
    ArrayList<Facebook> mActivity;

    public RecyclerViewAdapter2(MainActivity2 mainActivity, ArrayList<Facebook> mActivity) {
        this.mainActivity = mainActivity;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.single_row2, parent, false);

        return new RecyclerViewHolder2(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder2 holder, int position) {

        holder.mPost_ID.setText(mActivity.get(position).getPost_ID());
        holder.mPost_content.setText(mActivity.get(position).getPost_content());
        holder.mPost_createTime.setText("發文時間" + mActivity.get(position).getPost_createTime());


    }

    @Override
    public int getItemCount() {
        return mActivity.size();
    }
}
