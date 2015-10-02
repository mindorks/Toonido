package app.toonido.mindorks.toonido;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by janisharali on 30/09/15.
 */
public class Dialogs {

    public static Dialog imageMedium(final Context context){

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.popup_image_medium, null);
        Button camera =(Button)v.findViewById(R.id.camera);
        Button gallery =(Button)v.findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,PhotoActivity.class);
                context.startActivity(i);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Gallery",Toast.LENGTH_SHORT).show();
            }
        });

        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottom;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(v);
        return dialog;
    }
}
