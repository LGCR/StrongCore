package com.example.strongcore;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;


class PaintBodySection extends AsyncTask<Void, Void, Void> {
    private PainActivity painActivity;
    private ArrayList<Integer> mapBody;
    private Bitmap mapBodyBitmap;
    private Bitmap mutableBitmap;
    private Canvas bodyCanvas;
    private ImageView imageView;
    private MainActivity mainActivity;
    private int groupPosition;
    private int childPosition;
    ArrayList<Integer> painLevel;
    private ArrayList<Coordinates> coordinates;
    private int range;

    public PaintBodySection(PainActivity painActivity,
                            Bitmap mapBodyBitmap, Bitmap mutableBitmap,
                            Canvas bodyCanvas, ImageView imageView,
                            MainActivity mainActivity,
                            int groupPosition, int childPosition) {
        this.painActivity = painActivity;
        this.mapBodyBitmap = mapBodyBitmap;
        this.mutableBitmap = mutableBitmap;
        this.bodyCanvas = bodyCanvas;
        this.imageView = imageView;
        this.mainActivity = mainActivity;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        painLevel = new ArrayList<>();
        painLevel.add(Color.rgb(0, 255, 60));
        painLevel.add(Color.rgb(230, 247, 114));
        painLevel.add(Color.rgb(255, 183, 3));
        painLevel.add(Color.rgb(255, 78, 3));
        painLevel.add(Color.rgb(255, 0, 0));

        mapBody = new ArrayList<>();
        mapBody.add(Color.rgb(255, 0, 0));
        mapBody.add(Color.rgb(54, 0, 255));
        mapBody.add(Color.rgb(182, 212, 63));
        mapBody.add(Color.rgb(148, 189, 158));
        mapBody.add(Color.rgb(69, 177, 95));
        mapBody.add(Color.rgb(38, 174, 172));
        mapBody.add(Color.rgb(228, 0, 255));
        mapBody.add(Color.rgb(181, 38, 38));
        mapBody.add(Color.rgb(244, 171, 46));
        mapBody.add(Color.rgb(51, 40, 24));
        mapBody.add(Color.rgb(2, 98, 12));
        mapBody.add(Color.rgb(69, 112, 2));
        mapBody.add(Color.rgb(159, 70, 152));
        mapBody.add(Color.rgb(154, 30, 255));
        mapBody.add(Color.rgb(42, 230, 255));
        mapBody.add(Color.rgb(25, 190, 255));
        mapBody.add(Color.rgb(120, 160, 185));
    }

    @Override
    protected Void doInBackground(Void... voids) {
        range = mapBodyBitmap.getHeight() / 8;
        PaintSection ps1 = new PaintSection(0, range);
        PaintSection ps2 = new PaintSection(range, range * 2);
        PaintSection ps3 = new PaintSection(range * 2, range * 3);
        PaintSection ps4 = new PaintSection(range * 3, range * 4);
        PaintSection ps5 = new PaintSection(range * 4, range * 5);
        PaintSection ps6 = new PaintSection(range * 5, range * 6);
        PaintSection ps7 = new PaintSection(range * 6, range * 7);
        PaintSection ps8 = new PaintSection(range * 7, mapBodyBitmap.getHeight());

        ps1.execute();
        ps2.execute();
        ps3.execute();
        ps4.execute();
        ps5.execute();
        ps6.execute();
        ps7.execute();
        ps8.execute();

        return null;
    }

    class PaintSection extends AsyncTask<Void, Void, Void> {

        private int init;
        private int range;

        public PaintSection(int init, int range) {
            this.init = init;
            this.range = range;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int mapPixel = mapBody.get(groupPosition);
            int mapRedValue = Color.red(mapPixel);
            int mapGreenValue = Color.green(mapPixel);
            int mapBlueValue = Color.blue(mapPixel);
            int redValue, greenValue, blueValue, pixel;
            for (int y = init; y < mapBodyBitmap.getHeight(); y++) {
                if (y == (range-1)){
                    break;
                }
                for (int x = 0; x < mapBodyBitmap.getWidth(); x++) {
                    pixel = mapBodyBitmap.getPixel(x, y);
                    redValue = Color.red(pixel);
                    greenValue = Color.green(pixel);
                    blueValue = Color.blue(pixel);
                    if (mapRedValue == redValue && mapGreenValue == greenValue
                            && mapBlueValue == blueValue) {
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setColor(painLevel.get(childPosition));
                        bodyCanvas.drawPoint(x, y, paint);
                    }
                }
            }
            painActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageDrawable(new
                            BitmapDrawable(painActivity.getResources(), mutableBitmap));
                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
