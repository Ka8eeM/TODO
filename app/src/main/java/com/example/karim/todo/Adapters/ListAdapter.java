package com.example.karim.todo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karim.todo.Activities.TaskDetails;
import com.example.karim.todo.DataBaseModels.ProjectDB;
import com.example.karim.todo.DataBaseModels.Task;
import com.example.karim.todo.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<Task> tasks;
    Context context;
    private LayoutInflater inflater;

    public ListAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view, context, tasks);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Task task = tasks.get(position);
        holder.taskName.setText(task.getTaskName());
        holder.deleteItem.setImageResource(R.drawable.delete_icon);
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(ListAdapter.class.getSimpleName(), String.valueOf(getItemCount()) + " the size is here!");
                Log.e(ListAdapter.class.getSimpleName(), String.valueOf(position) + " i hope it works");
                int currPos = tasks.indexOf(task);
                tasks.remove(currPos);
                notifyItemRemoved(currPos);
                ProjectDB db = ProjectDB.getAppDatabase(context);
                db.taskDao().deleteTask(task);
                Toast.makeText(context, "Item is removed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteItem;
        TextView taskName;
        List<Task> tasks;
        Context context;

        public MyViewHolder(View itemView, Context context, List<Task> tasks) {
            super(itemView);
            this.context = context;
            this.tasks = tasks;
            taskName = itemView.findViewById(R.id.item_name);
            deleteItem = itemView.findViewById(R.id.delete_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyViewHolder.this.context, TaskDetails.class);
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        intent.setData(Uri.parse(String.valueOf(pos)));
                        Log.e(ListAdapter.class.getSimpleName(), String.valueOf(pos) + " what happened?");
                        MyViewHolder.this.context.startActivity(intent);
                    }
                }
            });
        }
    }
}