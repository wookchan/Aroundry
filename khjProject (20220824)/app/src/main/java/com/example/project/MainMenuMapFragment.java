package com.example.project;

import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.ATask.CommonAsyncTask;
import com.example.project.DTO.CleanDTO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainMenuMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    SupportMapFragment mapFragment;
    MapView sView = null;
    GoogleMap map;

    //중요
    ArrayList<CleanDTO> clean_list = new ArrayList<CleanDTO>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu_map , container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        CommonAsyncTask task = new CommonAsyncTask("kimCleanInfo");
        try {
            task.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clean_list = new Gson().fromJson( task.getResponse(), new TypeToken<ArrayList<CleanDTO>>(){}.getType() );

        //mapview 부름
        sView = view.findViewById(R.id.s_map);
        sView.onCreate(savedInstanceState);
        sView.getMapAsync(this);

        return view;
    }

    //이 메서드가 없으면 지도가 보이지 않음
    @Override
    public void onStart() {
        super.onStart();
        sView.onStart();
    }

    @Override
    public void onStop () {
        super.onStop();
        sView.onStop();

    }

    @Override
    public void onSaveInstanceState (@Nullable Bundle outState){
        super.onSaveInstanceState(outState);
        sView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        sView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sView.onLowMemory();
    }

    //맵뷰 설정
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        try {
            // 내 위치를 볼 수 있게 한다
            map.setMyLocationEnabled(true);

            for (CleanDTO dto : clean_list) {
                LatLng clean = new LatLng(Double.valueOf(dto.getLatitude()), Double.valueOf(dto.getLongitude()));
                //마커 옵션
                MarkerOptions marker = new MarkerOptions();
                marker.position(clean); //마커 위치
                marker.title(dto.getLocation());
                marker.snippet(dto.getAddress());
                marker.alpha(0.5f);
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


                googleMap.addMarker(marker).showInfoWindow();

                //맵뷰 카메라위치, 줌 설정
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clean, 16));
            }
            //인포윈도우 클릭
            googleMap.setOnInfoWindowClickListener(MainMenuMapFragment.this);

            // 하나의 윈도우info창 설정하기 - 마커 클릭시 하나의 창이 뜬다
            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {


                @Nullable
                @Override
                public View getInfoContents(@NonNull Marker marker ) {
                    LinearLayout info = new LinearLayout(getActivity().getApplicationContext());
                    info.setOrientation(LinearLayout.VERTICAL);

                    TextView title = new TextView(getActivity().getApplicationContext());
                    title.setTextColor(Color.GRAY);
                    title.setGravity(Gravity.BOTTOM);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText(marker.getTitle());


                    TextView snippet = new TextView(getActivity().getApplicationContext());
                    snippet.setTextColor(Color.GRAY);
                    snippet.setGravity(Gravity.LEFT);
                    snippet.setText(marker.getSnippet());

                    ImageView view = new ImageView(getActivity().getApplicationContext());

                    for(CleanDTO dto : clean_list) {
                        view.setImageResource(R.drawable.cleaning);
                        view.setForegroundGravity(Gravity.END);
                    }

                    info.addView(title);
                    info.addView(snippet);
                    info.addView(view);

                    return info;
                }

                @Nullable
                @Override
                public View getInfoWindow(@NonNull Marker marker) {
                    return null;
                }

            });

        } catch (SecurityException e) {
/*
            //마커찍기(위도,경도)
            LatLng clean1 = new LatLng(35.153447, 126.887794);
            LatLng clean2 = new LatLng(35.156292, 126.890828);
            LatLng clean3 = new LatLng(35.158292, 126.880828);

        map_list.add(clean1);
        map_list.add(clean2);
        map_list.add(clean3);

 */
            //맵에 마커표시, 인포윈도우 보여줌

            for (CleanDTO dto : clean_list) {
                LatLng clean = new LatLng(Double.valueOf(dto.getLatitude()), Double.valueOf(dto.getLongitude()));
                //마커 옵션
                MarkerOptions marker = new MarkerOptions();
                marker.position(clean); //마커 위치
                marker.title(dto.getLocation());
                marker.snippet(dto.getAddress());
                marker.alpha(0.5f);
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


                googleMap.addMarker(marker).showInfoWindow();

                //맵뷰 카메라위치, 줌 설정
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clean, 15));
            }
            //인포윈도우 클릭
            googleMap.setOnInfoWindowClickListener(MainMenuMapFragment.this);



/*
                //마커 옵션
            MarkerOptions marker1 = new MarkerOptions();
            MarkerOptions marker2 = new MarkerOptions();
            MarkerOptions marker3 = new MarkerOptions();



            marker1.position(clean3); //마커 위치
            marker1.title("광천코인세탁소");
            marker1.snippet("광주광역시 서구 농성1동 330-59");
            marker1.alpha(0.5f);
            marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            marker2.position(clean2); //마커 위치
            marker2.title("농성코인세탁소");
            marker2.snippet("광주광역시 서구 농성1동 330-59");
            marker2.alpha(0.5f);
            marker2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            marker3.position(clean1); //마커 위치
            marker3.title("한울세탁소");
            marker3.snippet("광주광역시 서구 농성1동 280-4");
            marker3.alpha(0.5f);
            marker3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


            //맵에 마커표시, 인포윈도우 보여줌
            googleMap.addMarker(marker1).showInfoWindow();
            googleMap.addMarker(marker2).showInfoWindow();
            googleMap.addMarker(marker3).showInfoWindow();
            //인포윈도우 클릭
            googleMap.setOnInfoWindowClickListener(MainMenuMapFragment.this);


            //맵뷰 카메라위치, 줌 설정
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clean1, 13));

*/


            // 하나의 윈도우info창 설정하기 - 마커 클릭시 하나의 창이 뜬다
            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {


                @Nullable
                @Override
                public View getInfoContents(@NonNull Marker marker ) {
                    LinearLayout info = new LinearLayout(getActivity().getApplicationContext());
                    info.setOrientation(LinearLayout.VERTICAL);

                    TextView title = new TextView(getActivity().getApplicationContext());
                    title.setTextColor(Color.GRAY);
                    title.setGravity(Gravity.BOTTOM);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText(marker.getTitle());


                    TextView snippet = new TextView(getActivity().getApplicationContext());
                    snippet.setTextColor(Color.GRAY);
                    snippet.setGravity(Gravity.LEFT);
                    snippet.setText(marker.getSnippet());


                    ImageView view = new ImageView(getActivity().getApplicationContext());


                    for(CleanDTO dto : clean_list) {
                        view.setImageResource(R.drawable.cleaning);
                        view.setForegroundGravity(Gravity.END);
                    }



                    info.addView(title);
                    info.addView(snippet);
                    info.addView(view);



                    return info;

                }


                @Nullable
                @Override
                public View getInfoWindow(@NonNull Marker marker) {
                    return null;
                }
            });

        }

    }



    private int getDistance(Location myLoc, Location markerLoc) {
        double distance = 0;
        // 거리를 구할 때는 location type을 사용한다
        distance = myLoc.distanceTo(markerLoc);

        return (int)distance;

    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }




}