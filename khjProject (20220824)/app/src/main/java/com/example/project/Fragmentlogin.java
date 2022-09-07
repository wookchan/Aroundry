package com.example.project;


import static com.example.project.Common.CommonMethod.loginDTO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class Fragmentlogin extends Fragment {

    private static String OAUTH_CLIENT_ID = "F5sj6DJGP6glRiJbyjcj";
    private static String OAUTH_CLIENT_SECRET = "MdlQMJhI9N";
    private static String OAUTH_CLIENT_NAME = "minjae";

    private static OAuthLogin mOAuthLoginInstance;
    private static Context mContext;
    private static final String TAG = "main:Fragmentlogin";

    private static String state = "";
    MainActivity home;
    Context context;
    MainMenuWashFragment washFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_login,
                container, false);

        // setContentView(R.layout.activity_login);

        home = (MainActivity) getActivity();
        context = home.getApplicationContext();


        /*
         * @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
         *  {
         *  View view = inflater.inflate(R.layout.fragment_home, container, false);
         *  return view; }
         *
         *
         * @Override protected void onCreate(Bundle savedInstanceState) {
         *  super.onCreate(savedInstanceState);
         * setContentView(R.layout.activity_home);
         *  Log.d(TAG, "onCreate: starting.");
         *  setupBottomNavigationView(); }
         * */


        //초기화
        NaverIdLoginSDK.INSTANCE.initialize(
                getContext(), OAUTH_CLIENT_ID,
                OAUTH_CLIENT_SECRET,
                OAUTH_CLIENT_NAME);

        NidOAuthLoginButton btn_naver = viewGroup.findViewById(R.id.btn_naver);
        btn_naver.setOAuthLoginCallback(new OAuthLoginCallback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "onSuccess: " + NaverIdLoginSDK.INSTANCE.getAccessToken());
                Log.d(TAG, "onSuccess: " + NaverIdLoginSDK.INSTANCE.getRefreshToken());
               getNaverProfile();

               //home.changeFragment(3);

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

        return viewGroup;
    }

    public void getNaverProfile(){ //<- 억세스 토큰 O일때만 성공함.
        NidOAuthLogin authLogin = new NidOAuthLogin();
        authLogin.callProfileApi(new NidProfileCallback<NidProfileResponse>() {
            @SuppressLint("ResourceType")
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

                Log.d(TAG, "loginDTO.getID() : " + loginDTO.getUserid());


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
                    Toast.makeText(getContext(),
                            "정상적으로 회원가입이 되었습니다", Toast.LENGTH_SHORT).show();
                    //finish();
                }else{
                    Toast.makeText(getContext(),
                            "회원가입에 실패하였습니다\n다시 확인 후 회원가입을 해주세요", Toast.LENGTH_SHORT).show();
                }

                home.changeFragment(home.menuNum);

               // home.getSupportFragmentManager().beginTransaction().replace(R.id.).commit();






               // Intent intent = new Intent(getContext(), LoginResultActivity.class);
                //startActivity(intent);
                //finish();



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
