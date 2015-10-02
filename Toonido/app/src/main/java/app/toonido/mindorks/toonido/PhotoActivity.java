package app.toonido.mindorks.toonido;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by janisharali on 01/10/15.
 */
public class PhotoActivity extends Activity {

    protected ImageView photo;
    protected static String imageDetails      = null;
    protected final String TAG="PhotoActivity";
    protected final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    protected Uri imageUri                      = null;
    PhotoActivity CameraActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        CameraActivity = this;
        photo = (ImageView) findViewById(R.id.photo);
        takePhoto();
    }

    public void takePhoto() {
        String fileName = "Camera_Example.jpg";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult( intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data)
    {
        if ( requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if ( resultCode == RESULT_OK) {
                String imageId = convertImageUriToFile( imageUri,CameraActivity);
                new LoadImagesFromSDCard().execute(""+imageId);
            } else if ( resultCode == RESULT_CANCELED) {
                Toast.makeText(this, " Picture was not taken ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, " Picture was not taken ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String convertImageUriToFile ( Uri imageUri, Activity activity )  {

        Cursor cursor = null;
        int imageID = 0;

        try {
            String [] proj={
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.ImageColumns.ORIENTATION
            };
            cursor = activity.managedQuery(

                    imageUri,         //  Get data for specific image URI
                    proj,             //  Which columns to return
                    null,             //  WHERE clause; which rows to return (all rows)
                    null,             //  WHERE clause selection arguments (none)
                    null              //  Order-by clause (ascending by name)

            );
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int columnIndexThumb = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
            int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            int size = cursor.getCount();
            if (size == 0) {
                imageDetails="No Image";
            }
            else
            {
                int thumbID = 0;
                if (cursor.moveToFirst()) {
                    imageID     = cursor.getInt(columnIndex);
                    thumbID     = cursor.getInt(columnIndexThumb);
                    String Path = cursor.getString(file_ColumnIndex);
                    String CapturedImageDetails = " CapturedImageDetails : \n\n"
                            +" ImageID :"+imageID+"\n"
                            +" ThumbID :"+thumbID+"\n"
                            +" Path :"+Path+"\n";
                    imageDetails=CapturedImageDetails;
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return ""+imageID;
    }
    public class LoadImagesFromSDCard  extends AsyncTask<String, Void, Void> {

        private ProgressDialog Dialog = new ProgressDialog(CameraActivity);
        Bitmap mBitmap;
        protected void onPreExecute() {
            Dialog.setMessage(" Loading image from Sdcard..");
            Dialog.show();
        }

        protected Void doInBackground(String... urls) {
            Bitmap bitmap = null;
            Bitmap newBitmap = null;
            Uri uri = null;
            try {
                uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + urls[0]);
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                if (bitmap != null) {
                    newBitmap = Bitmap.createScaledBitmap(bitmap, 170, 170, true);
                    bitmap.recycle();
                    if (newBitmap != null) {
                        mBitmap = newBitmap;
                    }
                }
            } catch (IOException e) {
                Log.d(TAG,"ali photocapture Exception "+e);
                cancel(true);
            }
            return null;
        }


        protected void onPostExecute(Void unused) {
            Dialog.dismiss();
            if(mBitmap != null)
            {
                photo.setImageBitmap(mBitmap);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
