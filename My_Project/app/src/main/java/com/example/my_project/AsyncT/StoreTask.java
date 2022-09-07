package com.example.my_project.AsyncT;

import static com.example.my_project.DATA.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;


import androidx.recyclerview.widget.RecyclerView;

import com.example.my_project.Adapter.Setak.HW_Listinfor2Adapter;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.DATA.HW_StoreDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StoreTask extends AsyncTask<Void,Void,Void> {
    ArrayList<HW_StoreDTO> dtos;
    HW_Listinfor2Adapter adapter;
    String userid;
    int store;
    RecyclerView listinfor2_recycler;

    private static final String TAG = "StoreTask에서";
    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
    public StoreTask(ArrayList<HW_StoreDTO> dtos, HW_Listinfor2Adapter adapter, String userid, int store) {
        this.dtos = dtos;
        this.adapter = adapter;
        this.userid = userid;
        this.store = store;
    }

    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답을 받는 부분
    HttpEntity httpEntity;       // 응답내용

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode((HttpMultipartMode.BROWSER_COMPATIBLE) );
            builder.setCharset(Charset.forName("utf-8"));

            //언젠간 지워야한다,로그인페이지에서 아이디를 받아서 쏴야하기 떄문임
            String id = userid;
            int store2 = store;
            builder.addTextBody("id",id, ContentType.create("Multipart/related","UTF-8"));
            builder.addTextBody("storeid",String.valueOf(store2),ContentType.create("Multipart/related","UTF-8"));

            // ipconfig은 커먼 메소드에 + "스프링 주소 연결" <-
            // 현 ipconfig의 포트는 8989이다
            String postURL = ipConfig+"/laundry/Hwstore";

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 보내고 응답받는 부분
            httpEntity = httpResponse.getEntity();  // 응답내용을 저장
            inputStream = httpEntity.getContent();  // 응답내용을 inputStream에 넣음
            readJsonSteam(inputStream); // 데이터베이스로부터 올떈 json형태로 온다 그러니 이것을 읽어들일 메소드가 필요함
        }
        catch (Exception e) {
            e.getMessage();
        }
        finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }


        return null;
    }
    //세번쨰 Void
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        adapter.notifyDataSetChanged();
        Log.d(TAG, ": "+dtos.size());
    }

    private void readJsonSteam(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream,"utf-8"));
        try {
            reader.beginArray();
            while(reader.hasNext()) {
                dtos.add(readMessage(reader)); //readmessage라는 메소드 안에 dto 형태로 캐스팅(파싱)해서 받아야함
            }
            reader.endArray();
        } //try
        catch (Exception e) {
            e.getMessage(); //에러
        }
        finally {
            reader.close(); // 닫기
        }
    }

    private HW_StoreDTO readMessage(JsonReader reader) throws IOException {
        int storeid=0,machineseq=0,using=0;
        Date costdate = null;
        String strdate = "";

        reader.beginObject();

        while(reader.hasNext()) {
            String readStr = reader.nextName();
            if(readStr.equals("storeid")) {
                storeid = reader.nextInt();
            }
            else if(readStr.equals("machineseq")) {
                machineseq = reader.nextInt();
            }
            else if(readStr.equals("using")) {
                using =reader.nextInt();
            }


            else if(readStr.equals("costdate")) {

                strdate = reader.nextString();
                try {
                    costdate = new Date( sdf.parse(strdate).getTime() );
                    //udate = sdf.parse(strdate);
                }
                catch(Exception e) {
                    e.getMessage();
                }
                //costdate = new Date(udate.getTime());
            }

            else {
                reader.skipValue();
            }

        }
        reader.endObject();
        return new HW_StoreDTO(storeid,machineseq,using,costdate);
    }
}
