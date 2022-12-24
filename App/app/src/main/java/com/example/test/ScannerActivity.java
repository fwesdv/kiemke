package com.example.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.test.Model.Asset;
import com.google.zxing.Result;

import java.util.List;

public class ScannerActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    List<Asset> assetList;
    Asset asset;
    Intent intentGet;
    Bundle bundleget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        intentGet= getIntent();
        bundleget = intentGet.getExtras();
        if (bundleget != null){
            assetList=(List<Asset>) bundleget.getSerializable("assetList");
        }
        requestCamera();
    }

    private void requestCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(ScannerActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }

    private void startCamera() {
        CodeScannerView scannerView = findViewById(R.id.scanner_view);

        TextView textView = findViewById(R.id.tv_textView);
        TextView textView2 = findViewById(R.id.tv_textView2);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Kết quả Scan được: " + result.getText());
                        textView2.setText("(Nhấn vào màn hình để tiếp tục)");
                        String scanResult=result.getText();
                        Toast.makeText(ScannerActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        assetList.forEach((i)->{
                            boolean check=i.getAssetId().equals(scanResult);
                            if(check){
                                asset=i;
                            }
                        });
                        Intent intent=new Intent(ScannerActivity.this, AssetDetail.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("asset",asset);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Finding QR Code to Scan...");
                textView2.setText("");
                mCodeScanner.startPreview();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
