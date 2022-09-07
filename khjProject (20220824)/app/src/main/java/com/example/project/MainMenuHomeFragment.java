package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.ATask.PS_SearchSelect;
import com.example.project.Adapter.PS_SearchAdapter;
import com.example.project.DTO.StoreDTO;

import java.util.ArrayList;

public class MainMenuHomeFragment extends Fragment {
    String TAG = "fragment:PS_Fragment1";

    Context ct;

    ImageView imageView;
    RecyclerView recyclerView;
    PS_SearchAdapter adapter;

    ArrayList<StoreDTO> dtos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main_menu_home, container, false);
        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.recyclerView);
        // recyclerView 에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (requireContext(), RecyclerView.VERTICAL, false);
//       recyclerView.setLayoutManager(layoutManager);
        // 어댑터 객체 생성
        adapter = new PS_SearchAdapter(getActivity(), dtos);
//        recyclerView.setAdapter(adapter);

        // 서버에 멤버들 ArrayList를 요구해서 가져온다 : AsyncTask 상속 받는 java
        PS_SearchSelect PSSearchSelect = new PS_SearchSelect(dtos, adapter);
        PSSearchSelect.execute();

        return rootView;
    }

    // 검색과 관련된 함수들
    public ArrayList<StoreDTO> getmExampleList(){
        return dtos;
    }

    public void editSetAdapter(ArrayList<StoreDTO> filteredList){
        adapter = new PS_SearchAdapter(getContext(),filteredList);
        recyclerView.setAdapter(adapter);
    }
}
