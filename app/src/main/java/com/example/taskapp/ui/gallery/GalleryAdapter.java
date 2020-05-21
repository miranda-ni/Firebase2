package com.example.taskapp.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.ui.OnItemClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>  {

    public ArrayList <File> arrayList;

    public OnItemClickListener onItemClickListener;

    public GalleryAdapter(ArrayList<File> list) {
        this.arrayList=list;
//        notifyDataSetChanged();

    }

        @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gallery,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView textView;

       public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.gallery_textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());

                }
            });
        }

        public void bind(File file) {
            textView.setText(file.getName());

        }
    }
}

