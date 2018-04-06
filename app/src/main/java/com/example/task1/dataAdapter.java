package com.example.task1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.List;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder> implements Serializable{

    private List<apiRepo> mRepoList;
    Context context;

    public dataAdapter(List<apiRepo> repoList) {
        mRepoList = repoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        context = view.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(dataAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(mRepoList.get(position).getFlag()).
                apply(new RequestOptions().override(400, 400))
                .into(holder.mCountry);
        holder.mName.setText(mRepoList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCountry;
        private TextView mName;

        public ViewHolder(View view) {
            super(view);
            mCountry = (ImageView) view.findViewById(R.id.country);
            mName = (TextView) view.findViewById(R.id.name);

            mCountry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String s =  mRepoList.get(getLayoutPosition()).getFlag();
                    Intent mainIntent = new Intent(context, fullImageActivity.class);
                    mainIntent.putExtra("BUNDLE", s);
                    context.startActivity(mainIntent);
                }
            });
        }
    }
}