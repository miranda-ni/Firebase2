package com.example.taskapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    private String verificationID;
    private EditText editPhone, editText;
    private Button check, send;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private TextView countryPhoneCode;
    private LottieAnimationView lottieAnimationView;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);


        editText = findViewById(R.id.editText);
        check = findViewById(R.id.check);
        send = findViewById(R.id.send);
        editPhone = findViewById(R.id.editPhone);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        countryPhoneCode = findViewById(R.id.countryPhoneCode);

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                phoneAuthCredential.getSmsCode();
                Log.e("phone", "onVerificationCompleted: ");
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    verifyCode(code);

                }
            }


            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("phone", "onVerificationFailed: " + e.getMessage());

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.e("phone", "onCodeSent: ");
                verificationID = s;
                editText.setVisibility(View.VISIBLE);
                check.setVisibility(View.VISIBLE);
                lottieAnimationView.setAnimation(R.raw.circle);

            }
        };
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
        signIn(credential);
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
//                            startActivity(new Intent(PhoneActivity.this, MainActivity.class));
//                            finish();
                      }else {
                Log.e("phone", "Error = " + task.getException().getMessage());
                Toast.makeText(PhoneActivity.this, "Ошибка авторизации", Toast.LENGTH_SHORT);}

            }

        });
    }


    public void onClickSend(View view) {
        String phone = countryPhoneCode.getText().toString().trim() + editPhone.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 60, TimeUnit.SECONDS, this, callbacks);
        Log.e("phone", "onClick: phone number" + phone);
        editPhone.setVisibility(View.GONE);
        send.setVisibility(View.GONE);
        countryPhoneCode.setVisibility(View.GONE);
        lottieAnimationView.setVisibility(View.GONE);


    }

    public void onCheck(View view) {
        String code = editText.getText().toString().trim();
        if (code.isEmpty() || code.length() < 6) {
            editText.requestFocus();
            return;


        }
        verifyCode(code);
        finish();


    }


}
