package com.example.biteright.utilits;

import android.content.Context;
import android.content.DialogInterface;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ShowAlert {
    public static void showAlert(Context context,String title,String message){

            MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
//                        Navigation.findNavController(_v).navigate(R.id.action_registrationFragment_to_homeFragment);
//                        clearFields();

                        }
                    });
            //.setIcon(R.drawable.biterightwithoutcalendar);

            builder.create();
            builder.show();


    }
}
