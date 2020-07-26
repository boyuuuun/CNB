package com.example.cnb00;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<User> userList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoadDB();

        // *Page for showing company information START* //
        final User getData = userList.get(0); // data gotten by last page.
        TextView state_tv, cn_tv, type_tv, field_tv, owner_tv, pn_tv, web_tv; // textview for showing company information
        //getHashKey();

        // show data
        state_tv = findViewById(R.id.state_tv);
        state_tv.setText(getData.getState());
        cn_tv = findViewById(R.id.cn_tv);
        cn_tv.setText(getData.getCompanyName());
        type_tv = findViewById(R.id.type_tv);
        type_tv.setText(getData.getType());
        field_tv = findViewById(R.id.field_tv);
        field_tv.setText(getData.getField());
        owner_tv = findViewById(R.id.owner_tv);
        owner_tv.setText(getData.getOwner());
        pn_tv = findViewById(R.id.pn_tv);
        pn_tv.setText(getData.getPhoneNumber());
        web_tv = findViewById(R.id.web_tv);
        web_tv.setText(getData.getWebCite());

        // click calling button
        findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pn_str = getData.getPhoneNumber().replaceAll("-","");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+pn_str));
                startActivity(intent);
            }
        });

        // click website button
        findViewById(R.id.web_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getData.getWebCite()));
                startActivity(intent);
            }
        });

        // show map
        MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey("APIKEY");
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);


        // *Page for showing company information END* //
    }

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        userList = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();
    }

    private void getHashKey(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String key = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key:", "!!!!!!!"+key+"!!!!!!");
            }
        } catch (Exception e){
            Log.e("name not found", e.toString());
        }
    }




}



