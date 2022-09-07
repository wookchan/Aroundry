package com.example.my_project.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my_project.Cash.HW_MonthSale;
import com.example.my_project.Cash.HW_Weeksale;
import com.example.my_project.HW_Home;
import com.example.my_project.R;


public class HW_CashFragment extends Fragment  {

    HW_Home homeActivity;
    Context context;

    TextView weeksale,monthsale;
    HW_Weeksale sale1;
    HW_MonthSale sale2;
    int viewses =0;
    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_fragment_cash, container, false);
        String userid = getArguments().getString("id");
        Bundle bundle = new Bundle();
        bundle.putString("userid",userid);

        homeActivity = (HW_Home) getActivity();

        context = homeActivity.getApplicationContext();

        sale1 = new HW_Weeksale();
        sale1.setArguments(bundle);
        sale2 = new HW_MonthSale();
        sale2.setArguments(bundle);

        weeksale = viewgroup.findViewById(R.id.weeksale);
        monthsale = viewgroup.findViewById(R.id.monthsale);
        weeksale.setTypeface(null, Typeface.BOLD);
        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.cashcontain, sale1).commit();


            if (viewses != 1 || viewses == 0) {
                viewses = 1;
                weeksale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        weeksale.setTypeface(null, Typeface.BOLD);
                        monthsale.setTypeface(null, Typeface.NORMAL);
                        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.cashcontain, sale1).commit();
                    }
                });
            }

            if (viewses != 2) {
                viewses = 2;
                monthsale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        weeksale.setTypeface(null, Typeface.NORMAL);
                        monthsale.setTypeface(null, Typeface.BOLD);
                        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.cashcontain, sale2).commit();
                    }
                });
            }



        return viewgroup;
    }



}