package com.example.fyp_robot_dog_androidx.Api_And_Function;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.fyp_robot_dog_androidx.R;

public class CustomAlert {
    public static void alertMessage(android.content.Context context, String title, String message){    // only allow click Yes
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_alert_message);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView AlertTitle = dialog.findViewById(R.id.alerTtitle);
        TextView AlertContent = dialog.findViewById(R.id.alertContent);

        AlertTitle.setText(title);
        AlertContent.setText(message);

        TextView ok = dialog.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static boolean alertChoiceMessage(android.content.Context context, String title, String content, String buttonFalse, String buttonYes){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_alert_message_choice);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView no = dialog.findViewById(R.id.No);
        TextView yes = dialog.findViewById(R.id.Yes);

        final boolean[] clickYes = {false};

        no.setText(buttonFalse);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                clickYes[0] = false;
            }
        });

        yes.setText(buttonYes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
                clickYes[0] = true;
            }
        });
        dialog.show();

        return clickYes[0];
    }
}