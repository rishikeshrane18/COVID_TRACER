package com.example.covidtracer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<StateData> stateData = new ArrayList<>();
    public CustomAdapter (Context ctx,List<StateData>stateData){
        this.inflater = LayoutInflater.from(ctx);
        this.stateData = stateData;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        holder.stateName.setText(stateData.get(position).getStateName());
        holder.sccases.setText(stateData.get(position).getConfirmedCases());
        holder.sacases.setText(stateData.get(position).getActiveCases());
        holder.srcases.setText(stateData.get(position).getRecoveredCases());
        holder.sdcases.setText(stateData.get(position).getDeathCases());

    }

    @Override
    public int getItemCount() {
        return stateData != null?stateData.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView stateName,sccases,sacases,srcases,sdcases;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.stateName);
            sccases = itemView.findViewById(R.id.sccases);
            sacases = itemView.findViewById(R.id.sacases);
            srcases = itemView.findViewById(R.id.srcases);
            sdcases = itemView.findViewById(R.id.sdcases);
        }
    }
}
