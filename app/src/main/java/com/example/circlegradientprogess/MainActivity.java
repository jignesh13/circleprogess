package com.example.circlegradientprogess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CircularProgressBar circularProgressBar;

    private Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circularProgressBar=findViewById(R.id.circularProgressBar);
        circularProgressBar.setTextView((TextView) findViewById(R.id.textView));
        random=new Random();
    }

    public void updateProgess(View view) {
        circularProgressBar.setProgress(random.nextInt(100));

    }
}
