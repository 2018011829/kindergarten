package com.example.myapplication.apply.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.main.activity.MainActivity;

public class Instruction extends AppCompatActivity {
    private Button btnReadFinish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_instruction);

        btnReadFinish = findViewById(R.id.read_finish);
        btnReadFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Instruction.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
