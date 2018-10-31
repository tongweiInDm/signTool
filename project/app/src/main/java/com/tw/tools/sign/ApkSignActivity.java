package com.tw.tools.sign;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ApkSignActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_sign);

        final EditText editTextPkgName = findViewById(R.id.edit_text_package_name);
        Button btnGetSign = findViewById(R.id.btn_get_sign);
        final EditText editTextSignMd5 = findViewById(R.id.edit_text_sign_md5);
        final EditText editTextSign = findViewById(R.id.edit_text_sign);

        btnGetSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pkgName = editTextPkgName.getText().toString();
                if (TextUtils.isEmpty(pkgName)) {
                    Toast.makeText(ApkSignActivity.this, "Fail to find package name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                PackageInfo packageInfo;
                try {
                    packageInfo = getPackageManager().getPackageInfo(pkgName,
                            PackageManager.GET_SIGNATURES);
                    byte[] signBytes = packageInfo.signatures[0].toByteArray();
                    editTextSign.setText(Base64.encodeToString(signBytes, Base64.NO_WRAP));
                    editTextSignMd5.setText(Utils.getMd5(signBytes));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(ApkSignActivity.this,
                            "Fail to find app by package name" + pkgName,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
