package com.example.taskapp.ui.onboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {
    LottieAnimationView lottieAnimationView;
    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textTitle1 = view.findViewById(R.id.textTitle1);
        TextView textView2 = view.findViewById(R.id.textTitle2);
        Button buttonStarted = view.findViewById(R.id.buttonStarted);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);

        int pos = getArguments().getInt("pos");
        switch (pos){
            case 0:
                lottieAnimationView.setAnimation(R.raw.shopcontent);
                textTitle1.setText("SHOP");
                textView2.setText("Select from different shops! The Freedom is yours!");
                buttonStarted.setVisibility(View.INVISIBLE);
                break;

                case 1:
                    lottieAnimationView.setAnimation(R.raw.search);
                    textTitle1.setText("SEARCH");
                    textView2.setText("Search among 1 million products. The Choice is yours");
                    buttonStarted.setVisibility(View.INVISIBLE);
                    break;
            case 2:
                lottieAnimationView.setAnimation(R.raw.fast);
                textTitle1.setText("FAST");
                textView2.setText("Super Fast Delivery! Right ar your door");
                break;
        }

                buttonStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveIsShown();
                       Intent intent = new Intent(getActivity(), MainActivity.class);
                       startActivity(intent);
                        getActivity().finish();
                    }
                });
        }



    private void saveIsShown() {
        SharedPreferences preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("isShown",true).apply();
    }
}

