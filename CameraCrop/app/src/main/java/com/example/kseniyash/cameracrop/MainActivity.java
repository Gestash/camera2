package com.example.kseniyash.cameracrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

    private ImageView imageView;
    private final int Pick_image = 1;
    File directory;
    final int TYPE_PHOTO = 1;
    final int TYPE_VIDEO = 2;

    final int REQUEST_CODE_PHOTO = 1;
    final int REQUEST_CODE_VIDEO = 2;

    final String TAG = "myLogs";

    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory();
        ivPhoto = (ImageView) findViewById(R.id.imageView);
        imageView = (ImageView) findViewById(R.id.imageView);
        Button PickImage = (Button) findViewById(R.id.btnGallery);
        //Настраиваем для нее обработчик нажатий OnClickListener:
        PickImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                //Тип получаемых объектов - image:
                photoPickerIntent.setType("image/*");
                //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });
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
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    try {

                        //Получаем URI изображения, преобразуем его в Bitmap
                        //объект и отображаем в элементе ImageView нашего интерфейса:
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            case TYPE_VIDEO:
                    if (resultCode == RESULT_OK) {
                        if (imageReturnedIntent == null) {
                            Log.d(TAG, "Intent is null");
                        } else {
                            Log.d(TAG, "Video uri: " + imageReturnedIntent.getData());
                        }
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled");
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
vvVideo = (VideoView)findViewById(R.id.videoV);
        Button PickVideo = (Button) findViewById(R.id.btnVideoGallery);
        //Настраиваем для нее обработчик нажатий OnClickListener:
        PickImage.setOnClickListener(new OnClickListener() {
@Override
public void onClick(View view) {

        //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        //Тип получаемых объектов - image:
        photoPickerIntent.setType("image/*");
        //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
        startActivityForResult(photoPickerIntent, Pick_image);

        }
        });