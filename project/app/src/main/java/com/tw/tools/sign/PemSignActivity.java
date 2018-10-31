package com.tw.tools.sign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class PemSignActivity extends AppCompatActivity {
    private static final String MARK_STRING = "---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pem_sign);

        final EditText editTextCerContent = findViewById(R.id.edit_text_cer_content);
        final EditText editTextSignMd5 = findViewById(R.id.edit_text_sign_md5);
        findViewById(R.id.btn_get_sign_md5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editTextCerContent.getText().toString();
                editTextSignMd5.setText(getSignMd5(content));
            }
        });
    }

    private String getSignMd5(String cerContent) {
        String[] lines = cerContent.split("\n");
        if (lines == null && lines.length == 0) {
            return null;
        }

        int beginLineIndex = 0;
        int lineCount = lines.length;
        String firstLine = lines[0];
        if (firstLine.contains(MARK_STRING)) {
            beginLineIndex = 1;
            lineCount--;
        }
        String endLine = lines[lines.length - 1];
        if (endLine.contains(MARK_STRING)) {
            lineCount--;
        }
        StringBuilder base64SignBuilder = new StringBuilder();
        for (int i = 0;i < lineCount;i++) {
            base64SignBuilder.append(lines[i + beginLineIndex]);
        }
        return Utils.getMd5(Base64.decode(base64SignBuilder.toString(), 0));
    }
}
