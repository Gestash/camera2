package com.example.kseniyash.mediasender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends Activity   {

    ImageView ivPhoto;
    VideoView vvVideo;
    File directory;
    private final int Pick_media = 1;
    private final int TYPE_PHOTO = 1;
    private final int TYPE_VIDEO = 2;
    final int REQUEST_CODE_PHOTO = 1;
    final int REQUEST_CODE_VIDEO = 2;
    final String TAG = "myLogs";//строка для вывода логов
    Uri mUri;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createDirectory();
        ivPhoto = (ImageView) findViewById(R.id.imageView);
        VideoView videoView = (VideoView)findViewById(R.id.videoView);
    }
    public void onClickGallery(View view) {
        Intent pickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickerIntent.setType("image/* video/*");
        pickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pickerIntent, Pick_media);
    }
    public void onClickPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }
    public void onClickVideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_VIDEO));
        startActivityForResult(intent, REQUEST_CODE_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        switch (requestCode) {
            case Pick_media:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedMediaUri = imageReturnedIntent.getData();
                        if (selectedMediaUri.toString().contains("image/*")) {
                            //handle image
                            final InputStream imageStream = getContentResolver().openInputStream(selectedMediaUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            ivPhoto.setImageBitmap(selectedImage);
                        } else  if (selectedMediaUri.toString().contains("video/*")) {
                            //handle video
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    private Uri generateFileUri(int type) {
        File file = null;
        switch (type) {
            case TYPE_PHOTO:
                file = new File(directory.getPath() + "/" + "photo_"
                        + System.currentTimeMillis() + ".jpg");
                break;
            case TYPE_VIDEO:
                file = new File(directory.getPath() + "/" + "video_"
                        + System.currentTimeMillis() + ".mp4");
                break;
        }
        Log.d(TAG, "fileName = " + file);
        return Uri.fromFile(file);
    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }
}