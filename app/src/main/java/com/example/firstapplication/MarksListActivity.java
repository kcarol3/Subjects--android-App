package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MarksListActivity extends AppCompatActivity {
    private ArrayList<MarksModel> mMarksList;
    private int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_list);

        Intent intent = getIntent();
        mMarksList = new ArrayList<>();
        String [] subjects = getResources().getStringArray(R.array.subjects);
        for (int i=0;i<intent.getIntExtra("marks",5);i++){
            mMarksList.add(new MarksModel(subjects[i],2));
        }
        RecyclerView marksRecyclerView = findViewById(R.id.listMarks);
        ArrayAdapter myAdapter=new ArrayAdapter(this,mMarksList);
        marksRecyclerView.setAdapter(myAdapter);
        marksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button avrButton = findViewById(R.id.avrButton);
        avrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (MarksModel model : mMarksList){
                    sum+=model.getMark();
                }
                String avr = String.valueOf(sum/(float)mMarksList.size());
                sum=0;

                Intent intent = new Intent();
                intent.putExtra("avr",avr);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}