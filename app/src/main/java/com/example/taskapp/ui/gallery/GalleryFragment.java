package com.example.taskapp.ui.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    private ArrayList<File> list = new ArrayList<>();
    private GalleryAdapter galleryAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_gallery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        galleryAdapter = new GalleryAdapter(list);
        recyclerView.setAdapter(galleryAdapter);
        getPermission();


    }
    private void getPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            getFiles();
            Log.e("TAG", "getPermission: get files");
        }else {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
            Log.e("TAG", "getPermission: else" );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101 ){
            getPermission();}

    }

    private void getFiles(){
        File folder = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera");

//      if (!folder.exists())folder.mkdir();
        for (File file: folder.listFiles()){
            Log.e("TAG", "getFiles: " + file.getAbsolutePath());
            list.add(file);
            Log.e("TAG", "getFiles: list adding");
            galleryAdapter.notifyDataSetChanged();
        }
    }


    }

