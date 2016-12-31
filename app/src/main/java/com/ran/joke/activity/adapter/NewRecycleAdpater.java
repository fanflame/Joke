package com.ran.joke.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ran.joke.R;
import com.ran.joke.bean.JokeResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanyiran on 16/12/31.
 */

public class NewRecycleAdpater extends RecyclerView.Adapter<NewRecycleAdpater.Holder> {
    private ArrayList<JokeResult.JokeData.Joke> mJokes;
    private LayoutInflater layoutInflater;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        Holder holder = new Holder(layoutInflater.inflate(R.layout.item_new,null, false));
        return holder;
    }

    @Override
    public int getItemCount() {
        return mJokes == null ? 0 : mJokes.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        JokeResult.JokeData.Joke joke = mJokes.get(position);
//        holder.tvTime.setText(joke.getUpdatetime());
        holder.tvContent.setText(joke.getContent().replace("　　","\n").replace("&nbsp;",""));
    }

    public void addData(List<JokeResult.JokeData.Joke> jokes){
        if(mJokes == null){
            mJokes = new ArrayList<>();
        }
        mJokes.addAll(jokes);
        notifyDataSetChanged();
    }

    public void clear(){
        if(mJokes != null){
            mJokes.clear();
            notifyDataSetChanged();
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tvTime)
//        TextView tvTime;
        @BindView(R.id.tvContent)
        TextView tvContent;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
