package com.scubacabs.init;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.scubacabs.R;

public class SelectTypeActivity extends AppCompatActivity {
    ImageView selectdriver,selectpassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        getSupportActionBar().hide();
        selectdriver = findViewById(R.id.selectdriver);
        selectpassenger = findViewById(R.id.selectpassenger);

        selectdriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeActivity.this,RegisterDriverActivity.class);
                startActivity(intent);
                finish();
            }
        });

        selectpassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeActivity.this,RegisterPassengerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
