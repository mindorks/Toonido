package app.toonido.mindorks.toonido;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Toast;

import com.parse.ParseObject;

public class HomeActivity extends Activity {

    LinearLayout changeHeads,makeComic,funFeed;
    private final static String TAG="HomeActivity";
    Context context;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        changeHeads = (LinearLayout)findViewById(R.id.changeHeads);
        makeComic = (LinearLayout)findViewById(R.id.makeComic);
        funFeed = (LinearLayout)findViewById(R.id.funFeed);
        context=this.getApplicationContext();

        changeHeads.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {
                Dialogs.imageMedium(HomeActivity.this).show();

             }
        });

        makeComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.imageMedium(HomeActivity.this).show();
            }
        });

        funFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
