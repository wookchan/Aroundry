package com.example.project.Adapter;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.ATask.MainWashStartStop;
import com.example.project.ATask.MoneyChargeInsert;
import com.example.project.DTO.MainWashDTO;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainWashAdapter extends
        RecyclerView.Adapter<MainWashAdapter.ViewHolder>{

    private static final String TAG = "main:MainWashAdapter";

    // 3. 메인한테 넘겨 받는것
    Context context;
    ArrayList<MainWashDTO> dtos;
    LayoutInflater inflater;

    // 4. 생성자를 만들어 메인에게서 넘겨받은것을 연결
    public MainWashAdapter(Context context, ArrayList<MainWashDTO> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.context);
    }

    // 5. 메소드는 여기에 만든다
    ///////////////////////////////////////////////////////////////////
    //filter 와 관련된 filteredList 함수
    public void filterList(ArrayList<MainWashDTO> filteredList) {
        dtos = filteredList;
        notifyDataSetChanged();
    }
    ///////////////////////////////////////////////////////////////////

    // 6. 화면을 인플레이트 시킨다
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.fragment_main_wash_item, parent, false);

        return new ViewHolder(itemView);
    }

    // 7. 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // dtos에 있는 데이터를 순서대로 불러온다
        MainWashDTO dto = dtos.get(position); // 다섯개라고 가정하면 position은 0~4
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를 사용하여 셋팅한다
        holder.setDto(dto);

        holder.btn_laundrystart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 유저의 돈이 최소 5000 이상일때 사용가능
                if(Integer.parseInt(loginDTO.getPoint()) >= 5000){
                    // 사용자 돈 차감
                    //String point = String.valueOf(Integer.parseInt(loginDTO.getPoint()) - 5000);
                    //loginDTO.setPoint(point);

                    MainWashStartStop mainWashStartStop = new
                            MainWashStartStop(loginDTO.getUserid(), dto.getMachineseq(), "1");
                    try {
                        mainWashStartStop.execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    showMessage();
                }


            }
        });

        holder.btn_laundrystop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainWashStartStop mainWashStartStop = new
                        MainWashStartStop(loginDTO.getUserid(), dto.getMachineseq(), "0");
                try {
                    mainWashStartStop.execute().get();
                    //notifyDataSetChanged();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 1. xml 파일에서 사용된 모든변수와 사용할 레이아웃을 클래스에서 선언한다 : 별 5개
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1-1. _item.xml 에 사용된 모든 위젯을 정의한다
        TextView tv_number, tv_remaintime, tv_yesno;
        ImageView laundryimage;
        Button btn_laundrystart, btn_laundrystop;

        // 1-2. example_item.xml 에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_number = itemView.findViewById(R.id.tv_number);
            tv_remaintime = itemView.findViewById(R.id.tv_remaintime);
            tv_yesno = itemView.findViewById(R.id.tv_yesno);
            laundryimage = itemView.findViewById(R.id.laundryimage);
            btn_laundrystart = itemView.findViewById(R.id.btn_drystart);
            btn_laundrystop = itemView.findViewById(R.id.btn_drystop);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), kkbMainActivity.class);
                    intent.putExtra("index", getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });*/
        }

        // 1-3. 함수를 만들어서 example_item 에 데이터를 연결시킨다
        public void setDto(MainWashDTO dto){
            tv_number.setText(dto.getMachineid());

            if(!dto.getResttime().equals("")){
                tv_remaintime.setText(dto.getResttime());
                tv_yesno.setText("사용중");
                tv_yesno.setBackgroundColor(Color.RED);
                laundryimage.setImageResource(R.drawable.laundrymove);

                // 만약 이기계를 사용하는 사람이 로그인 한 사람이면 정지시킬수 있다
                if(loginDTO.getUserid().equals(dto.getUserid())){
                    btn_laundrystart.setEnabled(false);
                    btn_laundrystop.setEnabled(true);
                }else{
                    btn_laundrystart.setEnabled(false);
                    btn_laundrystop.setEnabled(false);
                }

            }else{
                tv_remaintime.setText("0");
                tv_yesno.setText("사용가능");
                tv_yesno.setBackgroundColor(Color.GREEN);
                laundryimage.setImageResource(R.drawable.laundrystop);

                btn_laundrystart.setEnabled(true);
                btn_laundrystop.setEnabled(false);
            }

        }
    }

    private void showMessage() {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("안내");
        builder.setMessage("포인트가 부족합니다\n20000원을 충전하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // 예 버튼
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 충전하고 로그인다시?
                String money = "20000";
                MoneyChargeInsert chargeInsert = new MoneyChargeInsert(loginDTO.getUserid(), money); // 돈 액수만보낸다
                try {
                    chargeInsert.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        // 아니오 버튼
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 다이얼박스 사라짐
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();*/


        final List<String> ListItems = new ArrayList<>();
        ListItems.add("5000원");
        ListItems.add("10000원");
        ListItems.add("20000원");
        ListItems.add("50000원");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("포인트가 부족합니다\n충전하시겠습니까?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                String selectedText = items[pos].toString();
                String money = selectedText.replace("원", "");
                MoneyChargeInsert chargeInsert = new MoneyChargeInsert(loginDTO.getUserid(), money); // 돈 액수만보낸다
                try {
                    chargeInsert.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(context, selectedText + "이 충전되었습니다!!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();



    }


}
