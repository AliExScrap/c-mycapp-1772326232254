package com.example.capp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ScrollView;
import android.graphics.Color;
import java.io.*;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.parseColor("#1E1E1E"));
        tv.setPadding(24, 24, 24, 24);
        tv.setTextSize(14);
        tv.setTypeface(android.graphics.Typeface.MONOSPACE);
        
        try {
            String nativeDir = getApplicationInfo().nativeLibraryDir;
            Process p = Runtime.getRuntime().exec(nativeDir + "/main");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            p.waitFor();
            tv.setText(sb.toString());
        } catch (Exception e) {
            tv.setText("Error: " + e.getMessage());
        }
        scroll.addView(tv);
        setContentView(scroll);
    }
}