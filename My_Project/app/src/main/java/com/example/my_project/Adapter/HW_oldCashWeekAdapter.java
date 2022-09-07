package com.example.my_project.Adapter;

import android.annotation.SuppressLint;
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
import com.github.mikephil.charting.data.Entry;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HW_oldCashWeekAdapter extends RecyclerView.Adapter<HW_oldCashWeekAdapter.ViewHolder> {
    private static final String TAG = "Main:CashWeekAdapter";
    Context context;
    ArrayList<HW_CashDTO> dtos;
    LayoutInflater inflater;

    //값 받기

    public HW_oldCashWeekAdapter(Context context, ArrayList<HW_CashDTO> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.context);
    }

    //dto 추가
    public void addDTO(HW_CashDTO dto) {
        dtos.add(dto);
    }

    // 차트만들기
    public void drawChart(ArrayList<HW_CashDTO> dtos){

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.hw_cash_viewer,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HW_CashDTO dto = dtos.get(position);
        holder.setDto(dto);
    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout cashparents;
        TextView cashcurdate,cashdate,cashcur,cashlastcost;
        LineChart   costchart = (LineChart) itemView.findViewById(R.id.costchart);
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();


        public ViewHolder(View itemView) {
            super(itemView);
            cashcurdate = itemView.findViewById(R.id.cashcurdate);
            cashparents = itemView.findViewById(R.id.cashparents);
            cashdate = itemView.findViewById(R.id.cashdate);
            cashcur = itemView.findViewById(R.id.cashcur);
            cashlastcost = itemView.findViewById(R.id.cashlastcost);

        }

        public void setDto(HW_CashDTO dto) {
//            String arr[] = dto.getCash().split("/");
//            int val[] = new int[arr.length];
//            for(int i=0; i<arr.length; i++){
//                val[i] = Integer.parseInt(arr[i]);
//            }
//
//            String dat[] = dto.getCurdate().split(",");
//            Date date[] = new Date[dat.length];
//            for(int i=0; i<dat.length; i++){
//                try {
//                    date[i] = sdf.parse(dat[i]);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }

//            cashcurdate.setText( String.valueOf(dto.getCurdate().toString().substring(5, 7))+" 월 현재 매출"); //월
//            cashdate.setText(String.valueOf(dtos.get(0).getCurdate().toString().substring(5,10)) + " ~ " + String.valueOf(dtos.get(dtos.size()-1).getCurdate().toString().substring(5,10)) ); //월일일

//            int sum = 0;
//            cashcurdate.setText( dat[0].substring(5, 7)+" 월 현재 매출"); //월
//            cashdate.setText(dat[0].substring(5,10) + " ~ " + dat[date.length-1].substring(5,10)); //월일일
//
////            DecimalFormat decimalFormat = new DecimalFormat(",###");
////            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
////            ArrayList<Entry> values = new ArrayList<>();
////            ArrayList<String> times = new ArrayList<>();
//            //Log.d(TAG, "dto의 사이즈는?  " + dtos.size());
//            //Log.d(TAG, "dto의 상태는?  " +decimalFormat.format(dtos.get(1).getCash())+" 만원");
//            //Log.d(TAG, "dto: " + dtos.get(dtos.size()-1).getCurdate().toString().substring(5,10));
//            for (int i = 0; i < val.length; i++) {
//                    sum += val[i];
//                    times.add(i, dat[i].substring(5,10));
//                    values.add(new Entry(i, val[i], decimalFormat.format(val[i]) + " 원"));
//            }
//            cashcur.setText(decimalFormat.format(sum) +" 원");
//
//            XAxis xAxis = costchart.getXAxis(); //아래쪽
//            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 차트아래만 보여주기
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(times));
//
//            LineDataSet set1 = new LineDataSet(values,"이번 주 매출");
//            costchart.setScrollContainer(false);
//            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//            dataSets.add(set1);
//            LineData linedata = new LineData(dataSets);
//
//            set1.setDrawValues(true);
//            costchart.setData(linedata);
//



        }// SetDTO



    } // viewHolder
} // END


           /* times.add(0,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(1,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(2,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(3,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(4,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(5,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(6,String.valueOf(sdf.format(dto.getCurdate())));
            times.add(7,String.valueOf(sdf.format(dto.getCurdate())));*/




//Entry x = 순서, y = 돈 기입 // x축
        /*    values.add(new Entry(0,1000,decimalFormat.format(5000)+" 만원"));
            values.add(new Entry(1,3000,decimalFormat.format(9000)+" 만원"));
            values.add(new Entry(2,5500,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(3,6000,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(4,7500,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(5,1200,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(6,1800,decimalFormat.format(7000)+" 만원"));*/

//수동 타이핑을 ㅎ야하는듯 ;;
            /*
            ArrayList<Entry> values = new ArrayList<>();

            for (int i = 0; i < 10; i++) {

                float val = (float) (Math.random() * 10);
                values.add(new Entry(i, val));
            }
            LineDataSet set1;
            set1 = new LineDataSet(values,"DataSet 1");

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);

            costchart.setData(data);*/


            /*DecimalFormat decimalFormat = new DecimalFormat("#,###");

            ArrayList<Entry> values = new ArrayList<>();
            LineDataSet set1;
            //Entry x = 순서, y = 돈 기입
            values.add(new Entry(1,5000,decimalFormat.format(5000)+" 만원"));
            costchart.getXAxis().setDrawAxisLine(true);
            values.add(new Entry(20,9000,decimalFormat.format(9000)+" 만원"));
            values.add(new Entry(30,7000,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(40,7000,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(50,7000,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(60,7000,decimalFormat.format(7000)+" 만원"));
            values.add(new Entry(70,7000,decimalFormat.format(7000)+" 만원"));
            set1 = new LineDataSet(values,"이번 주 매출");

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData linedata = new LineData(dataSets);

            costchart.setData(linedata);
            set1.setDrawValues(false);

            XAxis xAxis = costchart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 차트아래만 보여주기*/