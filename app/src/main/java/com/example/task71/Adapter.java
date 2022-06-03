package com.example.task71;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task71.model.Model;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    Activity activity;
    List<Model> list;

    public Adapter(Context context, Activity activity, List<Model> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adverts_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.phone.setText(list.get(position).getPhone());
        holder.description.setText(list.get(position).getDescription());
        holder.date.setText(list.get(position).getDate());
        holder.location.setText(list.get(position).getLocation());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Display.class);
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("phone", list.get(position).getPhone());
                intent.putExtra("description", list.get(position).getDescription());
                intent.putExtra("date", list.get(position).getDate());
                intent.putExtra("location", list.get(position).getLocation());
                intent.putExtra("id", list.get(position).getId());
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, phone, description, date, location;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            phone = itemView.findViewById(R.id.contact);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            location = itemView.findViewById(R.id.location);
            layout = itemView.findViewById(R.id.recycler_layout);
        }
    }
}