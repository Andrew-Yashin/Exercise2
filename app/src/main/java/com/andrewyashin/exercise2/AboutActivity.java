package com.andrewyashin.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        final LinearLayout layout = findViewById(R.id.layout);
        TextView text = new TextView(this);
        text.setText("© 2018 Andrew Yashin");
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);


        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        text.setLayoutParams(linearLayoutParams);
        layout.addView(text);

        final EditText message = findViewById(R.id.message);
        final Button sendButton = findViewById(R.id.sendbutton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(message.getText().toString());
            }
        });

        final ImageButton phoneButton = findViewById(R.id.phone);

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();

            }
        });

        final ImageButton telegramButton = findViewById(R.id.telegram);

        telegramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTelegram();
            }
        });

        final ImageButton vkButton = findViewById(R.id.vk);

        vkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVK();
            }
        });
    }


    private void sendMessage(String message) {
        final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setType("message");
        emailIntent.setData(Uri.parse("mailto:andrewyashin@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message from app");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        if(emailIntent.resolveActivity(getPackageManager())!= null){
            startActivity(emailIntent);
        }
        else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
        }

    }


    private void call() {
        final Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:+79169603882"));
        if (phoneIntent.resolveActivity(getPackageManager())!= null){
            startActivity(phoneIntent);
        }
        else {
            Toast.makeText(this, "Can't open phone app", Toast.LENGTH_SHORT).show();
        }

    }

    private void openTelegram() {
        final Intent telegramIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Andrew_Yashin"));
        try {
            startActivity(telegramIntent);
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void openVK() {
        final Intent vkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/id2536289"));
        try {
            startActivity(vkIntent);
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
