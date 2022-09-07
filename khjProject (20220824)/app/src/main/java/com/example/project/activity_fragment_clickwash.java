package com.example.project;

import static com.example.project.Common.CommonMethod.storeDTOS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project.ATask.MainClickWashSelect;
import com.example.project.Adapter.MainClickWashAdapter;
import com.example.project.DTO.MainWashDTO;
import com.example.project.DTO.StoreDTO;

import java.util.ArrayList;

public class activity_fragment_clickwash extends Fragment {

    RecyclerView WashClickRecycler;
    SwipeRefreshLayout swipeclick;

    ArrayList<StoreDTO> dtos;
    ArrayList<MainWashDTO> mainWashDTOS;
    MainClickWashAdapter adapter;

    TextView tv_place;

    StoreDTO storeDTO = null;
    String storeid = "";
    kkbMainActivity activity = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_fragment_clickwash, container, false);

        activity = (kkbMainActivity) getActivity();
        if(activity.storeDTO != null){
            storeDTO = activity.storeDTO;
            activity.storeDTO = null;
        }
        storeid = storeDTO.getStoreid();

        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = storeDTOS;
        mainWashDTOS = new ArrayList<>();

        tv_place = rootView.findViewById(R.id.tv_place);
        swipeclick = rootView.findViewById(R.id.swipeclick);

        tv_place.setText(storeDTO.getLocation());

        WashClickRecycler = rootView.findViewById(R.id.washClickRecycler);
        // recyclerView 에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (requireContext(), RecyclerView.VERTICAL, false);
        WashClickRecycler.setLayoutManager(layoutManager);


        adapter = new MainClickWashAdapter(getActivity(), mainWashDTOS);
        WashClickRecycler.setAdapter(adapter);

        MainClickWashSelect mainWashSelect = new MainClickWashSelect(mainWashDTOS, adapter, storeid);
        mainWashSelect.execute();

        swipeclick.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainClickWashSelect mainWashSelect = new MainClickWashSelect(mainWashDTOS, adapter, storeid);
                mainWashSelect.execute();

                swipeclick.setRefreshing(false);
            }
        });

        return rootView;
    }
}



