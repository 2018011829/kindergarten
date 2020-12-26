package com.example.myapplication.school.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class ImageShower extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);

        ImageView imageView=findViewById(R.id.big_img);

        Intent intent=getIntent();
        //获取图片的值
        byte buf[] = intent.getByteArrayExtra("img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(buf, 0, buf.length);
        Bitmap bigBitmap = resizeImage(bitmap);
        //将图片显示显示在控件中
        Glide.with(ImageShower.this)
                .load(bigBitmap)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.faliure)
                .into(imageView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public Bitmap resizeImage(Bitmap bitmap) {
        // load the origial Bitmap
        Bitmap BitmapOrg = bitmap;

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = ImageShower.this.getWindowManager().getDefaultDisplay().getWidth();
        int newHeight = ImageShower.this.getWindowManager().getDefaultDisplay().getHeight();

        Log.v("tag",String.valueOf(width));
        Log.v("tag",String.valueOf(height));

        Log.v("tag",String.valueOf(newWidth));
        Log.v("tag",String.valueOf(newHeight));

        // calculate the scale
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);


        return resizedBitmap;

    }

}
