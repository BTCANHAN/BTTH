package com.example.tuan2_th2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail;
    TextView tvError;
    Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        tvError = findViewById(R.id.tvError);
        btnCheck = findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();

                if (email.isEmpty()) {
                    tvError.setText("Email không hợp lệ");
                } else if (!email.contains("@")) {
                    tvError.setText("Email không đúng định dạng");
                } else {
                    tvError.setTextColor(0xFF4CAF50); // xanh lá
                    tvError.setText("Bạn đã nhập email hợp lệ");
                }
            }
        });
    }
}
