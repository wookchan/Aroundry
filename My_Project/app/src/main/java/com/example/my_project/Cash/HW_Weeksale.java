package com.example.my_project.Cash;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_project.Adapter.HW_NewCashWeekAdapter;
import com.example.my_project.Fragment.HW_CashFragment;
import com.example.my_project.HW_Home;
import com.example.my_project.R;


public class HW_Weeksale extends Fragment {
    private static final String TAG = "hw_weeksale 로부터";
    //ArrayList<HW_CashDTO> dtos;
    HW_CashFragment cash;
    Context context;
    HW_Home homeActivity;
//    RecyclerView weekRecycle;
    HW_NewCashWeekAdapter weekFrag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_fragment_weeksale,container,false);
        // 프래그먼트가 부모일 경우에만 출력됨(액티비티일 경우 불러오지않음)
        String userid = getArguments().getString("userid");
        Bundle bundle = new Bundle();
        bundle.putString("userid",userid);

        homeActivity = (HW_Home) getActivity();
        context = homeActivity.getApplicationContext();

        //dtos = new ArrayList<>();

        //weekFrag 가 어댑터임
        weekFrag = new HW_NewCashWeekAdapter(this.context,userid);
//      weekRecycle = viewgroup.findViewById(R.id.weekRecycle);

        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.weekFrag, weekFrag).commit();


//        weekFrag.addDTO(new HW_CashDTO(170000, Date.valueOf("2022-07-01")));
//        weekFrag.addDTO(new HW_CashDTO(170000, Date.valueOf("2022-07-02")));
//        weekFrag.addDTO(new HW_CashDTO(140000, Date.valueOf("2022-07-03")));
//        weekFrag.addDTO(new HW_CashDTO(120000, Date.valueOf("2022-07-04")));
//        weekFrag.addDTO(new HW_CashDTO(100000, Date.valueOf("2022-07-05")));
//        weekFrag.addDTO(new HW_CashDTO(150000, Date.valueOf("2022-07-06")));
//        weekFrag.addDTO(new HW_CashDTO(190000, Date.valueOf("2022-07-07")));



        return viewgroup;


    }
}