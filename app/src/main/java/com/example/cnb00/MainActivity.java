package com.example.cnb00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<User> userList ;

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        userList = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoadDB();

        // *Page for showing company information START* //
        final User getData = userList.get(0); // data gotten by last page.

        TextView state_tv, cn_tv, type_tv, field_tv, owner_tv, pn_tv, web_tv; // textview for showing company information

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

        findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() { // click calling button
            @Override
            public void onClick(View v) {
                String pn_str = getData.getPhoneNumber().replaceAll("-","");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+pn_str));
                startActivity(intent);
            }
        });

        findViewById(R.id.web_btn).setOnClickListener(new View.OnClickListener() { // click website button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getData.getWebCite()));
                startActivity(intent);
            }
        });

        // *Page for showing company information END* //
    }

}



