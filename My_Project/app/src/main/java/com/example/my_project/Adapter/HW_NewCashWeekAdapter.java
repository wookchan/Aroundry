package com.example.my_project.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_project.Adapter.Cash.NewCashMonthFragment;
import com.example.my_project.AsyncT.WeekTask;
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


public class HW_NewCashWeekAdapter extends Fragment {
    private static final String TAG = "NewCashWeek로부터";
    HW_Home homeActivity;
    Context context;
    ArrayList<HW_CashDTO> dtos;
    LinearLayout cashparents;
    TextView cashcurdate,cashdate,cashcur,cashlastcost;
    LineChart costchart;
    DecimalFormat decimalFormat = new DecimalFormat(",###");
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    NewCashMonthFragment ncmf;
    String userid;
    public HW_NewCashWeekAdapter(Context context,String userid) {
        this.context = context;
        this.userid = userid;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_cash_viewer,container,false);

        dtos = new ArrayList<>();

        WeekTask weekTask = new WeekTask(dtos,userid);
        try {
            weekTask.execute().get();
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sum=0;
        homeActivity = (HW_Home) getActivity();
        context = homeActivity.getApplicationContext();
        cashcurdate = viewgroup.findViewById(R.id.cashcurdate);
        cashparents = viewgroup.findViewById(R.id.cashparents);
        cashdate = viewgroup.findViewById(R.id.cashdate);
        cashcur = viewgroup.findViewById(R.id.cashcur);
        cashlastcost = viewgroup.findViewById(R.id.cashlastcost);
        costchart = viewgroup.findViewById(R.id.costchart);
        ncmf = new NewCashMonthFragment();
        homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.prevcashcontain, ncmf).commit();
        // 액티비티   - 액티비티 - 프래그먼트 - 프래그먼트 - 프레임레이아웃
        //MainActivity - HOME - CashFr - (Cash)Weeksale - CashweekAdapter2(현재)
        darwchart(dtos,times);
        Log.d(TAG, "dtos: "+ dtos);
      //  Log.d(TAG, "dots: " + dtos.get(0).getCost());
        for(int i =0; i < dtos.size() ;i++) {
            sum += dtos.get(i).getCost();
        }
      
      //  Log.d(TAG, "sum: " + sum);
       ///cashcurdate.setText(String.valueOf(dtos.get(0).getCostdate().toString().substring(5, 7)) + " 월 현재 매출"); //월
       //cashcurdate.setText(String.valueOf(dtos.get(0).getCostdate().toString().substring(5, 7)) + " 월 현재 매출"); //월
       cashcurdate.setText(String.valueOf(dtos.get(0).getCostdate().toString().substring(5, 10)) + " ~ " + String.valueOf(dtos.get(dtos.size() - 1).getCostdate().toString().substring(5, 10))+" 매출"); //월일일

        cashcur.setText(decimalFormat.format(sum) +" 원");

        XAxis xAxis = costchart.getXAxis(); //아래쪽
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 차트아래만 보여주기
        xAxis.setValueFormatter(new IndexAxisValueFormatter(times));

        Legend legend = costchart.getLegend();
        LineDataSet set1 = new LineDataSet(values,"이번 주 매출");
        costchart.setScrollContainer(false);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        LineData linedata = new LineData(dataSets);
        set1.setDrawValues(true);
        //legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        //legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        //범례 위치조절
        legend.setXOffset(250f);
        legend.setYOffset(2f);
        costchart.setData(linedata);
        costchart.setDescription(null);

        return viewgroup;
    }

    private void darwchart(ArrayList<HW_CashDTO> dtos, ArrayList<String> times) {
        for (int i=0; i < dtos.size()  ;i++) {
            values.add(new Entry(i,dtos.get(i).getCost(),dtos.get(i).getCost()));
            times.add(i, sdf.format(dtos.get(i).getCostdate()));
        }
    }


}
