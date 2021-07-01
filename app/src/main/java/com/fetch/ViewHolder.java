package com.fetch;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView Id,listID,name;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        Id=itemView.findViewById(R.id.identity);
        listID=itemView.findViewById(R.id.lID);
        name = itemView.findViewById(R.id.names);
    }
}
