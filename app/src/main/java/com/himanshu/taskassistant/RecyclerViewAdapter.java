package com.himanshu.taskassistant;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
/*
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.View_Holder>{
    ArrayList<Data> mList;
    Context context;
    Activity activity;
    DatabaseHelper databaseHelper;
    public static class View_Holder extends RecyclerView.ViewHolder{
        public TextView mTitle, mDescription, mDateTime;
        public ImageView mDelete, mCompleted;
        public View_Holder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.titleText);
            mDescription = itemView.findViewById(R.id.descriptionText);
            mDateTime = itemView.findViewById(R.id.timeStamp);
            mDelete = itemView.findViewById(R.id.btnDelete);
            mCompleted = itemView.findViewById(R.id.btnCheck);

        }
    }

    public RecyclerViewAdapter(ArrayList<Data> exampleList)
    {
        mList = exampleList;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        View_Holder vh = new View_Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        Data data = mList.get(position);
        holder.mTitle.setText(data.title);
        holder.mDescription.setText(data.description);
        holder.mDateTime.setText(data.dateTime.toString());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    mList.remove(position);
                    notifyItemRemoved(position);
                }
                catch (IndexOutOfBoundsException e)
                {
                    mList.remove(0);
                    notifyItemRemoved(0);
                }
            }
        });
        holder.mCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    mList.remove(position);
                    notifyItemRemoved(position);
                }
                catch (IndexOutOfBoundsException e)
                {
                    mList.remove(0);
                    notifyItemRemoved(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
*/