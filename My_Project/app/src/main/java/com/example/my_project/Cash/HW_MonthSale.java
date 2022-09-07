package com.example.my_project.Cash;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_project.Adapter.HW_NewCashMonthAdapter;
import com.example.my_project.DATA.HW_CashDTO;
import com.example.my_project.HW_Home;
import com.example.my_project.R;

import java.sql.Date;
import java.util.ArrayList;


public class HW_MonthSale extends Fragment {
    ArrayList<HW_CashDTO> dtos;
    Context context;
    HW_Home homeActivity;
    RecyclerView monthRecycle;
    HW_NewCashMonthAdapter monFrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_fragment_monthsale,container,false);
        String userid = getArguments().getString("userid");
        Bundle bundle = new Bundle();
        bundle.putString("userid",userid);
        homeActivity = (HW_Home) getActivity();
        context = homeActivity.getApplicationContext();


        monFrag = new HW_NewCashMonthAdapter(this.context,userid);
        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.monFrag, monFrag).commit();

//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-01")));
//        monFrag.addDTO(new HW_CashDTO(275000,Date.valueOf("2022-07-02")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-03")));
//        monFrag.addDTO(new HW_CashDTO(175000,Date.valueOf("2022-07-04")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-05")));
//        monFrag.addDTO(new HW_CashDTO(275000,Date.valueOf("2022-07-06")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-07")));
//        monFrag.addDTO(new HW_CashDTO(75000,Date.valueOf("2022-07-08")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-09")));
//        monFrag.addDTO(new HW_CashDTO(275000,Date.valueOf("2022-07-10")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-11")));
//        monFrag.addDTO(new HW_CashDTO(75000,Date.valueOf("2022-07-12")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-13")));
//        monFrag.addDTO(new HW_CashDTO(275000,Date.valueOf("2022-07-14")));
//        monFrag.addDTO(new HW_CashDTO(130000,Date.valueOf("2022-07-15")));
//        monFrag.addDTO(new HW_CashDTO(175000,Date.valueOf("2022-07-16")));

        return viewgroup;
    }
}
