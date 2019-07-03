package com.example.strongcore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BodySectionMapCoordinates extends AsyncTask<Void, Void, Boolean> implements Serializable {

    private final Bitmap mapBodyBitmap;
    ArrayList<Integer> mapBody ;
    private MainActivity mainActivity;
    boolean isComplete = false;
    private SplashActivity splashActivity;
    private PersistenceDAO persistenceDAO;

    BodySectionMapCoordinates(SplashActivity splashActivity){
        this.splashActivity = splashActivity;
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
        mapBodyBitmap = ((BitmapDrawable) splashActivity.getResources().getDrawable(R.drawable.mapacorpo)).getBitmap();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if (!isComplete) {
            int red, blue, green, mapRedValue, mapGreenValue, mapBlueValue, pixel;
            try{
            FileOutputStream fileOut =
                    new FileOutputStream(new File(splashActivity.getFilesDir(), "coordinates.sc"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (int i = 0; i < mapBody.size(); i++) {

                mapRedValue = Color.red(mapBody.get(i));
                mapGreenValue = Color.green(mapBody.get(i));
                mapBlueValue = Color.blue(mapBody.get(i));

                ArrayList<Coordinates> coordinates = new ArrayList<>();

                for (int y = 0; y < mapBodyBitmap.getHeight(); y++) {
                    for (int x = 0; x < mapBodyBitmap.getWidth(); x++) {
                        pixel = mapBodyBitmap.getPixel(x, y);
                        red = Color.red(pixel);
                        green = Color.green(pixel);
                        blue = Color.blue(pixel);

                        if (mapRedValue == red && mapGreenValue == green
                                && mapBlueValue == blue) {
                            Log.e("MSG", x + "," + y);
                            out.writeObject(new Coordinates(i, x, y));
                        }
                    }
                }
            }
                out.close();
                fileOut.close();
                Log.e("MSG", "Serialized data is saved in"
                        + splashActivity.getFilesDir().getAbsolutePath() + "coordinates.sc");
            } catch (IOException e) {
                return false;
            }
        }
        SystemClock.sleep(3000);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        start();
    }

    private void start() {
        persistenceDAO = new PersistenceDAO(splashActivity.getApplicationContext());
        if (persistenceDAO.tableExists(persistenceDAO.getReadableDatabase(), persistenceDAO.TABELA) && persistenceDAO.getList().size() > 0) {
            List<Persistence> persistencies = persistenceDAO.getList();
            UserDAO userDAO = new UserDAO(splashActivity.getApplicationContext());
            User user = userDAO.getUserByEmail(persistencies.get(0).getEmail());
            Log.e("User", user.getName());
            Intent mainIntent = new Intent(splashActivity, MainActivity.class);
            mainIntent.putExtra("User", user);
            splashActivity.startActivity(mainIntent);
            splashActivity.finish();
        } else {
            Intent mainIntent = new Intent(splashActivity, LoginActivity.class);
            splashActivity.startActivity(mainIntent);
            splashActivity.finish();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        File file = new File(splashActivity.getFilesDir(), "coordinates.sc");
        if (file.exists()){
            isComplete = true;
        }
    }
}
