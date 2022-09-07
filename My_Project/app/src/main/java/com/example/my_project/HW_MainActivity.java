package com.example.my_project;

import static com.example.my_project.DATA.CommonMethod.loginDTO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_project.AsyncT.LoginTask;
import com.example.my_project.DATA.HW_OwnerDTO;
import com.example.my_project.Loading.LoadingActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HW_MainActivity extends AppCompatActivity {
    private static final String TAG = "mainActivity : ";
    EditText userId,userPw;
    ArrayList<HW_OwnerDTO> dtos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_main);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        dtos = new ArrayList<>();
//        //로그인 페이지를 띄움
//        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), HW_Home.class);
//                startActivity(intent);
//            }
//        });

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디 및 비밀번호의 길이가
                if(userId.getText().toString().length() > 0 && userPw.getText().toString().length() > 0) {
                    String id = userId.getText().toString();
                    String pw = userPw.getText().toString();

                    LoginTask loginTask = new LoginTask(id,pw,dtos);
                    try {
                        Log.d(TAG, "loginTask 진입: ");
                        loginTask.execute().get();
                    }
                    catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(loginDTO != null) {
                        Intent intent = new Intent(getApplicationContext(), HW_Home.class);
                        intent.putExtra("ownerid",id);
                        intent.putExtra("ownerpw",pw);
                        intent.putExtra("ownername",loginDTO.getName());
                        intent.putExtra("ownertel",loginDTO.getPhone());
                        intent.putExtra("ownerprofile",loginDTO.getProfileurl());
                        startActivity(intent);
                    }
                    else {
                        Log.d(TAG, "else: "+id+" , "+pw);
                        Toast.makeText(getApplicationContext(), "아이디가 없거나 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
                        userId.setText("");
                        userPw.setText("");
                        userId.requestFocus();

                    }
                }
                else { // 에디트텍스트에 내용이 없을때
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해 주십시오", Toast.LENGTH_SHORT).show();
                    userId.requestFocus();
                    return;
                }


            }
        });



    }


}