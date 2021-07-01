package com.fetch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class dAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<DataIntilization> dataList;
        private MainActivity mainActiv;

        public dAdapter(List<DataIntilization> dataList, MainActivity mainActiv) {
            this.dataList = dataList;
            this.mainActiv = mainActiv;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout, parent, false);

            itemView.setOnClickListener(mainActiv);
            itemView.setOnLongClickListener(mainActiv);

            return new ViewHolder(itemView);
        }



        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataIntilization edit = dataList.get(position);

        holder.Id.setText(String.valueOf(edit.getId()));
        holder.listID.setText(String.valueOf(edit.getListID()));
        holder.name.setText(edit.getName());



        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }


