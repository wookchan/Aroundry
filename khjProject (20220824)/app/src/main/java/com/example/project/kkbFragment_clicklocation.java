package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project.ATask.Bookmark;
import com.example.project.DTO.MemberDTO;
import com.example.project.DTO.PS_SearchDTO;
import com.example.project.DTO.StoreDTO;
import com.google.android.material.tabs.TabLayout;

public class kkbFragment_clicklocation extends Fragment {

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;

    Switch bk;

    Button btnplus;
    LinearLayout linearRest;
    LinearLayout linearRestTV;
    TabLayout tabs;
    MemberDTO memberdtos;
    PS_SearchDTO psdtos;

    kkbMainActivity activity = null;

    private static final String TAG = "main:kkbFragment_clicklocation";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.kkbfragment_clicklocation, container, false);

        activity = (kkbMainActivity) getActivity();

        btnplus = rootView.findViewById(R.id.btnplus);
        linearRest =rootView.findViewById(R.id.linearRest);
        linearRestTV = rootView.findViewById(R.id.linearRestTV);

        //선택한 지점 정보 출력
        //예: 두번째 index 1
        Intent intent = getActivity().getIntent();
        StoreDTO dto = (StoreDTO) intent.getSerializableExtra("dto");
        TextView location  = rootView.findViewById(  R.id.txtlocation );
        TextView address   = rootView.findViewById(  R.id.txtaddress );
        location.setText( dto.getLocation() );
        address.setText( dto.getAddress() );

        activity.getStoreDto(dto);

        setVisible(dto.getF_cctv(), R.id.imgcctv, R.id.txtcctv , rootView);
        setVisible(dto.getF_game(), R.id.imggame, R.id.txtgame , rootView);
        setVisible(dto.getF_toilet(), R.id.imgtoilet, R.id.txttoilet , rootView);
        setVisible(dto.getF_concent(), R.id.imgconcent, R.id.txtconcent , rootView);
        setVisible(dto.getF_wifi(), R.id.imgwifi, R.id.txtwifi , rootView);
        setVisible(dto.getF_coin(), R.id.imgcoin, R.id.txtcoin , rootView);

        /**
         * 가로 슬라이드 뷰 Fragment
         */

        //ViewPager2
        mPager =rootView.findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new kkbMyAdapter(getActivity(), num_page);
        mPager.setAdapter(pagerAdapter);


        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        /**
         * 이 부분 조정하여 처음 시작하는 이미지 설정.
         * 2000장 생성하였으니 현재위치 1002로 설정하여
         * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
         */

        mPager.setCurrentItem(1000); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);


                }
            }

        });

        /*즐겨찾기 버튼 클릭시 즐겨찾기 문구*/
        bk = rootView.findViewById(R.id.bookmark);
        bk.setVisibility( loginDTO==null ?  View.GONE : View.VISIBLE  );

        bk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Bookmark bookmark = new Bookmark(loginDTO.getUserid(), dto.getStoreid(), "1");
                    bookmark.execute();
                    Toast.makeText(getContext(), "즐겨찾기에 추가했습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Bookmark bookmark = new Bookmark(loginDTO.getUserid(), dto.getStoreid(), "0");
                    bookmark.execute();
                    Toast.makeText(getContext(), "즐겨찾기 해제했습니다", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // 편의시설 보이게 하는 버튼
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linearRest.getVisibility() == View.VISIBLE || linearRestTV.getVisibility()==View.VISIBLE){
                    linearRest.setVisibility(View.GONE);
                    linearRestTV.setVisibility(View.GONE);
                }else {
                    linearRest.setVisibility(View.VISIBLE); //이미지부분
                    linearRestTV.setVisibility(View.VISIBLE); //텍스트부분
                }
            }
        });

        return rootView;
    }

    private void setVisible(String state, int imgId, int txtId, ViewGroup view ){
        ImageView image  = view.findViewById(  imgId );
        TextView text   = view.findViewById(  txtId );
        image.setVisibility( state.equals("1") ? View.VISIBLE : View.GONE );
        text.setVisibility( state.equals("1") ? View.VISIBLE : View.GONE );
    }

}
