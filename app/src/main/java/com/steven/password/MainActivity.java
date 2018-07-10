package com.steven.password;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.steven.password.widget.PayPasswordView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.payBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayPasswordDialog();
            }
        });
    }

    private void openPayPasswordDialog() {
        PayPasswordView payPasswordView = new PayPasswordView(this);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(payPasswordView);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
    }
}
