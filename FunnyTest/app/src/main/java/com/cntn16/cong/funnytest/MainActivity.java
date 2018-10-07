package com.cntn16.cong.funnytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillAllFunnyTest();

    }

    private void fillAllFunnyTest() {
        ArrayList<FunnyTest> funnyTestArrayList = initFunnyTestArray();
        FunnyTestAdapter funnyTestAdapter = new FunnyTestAdapter(this,funnyTestArrayList,R.layout.layout_test_template_mutiplechoice);
        ListView listView = (ListView) findViewById(R.id.lvTest);
        listView.setAdapter(funnyTestAdapter);
    }

    private ArrayList<FunnyTest> initFunnyTestArray() {
        ArrayList<FunnyTest> funnyTestArrayList = new ArrayList<>();
        funnyTestArrayList.add(new FunnyTest("PUBG is a game on PC/Mobile?",new String[]{"A. PC","B. Mobile"},0,2));
        funnyTestArrayList.add(new FunnyTest("______ market is a symbol of Ho Chi Minh City",new String[]{"Ben Thanh"},0,3));
        funnyTestArrayList.add(new FunnyTest("Who is the hottest pop singer in Viet Nam?",new String[]{"A. Only C","B. Son Tung","C. Mr. Siro","D. Huong Tram"},1,1));
        funnyTestArrayList.add(new FunnyTest("France is the football team champion of World Cup 2018?",new String[]{"A. Yes","B. No"},0,2));
        funnyTestArrayList.add(new FunnyTest("What is the capital of Japan?",new String[]{"A. Jakarta","B. Tokyo","C. Bangkok","D. Seoul"},1,1));
        funnyTestArrayList.add(new FunnyTest("What is the name of Android 8.0",new String[]{"Oreo"},0,3));
        return funnyTestArrayList;
    }
}
