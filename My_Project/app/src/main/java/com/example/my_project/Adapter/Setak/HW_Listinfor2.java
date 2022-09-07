package com.example.my_project.Adapter.Setak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my_project.Adapter.HW_SetakAdapter;
import com.example.my_project.AsyncT.ListTask;
import com.example.my_project.AsyncT.StoreTask;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.DATA.HW_StoreDTO;
import com.example.my_project.R;

import java.util.ArrayList;


public class HW_Listinfor2 extends Fragment {
    HW_Listinformation homeActivity;
    Context context;
    RecyclerView listinfor2_recycler;
    TextView listinfro2_titlelocation;
    HW_Listinfor2Adapter adapter;
    ArrayList<HW_StoreDTO> dtos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_hw__listinfor2, container, false);
        homeActivity = (HW_Listinformation) getActivity();
        context = homeActivity.getApplicationContext();
        Intent intent = homeActivity.getIntent();
        HW_SetakDTO DTO = (HW_SetakDTO) intent.getSerializableExtra("DTO");
        String userid = intent.getStringExtra("userid");
        int store = DTO.getStoreid();
        dtos = new ArrayList<>();

        listinfro2_titlelocation = viewGroup.findViewById(R.id.listinfro2_titlelocation);
        listinfro2_titlelocation.setText(DTO.getTitlelocation()+" 현황");

        listinfor2_recycler = viewGroup.findViewById(R.id.listinfor2_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context, listinfor2_recycler.VERTICAL,false);
        listinfor2_recycler.setLayoutManager(layoutManager);

        adapter = new HW_Listinfor2Adapter(dtos,this.context);
        listinfor2_recycler.setAdapter(adapter);
        StoreTask storetask  = new StoreTask(dtos,adapter,userid,store);
        storetask.execute();




        return  viewGroup;
    }
}