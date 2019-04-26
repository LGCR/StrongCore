package com.example.strongcore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class CardAdapter extends PagerAdapter {

    int views[] = {R.layout.cardview, R.layout.cardview_body};
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<ExerciseModel> exerciseModels;

    CardAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(views[position], container, false);
        if (position == 0) {
            exerciseModels = new ArrayList<>();
            exerciseModels.add(new ExerciseModel("Prancha"));
            exerciseModels.add(new ExerciseModel("Prancha Lateral"));
            exerciseModels.add(new ExerciseModel("Ponte"));
            exerciseModels.add(new ExerciseModel("Ponte na Bola"));
            exerciseModels.add(new ExerciseModel("Hiperextensão"));
            ExerciseListAdapter adapter = new ExerciseListAdapter(exerciseModels, context);
            ListView listView = view.findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
