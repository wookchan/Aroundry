package com.example.my_project.AsyncT;

import static com.example.my_project.DATA.CommonMethod.ipConfig;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.my_project.Adapter.HW_NewCashWeekAdapter;
import com.example.my_project.DATA.HW_CashDTO;
import com.example.my_project.HW_Home;
import com.example.my_project.R;

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
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WeekTask extends AsyncTask<Void,Void,Void> {
    ArrayList<HW_CashDTO> dtos;
    Context context;
    HW_Home homeActivity;
    String userid;

    private static final String TAG = "WeekTask : ";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd 00:00:00");

    public WeekTask(ArrayList<HW_CashDTO> dtos,String userid) {
        this.dtos = dtos;
        this.userid = userid;
    }
    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
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

            // id와 밑의줄은 지워야함
            String id = userid;
            builder.addTextBody("id",id, ContentType.create("Multipart/related","UTF-8"));

            String postURL = ipConfig+"/laundry/Hwweekcost";

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

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        //어댑터의 역활하는 프래그먼트이나 notify.. 이안됨

    }
    public void readJsonSteam(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream,"utf-8"));
        try {
            reader.beginArray();
            while(reader.hasNext()) {
                dtos.add(readMessage(reader)); //readmessage라는 메소드 안에 dto 형태로 캐스팅(파싱)해서 받아야함
            }
            reader.endArray();

            Log.d(TAG, "dtossize : " + dtos.size());
        } //try
        catch (Exception e) {
            e.getMessage(); //에러
        }
        finally {
            reader.close(); // 닫기
        }
    }

    private HW_CashDTO readMessage(JsonReader reader) throws Exception {
        String userid="",strdate="";
        java.util.Date udate = null;
        Date costdate=null;
        int cost=0;
        reader.beginObject();
        while(reader.hasNext()) {
            String readStr = reader.nextName();
            if(readStr.equals("userid")) {
                userid = reader.nextString();
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
            else if(readStr.equals("cost")) {
                cost = reader.nextInt();
            }
            else {
                reader.skipValue();
                Log.d(TAG, "readmessage : 값이 비어 스킵함");
            }

        }
        reader.endObject();
        return new HW_CashDTO(userid,costdate,cost);
    }

}
