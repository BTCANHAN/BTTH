package com.example.tuan2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtNumber;
    Button btnCreate;
    TextView tvError;
    LinearLayout listContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNumber = findViewById(R.id.edtNumber);
        btnCreate = findViewById(R.id.btnCreate);
        tvError = findViewById(R.id.tvError);
        listContainer = findViewById(R.id.listContainer);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listContainer.removeAllViews(); // Xoá danh sách cũ
                tvError.setVisibility(View.GONE); // Ẩn lỗi cũ

                String input = edtNumber.getText().toString().trim();

                // Kiểm tra nhập rỗng
                if (input.isEmpty()) {
                    showError("Dữ liệu bạn nhập không hợp lệ");
                    return;
                }

                try {
                    int n = Integer.parseInt(input);
                    if (n <= 0) {
                        showError("Dữ liệu bạn nhập không hợp lệ");
                        return;
                    }

                    // Tạo danh sách các nút
                    for (int i = 1; i <= n; i++) {
                        TextView item = new TextView(MainActivity.this);
                        item.setText(String.valueOf(i));
                        item.setTextColor(0xFFFFFFFF);
                        item.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        item.setBackgroundColor(0xFFFF4444);
                        item.setPadding(0, 20, 0, 20);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                400,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(0, 15, 0, 0);
                        item.setLayoutParams(params);
                        item.setBackgroundResource(R.drawable.red_button_bg);
                        listContainer.addView(item);
                    }

                } catch (NumberFormatException e) {
                    showError("Dữ liệu bạn nhập không hợp lệ");
                }
            }
        });
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
    }
}