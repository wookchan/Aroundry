package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;
import static com.example.project.Common.CommonMethod.saveInfo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.project.ATask.PS_QRCodeSelect;
import com.example.project.DTO.PS_QRCodeDTO;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PS_QRScannerActivity extends AppCompatActivity {
    private static final String TAG = "main:PS_QRScannerActivity";

    /*
    *   사용자이름 : name
    *   잔여금액 : point
    * */

    TextView textView;
    TextView qr_name;
    TextView qr_point;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;
    PS_QRCodeDTO dto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        textView = findViewById(R.id.qrcode_text);
        qr_name = findViewById(R.id.name);
        qr_point = findViewById(R.id.point);

        /* 원래있던(로그인할 당시) dto 에서 name 을 받아옴 */
        String name =loginDTO.getName();

        // 서버로 데이터를 보낸다 : AsyncTask를 상속받는 java파일을 만든다 -> 이 이후, loginDTO에 사용자이름과 잔여금액이 저장된다.
        PS_QRCodeSelect PSQRCodeSelect = new PS_QRCodeSelect(name);
        try {
            PSQRCodeSelect.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* 화면에 loginDTO에서 가져온 name, point 표시 */
        qr_name.setText(loginDTO.getName());
        qr_point.setText(loginDTO.getPoint());

        surfaceView = findViewById(R.id.surfaceView);

        barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setRequestedPreviewSize(640, 480).build();

        /* getHolder().addCallback => SurfaceView와 CameraSource를 연결 */
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try{
                    cameraSource.start(surfaceHolder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            /* QR Code를 인식할 때마다 어떤 작업을 수행할지를 명령할 수 있습니다.
            이 메소드를 통해서 qr 코드관련 작업을 수행하게 됩니다. */
            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcode = detections.getDetectedItems();
                if(qrcode.size() != 0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(qrcode.valueAt(0).displayValue);

                            String[] data = qrcode.valueAt(0).displayValue.split(",");
                            String storeid = data[0];
                            String machineseq = data[1];

                            Log.d(TAG, "storeid : " + storeid + ", " + machineseq);

                            saveInfo(getApplicationContext(), "storeid", storeid);

                            finish();

                            // intent를 이용해서 textView에 있는 url을 띄워주는 역할
                            // 에뮬에서는 구글url입력하면 되는데, 실제로 연결해서 확인해봐야함
                            /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("textView"));

                            startActivity(intent);*/
                        }
                    });
                }
            }
        });

        // **에뮬에서 연결을 확인한 지점**
        /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));

                            startActivity(intent);*/
    }


}