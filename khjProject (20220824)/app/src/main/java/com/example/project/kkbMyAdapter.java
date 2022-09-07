package com.example.project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class kkbMyAdapter extends FragmentStateAdapter {

    public int mCount;

    public kkbMyAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new com.example.project.kkbFragment_1();
        else if(index==1) return new com.example.project.kkbFragment_2();
        else if(index==2) return new com.example.project.kkbFragment_3();
        else return new com.example.project.kkbFragment_4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }


}