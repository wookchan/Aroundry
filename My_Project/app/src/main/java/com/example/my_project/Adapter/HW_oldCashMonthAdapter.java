package com.example.my_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_project.DATA.HW_CashDTO;
import com.example.my_project.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HW_oldCashMonthAdapter extends RecyclerView.Adapter<HW_oldCashMonthAdapter.ViewHolder> {
    private static final String TAG = "CashMonthAdapter: Main";
    Context context;
    ArrayList<HW_CashDTO> dtos;
    LayoutInflater inflater;
    DecimalFormat decimalFormat = new DecimalFormat(",###");
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    int sum = 0;
    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    public HW_oldCashMonthAdapter(Context context, ArrayList<HW_CashDTO> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.context);
    }

    public void addDTO(HW_CashDTO dto) {
        dtos.add(dto);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.hw_cash_viewer, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HW_CashDTO dto = dtos.get(position);
        holder.setDto(dto);


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout cashparents;
        TextView cashcurdate = itemView.findViewById(R.id.cashcurdate);
        TextView cashdate = itemView.findViewById(R.id.cashdate);
        TextView cashcur = itemView.findViewById(R.id.cashcur);
        TextView cashlastcost = itemView.findViewById(R.id.cashlastcost);
        LineChart costchart = itemView.findViewById(R.id.costchart);
//        ArrayList<Entry> values = new ArrayList<>();
//        ArrayList<String> times = new ArrayList<>();


        public ViewHolder(View itemView) {
            super(itemView);

            for (int i = 0; i < dtos.size(); i++) {
                sum += dtos.get(i).getCost();
                times.add(i, sdf.format(dtos.get(i).getCostdate()));
                values.add(new Entry(i, dtos.get(i).getCost()));
            }

            cashcurdate.setText(String.valueOf(dtos.get(0).getCostdate().toString().substring(5, 7)) + " 월 현재 매출"); //월
            cashdate.setText(String.valueOf(dtos.get(0).getCostdate().toString().substring(5, 10)) + " ~ " + String.valueOf(dtos.get(dtos.size() - 1).getCostdate().toString().substring(5, 10))); //월일일
            cashcur.setText(decimalFormat.format(sum) + " 원");


            XAxis xAxis = costchart.getXAxis(); //아래쪽
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 차트아래만 보여주기
            xAxis.setValueFormatter(new IndexAxisValueFormatter(times));

            LineDataSet set1 = new LineDataSet(values, "이번 주 매출");
            costchart.setScrollContainer(false);
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1);
            LineData linedata = new LineData(dataSets);

            set1.setDrawValues(true);
            costchart.setData(linedata);
        }

        public void setDto(HW_CashDTO dto) {

//            for (int i = 0; i < dtos.size(); i++) {
//                sum += dtos.get(i).getCash();
//                times.add(i, sdf.format(dtos.get(i).getCurdate()));
//                values.add(new Entry(i, dtos.get(i).getCash()));
//            }


//            cashcurdate.setText(String.valueOf(dto.getCurdate().toString().substring(5, 7)) + " 월 현재 매출"); //월
//            cashdate.setText(String.valueOf(dtos.get(0).getCurdate().toString().substring(5, 10)) + " ~ " + String.valueOf(dtos.get(dtos.size() - 1).getCurdate().toString().substring(5, 10))); //월일일

//            cashcurdate.setText( dtos.get(0).getCurdate().toString().substring(5, 7)+" 월 현재 매출"); //월
//            cashdate.setText(dtos.get(0).getCurdate().toString().substring(5,10) + " ~ " + dtos.get(dtos.size()-1).getCurdate().toString().substring(5,10)); //월일일

//            DecimalFormat decimalFormat = new DecimalFormat(",###");
//            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
//            ArrayList<Entry> values = new ArrayList<>();
//            ArrayList<String> times = new ArrayList<>();
            //Log.d(TAG, "dto의 사이즈는?  " + dtos.size());
            //Log.d(TAG, "dto의 상태는?  " +decimalFormat.format(dtos.get(1).getCash())+" 만원");
            //Log.d(TAG, "dto: " + dtos.get(dtos.size()-1).getCurdate().toString().substring(5,10));
//                for (int i = 0; i < val.length; i++) {
//                    sum += val[i];
//                    times.add(i, dat[i].substring(5,10));
//                    values.add(new Entry(i, val[i], decimalFormat.format(val[i]) + " 원"));
//                }
//            cashcur.setText(decimalFormat.format(sum) + " 원");
//
//
//            XAxis xAxis = costchart.getXAxis(); //아래쪽
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 차트아래만 보여주기
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
//
//            LineDataSet set1 = new LineDataSet(values, "이번 주 매출");
//            costchart.setScrollContainer(false);
//            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//            dataSets.add(set1);
//            LineData linedata = new LineData(dataSets);
//
//            set1.setDrawValues(true);
//            costchart.setData(linedata);


        }





    }
}
