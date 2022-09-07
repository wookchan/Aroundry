
package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.Adapter.AndroidAdapter;
import com.example.project.Adapter.PS_SearchAdapter;
import com.example.project.DTO.MemberDTO;
import com.example.project.DTO.PS_SearchDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Mainfragment mainfragment = new Mainfragment();
    public FragmentManager fragmentManager = getSupportFragmentManager();
    public MainMenuHomeFragment fragmentHome = new MainMenuHomeFragment();
    public MainMenuMapFragment fragmentMap = new MainMenuMapFragment();
    public Fragmentlogin fragmentlogin = new Fragmentlogin();
    public MainMenuMypageFragment fragmentMypage = new MainMenuMypageFragment();
    public washFragment washFragment = new washFragment();

    AndroidAdapter adapter;

    ArrayList<MemberDTO> dtos;

    public int menuNum = -1;

    private static final String TAG = "main:PS_MainActivity";

    PS_Fragment1 PSFragment1;
    Fragment2 fragment2;

    Fragment selFragment = null;
    ArrayList<PS_SearchDTO> filteredList;

    public ArrayList<PS_SearchDTO> mPSSearchDTOS;
    private PS_SearchAdapter mAdapter;

    BottomNavigationView bottomNavigationView;

    //위치 : 현재위치 Location 객체 생성
    public static Location myLoc= new Location("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        checkDangerousPermissions();
        requestMyLocation();    // 내 위치찾기 메소드 선언

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, mainfragment).commitAllowingStateLoss();

        /**
         * 12345
         * */
        bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = new ArrayList<>();


        // 어댑터 객체 생성
        adapter = new AndroidAdapter(MainActivity.this, dtos);

        // 프래그먼트 생성
        PSFragment1 = new PS_Fragment1();
        fragment2 = new Fragment2();

    }

    public void changeFragment(int menuNum){
        if(menuNum == 3){
            fragmentManager.beginTransaction().replace(R.id.menu_frame_layout, washFragment).commit();
        }else if(menuNum == 4){
            fragmentManager.beginTransaction().replace(R.id.menu_frame_layout, fragmentMypage).commit();
        }

    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    transaction.replace(R.id.menu_frame_layout, mainfragment).commitAllowingStateLoss();
                    break;
                case R.id.menu_map:
                    transaction.replace(R.id.menu_frame_layout, fragmentMap).commitAllowingStateLoss();
                    break;

                case R.id.menu_wash:
                    if(loginDTO == null){
                        transaction.replace(R.id.menu_frame_layout, fragmentlogin).commitAllowingStateLoss();
                        menuNum = 3;
                    }else {
                        transaction.replace(R.id.menu_frame_layout, washFragment).commitAllowingStateLoss();
                    }

                    break;
                case R.id.menu_my_page:
                    if(loginDTO == null){
                        transaction.replace(R.id.menu_frame_layout, fragmentlogin).commitAllowingStateLoss();
                        menuNum = 4;
                    }else {
                        transaction.replace(R.id.menu_frame_layout, fragmentMypage).commitAllowingStateLoss();
                    }
                    break;

            }

            return true;
        }
    }

    // 위험권한 : 실행시 허용여부를 다시 물어봄
    private void checkDangerousPermissions() {
        String[] permissions = {
                // 위험권한 내용 : 메니페스트에 권한을 여기에 적음
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    // 내 위치 찾기
    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location loc =
                    manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc != null) {
                myLoc = loc;
            }
        } catch (SecurityException e) {
        }
    }
}

