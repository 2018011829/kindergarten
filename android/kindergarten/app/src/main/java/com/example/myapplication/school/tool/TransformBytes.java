package com.example.myapplication.school.tool;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class TransformBytes {

    /**
     * 将bitmap对象转换成字节数组
     * @param bm
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();
    }
}
