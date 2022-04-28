package com.example.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    TextView view1;
    Button btn_ok;

    ArrayList<String> exceptList = new ArrayList<>();
    HashSet<String> lottoSet = new HashSet<>();
    ArrayList<String> arrList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view1 = findViewById(R.id.view1);
        btn_ok = findViewById(R.id.ok);

        btn_ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                view1.setText("");

                if( exceptList.size() > 35 ) {
                    Toast.makeText(MainActivity.this, "숫자는 35개 이하로 선택해 주세요 :)", Toast.LENGTH_SHORT).show();
                    return;
                }

                while (lottoSet.size() < 7) {
                    int num = (int)(Math.random()*45);
                    if( num != 0 ) {
                        if( !exceptList.contains(num + "") ) {
                            lottoSet.add(num+"");
                        }
                    }
                }
                arrList.addAll(lottoSet);
                Collections.sort(arrList, String.CASE_INSENSITIVE_ORDER);

                for (int i=0; i < arrList.size(); i++) {
                    if (i < 5) {
                        view1.append(arrList.get(i)+ ",  ");
                    } else if(i == 5){
                        view1.append(arrList.get(i)+ "  ");
                    } else {
                        view1.append(" 보너스 : " + arrList.get(i));
                    }
                }

                //초기화
                lottoSet.clear();
                arrList.clear();
            }
        });
    }

    public void onButtonClick(View v) {
        String text = ((Button) v).getText().toString();
        v.setSelected(!v.isSelected());

        if (v.isSelected()) {
            //Handle selected state change
            v.setBackgroundResource(R.drawable.shape_click);
            ((Button) v).setTextColor(Color.WHITE);
            exceptList.add(text);
        } else {
            //Handle de-select state change
            v.setBackgroundResource(R.drawable.shape_normal);
            ((Button) v).setTextColor(Color.BLACK);
            exceptList.remove(text);
        }
    }
}