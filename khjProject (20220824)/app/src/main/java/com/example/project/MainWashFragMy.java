package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project.ATask.MainMyWashSelect;
import com.example.project.Adapter.MainWashMyAdapter;
import com.example.project.DTO.MainMyWashDTO;

import java.util.ArrayList;

public class MainWashFragMy extends Fragment {

    RecyclerView mainWashMyRecycler;
    SwipeRefreshLayout swipemy;
    TextView tv_money;

    ArrayList<MainMyWashDTO> mainMyWashDTOS;
    MainWashMyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_wash_myrecycler, container, false);

        mainWashMyRecycler = rootView.findViewById(R.id.mainWashMyRecycler);
        swipemy = rootView.findViewById(R.id.swipemy);
        tv_money = rootView.findViewById(R.id.tv_money);

        mainMyWashDTOS = new ArrayList<>();

        // recyclerView 에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (requireContext(), RecyclerView.VERTICAL, false);
        mainWashMyRecycler.setLayoutManager(layoutManager);

        // 어댑터 객체 생성
        adapter = new MainWashMyAdapter(getActivity(), mainMyWashDTOS);
        mainWashMyRecycler.setAdapter(adapter);

        MainMyWashSelect mainMyWashSelect = new MainMyWashSelect(mainMyWashDTOS, adapter, loginDTO.getUserid());
        mainMyWashSelect.execute();

        tv_money.setText(loginDTO.getPoint());

        swipemy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainMyWashSelect mainMyWashSelect = new MainMyWashSelect(mainMyWashDTOS, adapter, loginDTO.getUserid());
                mainMyWashSelect.execute();

                swipemy.setRefreshing(false);
            }
        });


        return rootView;

    }



}
