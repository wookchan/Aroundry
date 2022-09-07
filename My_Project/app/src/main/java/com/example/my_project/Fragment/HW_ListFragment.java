package com.example.my_project.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_project.Adapter.HW_SetakAdapter;
import com.example.my_project.AsyncT.ListTask;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.HW_Home;
import com.example.my_project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HW_ListFragment extends Fragment {
    private static final String TAG = "리스트 프래그먼트";

    HW_SetakAdapter adapter;
    ArrayList<HW_SetakDTO> dtos;
    RecyclerView homerecycle;
    HW_Home homeActivity;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_fragment_list,container,false);
        String userid = getArguments().getString("id");
        Bundle bundle = new Bundle();
        bundle.putString("userid",userid);
        homeActivity = (HW_Home) getActivity();
        dtos = new ArrayList<>();



        context = homeActivity.getApplicationContext();
        // 리사이클러뷰 초기화
        homerecycle = viewgroup.findViewById(R.id.homerecycle);
        Log.d(TAG, "의 : dtos ");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context, homerecycle.VERTICAL,false);
        homerecycle.setLayoutManager(layoutManager);
        
        adapter = new HW_SetakAdapter(this.context,dtos,userid);
        homerecycle.setAdapter(adapter);

        //임시로 만들어둔 dto 글자수 제한이 필요함
//        adapter.addDTO(new HW_SetakDTO("NoImage","광주 광역시 서구 농성동 경영로 33",3,"CCTV,화장실,오락시설,와이파이","광주 서구청점"));
//        adapter.addDTO(new HW_SetakDTO("R.drawable.dnfguardcrash","광주 광역시 북구 문흥동 서하로 12",4,"와이파이,CCTV,노래방,콘센트,화장실","광주 북구 문흥점"));
//        adapter.addDTO(new HW_SetakDTO("R.drawable.dmc5blackhall","광주 광역시 북구 중흥동 경양로 2",1,"CCTV,화장실","광주 북구 중흥 1동점"));


        ListTask listTask = new ListTask(dtos,adapter,userid);
        listTask.execute();

        //adapter.notifyDataSetChanged();




        return viewgroup;
    }
}