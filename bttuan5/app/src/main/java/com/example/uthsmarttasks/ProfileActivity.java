package com.example.uthsmarttasks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtDob;
    private ImageView imgAvatar;
    private Button btnBackFull;
    private ImageButton btnBack;

    private SharedPreferences prefs;
    private static final String PREFS_NAME = "UserProfilePrefs";
    private static final String KEY_DOB = "dob";

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtDob = findViewById(R.id.edtDob);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnBackFull = findViewById(R.id.btnBackFull);
        btnBack = findViewById(R.id.btnBack);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getDisplayName() != null) edtName.setText(user.getDisplayName());
            if (user.getEmail() != null) edtEmail.setText(user.getEmail());
        }

        // Lấy DOB lưu trữ
        String savedDob = prefs.getString(KEY_DOB, "23/05/1995");
        edtDob.setText(savedDob);

        edtDob.setOnClickListener(v -> showDatePickerDialog());
        findViewById(R.id.profileRoot).setOnTouchListener((v, event) -> {
            hideKeyboard();
            return false;
        });

        btnBackFull.setOnClickListener(v -> logoutAndReturn());
        btnBack.setOnClickListener(v -> logoutAndReturn());
    }

    private void logoutAndReturn() {
        saveDob();
        if (mGoogleSignInClient != null) mGoogleSignInClient.signOut();
        mAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        String[] parts = edtDob.getText().toString().split("/");
        int year = parts.length == 3 ? Integer.parseInt(parts[2]) : calendar.get(Calendar.YEAR);
        int month = parts.length == 3 ? Integer.parseInt(parts[1]) - 1 : calendar.get(Calendar.MONTH);
        int day = parts.length == 3 ? Integer.parseInt(parts[0]) : calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    String date = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                    edtDob.setText(date);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void saveDob() {
        prefs.edit().putString(KEY_DOB, edtDob.getText().toString()).apply();
    }
}
