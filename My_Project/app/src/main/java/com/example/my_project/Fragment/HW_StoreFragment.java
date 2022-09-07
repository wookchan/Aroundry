package com.example.my_project.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_project.HW_Home;
import com.example.my_project.R;


public class HW_StoreFragment extends Fragment {
    HW_Home homeActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_fragment_store,container,false);
        homeActivity = (HW_Home) getActivity();
        String userid = getArguments().getString("id");
        Bundle bundle = new Bundle();
        bundle.putString("userid",userid);
        return viewgroup;
    }
}