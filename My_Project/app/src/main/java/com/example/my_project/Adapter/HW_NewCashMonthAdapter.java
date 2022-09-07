package com.example.my_project.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_project.Adapter.Cash.NewCashMonthFragment;
import com.example.my_project.AsyncT.MonthTask;
import com.example.my_project.Cash.HW_MonthSale;
import com.example.my_project.DATA.HW_CashDTO;
import com.example.my_project.HW_Home;
import com.example.my_project.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HW_NewCashMonthAdapter extends Fragment {
    private static final String TAG = "NewCashMonth로부터";
    HW_Home homeActivity;
    Context context;
    ArrayList<HW_CashDTO> dtos;
    LinearLayout cashparents;
    TextView cashcurdate,cashdate,cashcur,cashlastcost, monthcheck;
    LineChart costchart;
    DecimalFormat decimalFormat = new DecimalFormat(",###");
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    NewCashMonthFragment ncmf;
    HW_MonthSale hw_monthSale;
    String userid;
    int sum2=0;
    int cnt=0;
    int cnt2 = 0;
    public HW_NewCashMonthAdapter(Context context,String userid) {
        this.context=context;
        this.userid = userid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_cash_viewer,container,false);
        dtos = new ArrayList<>();
        MonthTask monthTask = new MonthTask(dtos,userid);
        try {
            monthTask.execute().get();
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum=0;
        homeActivity = (HW_Home) getActivity();
        hw_monthSale = (HW_MonthSale) getParentFragment();
        context = homeActivity.getApplicationContext();

        cashcurdate = viewgroup.findViewById(R.id.cashcurdate);
        cashparents = viewgroup.findViewById(R.id.cashparents);
        cashdate = viewgroup.findViewById(R.id.cashdate);
        cashcur = viewgroup.findViewById(R.id.cashcur);
        cashlastcost = viewgroup.findViewById(R.id.cashlastcost);
        costchart = viewgroup.findViewById(R.id.costchart);
        monthcheck = homeActivity.findViewById(R.id.monthcheck);






        darwchart(dtos,times);

        Log.d(TAG, "dtos: " + dtos.get(0).getCost());
        for(int i =0; i < dtos.size() ;i++) {
            sum += dtos.get(i).getCost();
        }
        Log.d(TAG, "sum: " + sum);
        cashlastcost.setText("3달 평균 매출  " + decimalFormat.format(sum/3) + "  원");
        cashcurdate.setText(String.valueOf(dtos.get(dtos.size()-1).getCostdate().toString().substring(5, 7)) + " 월 현재 매출"); //월
        cashdate.setText(String.valueOf(dtos.get(dtos.size()-1).getCostdate().toString().substring(5, 7)) + "-01 ~ " + String.valueOf(dtos.get(dtos.size() - 1).getCostdate().toString().substring(5, 10))); //년월
        //"2011-22-22"
        //cashcur.setText(decimalFormat.format(sum) +" 원");

        XAxis xAxis = costchart.getXAxis(); //아래쪽
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 차트아래만 보여주기
        xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
        xAxis.setSpaceMax(0.1f);
        xAxis.setSpaceMin(0.1f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);


        LineDataSet set1 = new LineDataSet(values,"이번 달 매출");
        costchart.setScrollContainer(false);
        Legend legend = costchart.getLegend();
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        LineData linedata = new LineData(dataSets);
        Description description = new Description();
        description.setText("");

        set1.setDrawValues(true);
        // 범례 위치조절
        legend.setXOffset(250f);
        legend.setYOffset(2f);

        costchart.setData(linedata);
        costchart.setDescription(null);
        ncmf = new NewCashMonthFragment();
        Bundle bundle = new Bundle();
        bundle.putString("prevprevdate", times.get(0));
        bundle.putString("prevdate", times.get(1));
        bundle.putInt("prevprevdata", (Integer) values.get(0).getData());
        bundle.putInt("prevdata", (Integer) values.get(1).getData());
        ncmf.setArguments(bundle);
        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.prevcashcontain, ncmf).commit();









        return viewgroup;
    }

    private void darwchart(ArrayList<HW_CashDTO> dtos, ArrayList<String> times) {

        String fullstrdate = "";
        Log.d(TAG, "cnt: "+cnt);
        String strdate = "";
//        for (int i=0; i < dtos.size()  ;i++) {
//            values.add(new Entry(i,dtos.get(i).getCost(),dtos.get(i).getCost()));
//            times.add(i, sdf.format(dtos.get(i).getCostdate()));
//        }
        for (int i=0; i< dtos.size(); i++ ) {
           // Log.d(TAG, "dtos마지막: "+String.valueOf(dtos.get(dtos.size()-1).getCostdate()));
           // Log.d(TAG, "fullstrdate: " + dtos.get(i).getCostdate().toString());
            Log.d(TAG, "dtos last: "+dtos.get(dtos.size()-1).getCostdate());
            if(i == dtos.size()-1 ) {
                strdate = String.valueOf(dtos.get(i).getCostdate().toString().substring(5, 7));
                fullstrdate = String.valueOf(dtos.get(i).getCostdate().toString());

            }
            else if(i < dtos.size() -2 ) {
                fullstrdate = String.valueOf(dtos.get(i+1).getCostdate().toString());
                strdate = String.valueOf(dtos.get(i+1).getCostdate().toString().substring(5, 7));

            }
            else if(i < dtos.size() -1 ) {
                fullstrdate = String.valueOf(dtos.get(i).getCostdate().toString());
                strdate = String.valueOf(dtos.get(i).getCostdate().toString().substring(5, 7));

            }

                if (String.valueOf(dtos.get(dtos.size()-1).getCostdate()).toString().equals(fullstrdate)) {
                    cashcur.setText(decimalFormat.format(sum2) +" 원");
                    values.add(new Entry(2, sum2, sum2));
                    times.add(2, sdf.format(dtos.get(i).getCostdate()));
                    Log.d(TAG, "cost date: "+dtos.get(i).getCostdate());
                    Log.d(TAG, "times : " + sdf.format(dtos.get(i).getCostdate()));
                    sum2 = 0;
                }
                else if (String.valueOf(dtos.get(i).getCostdate().toString().substring(5, 7)).equals(strdate)  ) {
                    sum2 += dtos.get(i).getCost();

                }
                else if (!String.valueOf(dtos.get(i).getCostdate().toString().substring(5, 7)).equals(strdate)) {


                    values.add(new Entry(cnt, sum2, sum2));
                    times.add(cnt, sdf.format(dtos.get(i).getCostdate()));
                    Log.d(TAG, "times: " + sdf.format(dtos.get(i).getCostdate()));
                    Log.d(TAG, "cost date: "+dtos.get(i).getCostdate());
                    Log.d(TAG, "values data0 : : " + values.get(0).getData());
                    Log.d(TAG, "times data0 : : " + times.get(0).toString());
                    sum2 = 0;
                    cnt++;
                }



        }


        Log.d(TAG, "values: "+values.size());
        Log.d(TAG, "times: "+times.size());


    }

}

