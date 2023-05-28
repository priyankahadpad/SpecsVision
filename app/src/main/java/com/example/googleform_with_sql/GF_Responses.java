package com.example.googleform_with_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GF_Responses extends AppCompatActivity {

    private TextView Links;
    private TextView textView3, textView12;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_f__responses);

        textView3 = findViewById(R.id.textView3);
        textView12 = findViewById(R.id.textView12);



        String text = "Click On here, To Paste The Copied Link To QR-Code Generate.";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(GF_Responses.this, QR_Generator.class);
                startActivity(i);
            }
        };

        ss.setSpan(clickableSpan,0,13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView12.setText(ss);
        textView12.setMovementMethod(LinkMovementMethod.getInstance());


        String value = getIntent().getStringExtra("key");

        try {
            textView3.setText(value + "#responses");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

