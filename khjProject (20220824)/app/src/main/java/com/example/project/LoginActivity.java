package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.ATask.JoinInsert;
import com.example.project.DTO.MemberDTO;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.oauth.view.NidOAuthLoginButton;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileResponse;
import com.nhn.android.naverlogin.OAuthLogin;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "activity_main";

    private static String OAUTH_CLIENT_ID = "F5sj6DJGP6glRiJbyjcj";
    private static String OAUTH_CLIENT_SECRET = "MdlQMJhI9N";
    private static String OAUTH_CLIENT_NAME = "minjae";

    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;


    private String state = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        //초기화
        NaverIdLoginSDK.INSTANCE.initialize(
                this,OAUTH_CLIENT_ID,
                OAUTH_CLIENT_SECRET,
                OAUTH_CLIENT_NAME);

        NidOAuthLoginButton btn_naver = findViewById(R.id.btn_naver);
        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: " + NaverIdLoginSDK.INSTANCE.getAccessToken());
                Log.d(TAG, "onSuccess: " + NaverIdLoginSDK.INSTANCE.getRefreshToken());
                getNaverProfile();


            }

                @Override
            public void onFailure(int i, String s) {
                Log.d(TAG, "onFailure: " + s);
            }

            @Override
            public void onError(int i, String s) {
                Log.d(TAG, "onError: " + s);
            }
        });

    }

    public void getNaverProfile(){ //<- 억세스 토큰 O일때만 성공함.
        NidOAuthLogin authLogin = new NidOAuthLogin();
        authLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
            @Override
            public void onSuccess(NidProfileResponse nidProfileResponse) {

                Log.d(TAG, "onSuccess: " + nidProfileResponse.getProfile().getId());
                Log.d(TAG, "onSuccess: " + nidProfileResponse.getProfile().getEmail());
                Log.d(TAG, "onSuccess: " + nidProfileResponse.getProfile().getMobile());
                Log.d(TAG, "onSuccess: " + nidProfileResponse.getProfile().getName());
                Log.d(TAG, "onSuccess: " + nidProfileResponse.getProfile().getProfileImage());

                loginDTO = new MemberDTO(nidProfileResponse.getProfile().getName()
                        , nidProfileResponse.getProfile().getEmail()
                        , nidProfileResponse.getProfile().getMobile()
                        , nidProfileResponse.getProfile().getId()
                        , nidProfileResponse.getProfile().getProfileImage());
                // Log.d(TAG, "loginDTO.getEmail(): "+ loginDTO.getEmail());


                String id = loginDTO.getUserid();
                String email = loginDTO.getEmail();
                String name = loginDTO.getName();
                String phonenumber = loginDTO.getPhonenumber();
                String profileimage = loginDTO.getProfileimage();


               // 서버로 데이터를 보낸다 : AsyncTask를 상속받는 java파일을 만든다
               JoinInsert joinInsert = new JoinInsert(name, email, phonenumber, id, profileimage);
                try {
                    state = joinInsert.execute().get();
                    Log.d(TAG, "state: " + state);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                state = state.trim();
                // 정상적으로 데이터베이스에 삽입이 되면 1을 리턴, 아니면 0이하수를 리턴
                if(state.equals("1")){
                    Toast.makeText(LoginActivity.this,
                            "정상적으로 회원가입이 되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "회원가입에 실패하였습니다\n다시 확인 후 회원가입을 해주세요", Toast.LENGTH_SHORT).show();
                }


                //Intent intent = new Intent(getApplicationContext(), LoginResultActivity.class);
               // startActivity(intent);
               // finish();



            }

            @Override
            public void onFailure(int i, String s) {
                Log.d(TAG, "onFailure: " + s);
            }

            @Override
            public void onError(int i, String s) {
                Log.d(TAG, "onFailure: " + s);
            }
        });

    }





}