package com.example.taskapp.ui.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.taskapp.R;
import com.google.android.material.tabs.TabLayout;

public class OnBoardActivity extends AppCompatActivity {
   private Button skip;
    private int i;
   private TabLayout tabLayout;
  private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        skip = findViewById(R.id.skip);
        tabLayout = findViewById(R.id.tabDOts);
        tabLayout.setupWithViewPager(viewPager,true);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0){
                    viewPager.setCurrentItem(2);
//                    skip.setVisibility(view.INVISIBLE);
                    Log.e("TAG", "onClick: open to board" );
                }
            }
        });

    }

    public void onClick(View view) {
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("pos",position);
            BoardFragment fragment = new BoardFragment();
            fragment.setArguments(bundle);


            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
