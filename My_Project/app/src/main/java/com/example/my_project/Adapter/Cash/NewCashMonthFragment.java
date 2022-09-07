package com.example.my_project.Adapter.Cash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my_project.HW_Home;
import com.example.my_project.R;

import java.text.DecimalFormat;


public class NewCashMonthFragment extends Fragment {
    private static final String TAG = "NewCashMonthFragment";
    DecimalFormat decimalFormat = new DecimalFormat(",###");
    
    String prevprevdate,prevdate;
    int prevprevdata,prevdata;
    TextView cashmonthyear1,cashmonthyear2,cashmonthsum1,cashmonthsum2;
    HW_Home homeActivty;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewgroup= (ViewGroup) inflater.inflate(R.layout.fragment_new_cash_month,container,false);
        homeActivty = (HW_Home) getActivity();
        Bundle bundle= getArguments();

        cashmonthsum1=viewgroup.findViewById(R.id.cashmonthsum1);
        cashmonthsum2=viewgroup.findViewById(R.id.cashmonthsum2);
        cashmonthyear1=viewgroup.findViewById(R.id.cashmonthyear1);
        cashmonthyear2=viewgroup.findViewById(R.id.cashmonthyear2);

        if(bundle != null) {
            prevprevdate = bundle.getString("prevprevdate");
            Log.d(TAG, "bundle1: "+prevprevdate);
            prevdate = bundle.getString("prevdate");
            prevprevdata = bundle.getInt("prevprevdata",0);
            prevdata = bundle.getInt("prevdata",0);

            cashmonthyear1.setText(prevprevdate.substring(0,2)+"년 "+prevprevdate.substring(3,5)+"월");
            cashmonthsum1.setText(decimalFormat.format(prevprevdata)+"원");
            cashmonthyear2.setText(prevdate.substring(0,2)+"년 "+prevdate.substring(3,5)+"월");
            cashmonthsum2.setText(decimalFormat.format(prevdata)+"원");
        }

        
        return viewgroup;
    }
}