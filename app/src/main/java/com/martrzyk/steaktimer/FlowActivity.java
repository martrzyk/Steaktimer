package com.martrzyk.steaktimer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_flow)
public class FlowActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FlowActivity.this.finish();
//            }
//        });
//    }


    public static Intent intentCreator(Context context) {
        Intent i = new Intent(context, FlowActivity.class);
        return i;
    }

}
