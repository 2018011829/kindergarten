package com.example.myapplication.school.tool;

import com.example.myapplication.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GetMipmapName {

    Class<R.mipmap> c = R.mipmap.class;
    ArrayList<MipmapImage> images;

    class MipmapImage {

        String fileName;// 名字
        int value;// id
    }

    public GetMipmapName() {
    }

    public String getIdName(int id) {
        images = new ArrayList<MipmapImage>();
        for (Field f : c.getFields()) {
            try {
                MipmapImage image = new MipmapImage();
                image.fileName = f.getName();
                image.value = f.getInt(f);
                images.add(image);
                if (id == image.value) {
                    return image.fileName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}