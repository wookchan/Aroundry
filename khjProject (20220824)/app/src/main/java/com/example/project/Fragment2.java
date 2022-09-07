package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.ATask.PS_SearchSelect3;
import com.example.project.Adapter.PS_SearchAdapter;
import com.example.project.DTO.StoreDTO;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    Context ct;
    RecyclerView recyclerView2;
    PS_SearchAdapter adapter2;

    ArrayList<StoreDTO> dtos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.ps_fragment2,
                container, false);

        String userid = loginDTO.getUserid();

        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = new ArrayList<>();

        recyclerView2 = viewGroup.findViewById(R.id.recyclerView2);
        // recyclerView 에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (requireContext(), RecyclerView.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        // 어댑터 객체 생성
        adapter2 = new PS_SearchAdapter(getActivity(), dtos);
        recyclerView2.setAdapter(adapter2);

        // 서버에 멤버들 ArrayList를 요구해서 가져온다 : AsyncTask 상속 받는 java
        PS_SearchSelect3 PSSearchSelect = new PS_SearchSelect3(dtos, adapter2, userid);
        PSSearchSelect.execute();
        return viewGroup;
    }

    // 검색과 관련된 함수들
    public ArrayList<StoreDTO> getmExampleList(){
        return dtos;
    }

    public void editSetAdapter(ArrayList<StoreDTO> filteredList){
        adapter2 = new PS_SearchAdapter(getContext(),filteredList);
        recyclerView2.setAdapter(adapter2);
    }
}
