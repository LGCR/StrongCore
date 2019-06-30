package com.example.strongcore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PainActivity extends AppCompatActivity {

    private View groupView;
    private RadioButton lastRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain);

        getSupportActionBar().hide();

        final View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        final int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {

            @Override
            public void onSystemUiVisibilityChange(int visibility)
            {
                if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                {
                    decorView.setSystemUiVisibility(uiOptions);
                }
            }
        });

        final ExpandableListView elvPainSections = findViewById(R.id.elv_pain_sections);

        // cria os itens de cada grupo
        List<PainSection> painSections = new ArrayList<>();
        painSections.add(new PainSection(1, "Pacote de bala"));
        painSections.add(new PainSection(2, "Pacote de chiclete"));
        painSections.add(new PainSection(3, "Bolo de chocolate"));
        painSections.add(new PainSection(4, "Bolo de chocolate"));
        painSections.add(new PainSection(5, "Bolo de chocolate"));
        painSections.add(new PainSection(6, "Bolo de chocolate"));
        painSections.add(new PainSection(7, "Bolo de chocolate"));
        painSections.add(new PainSection(8, "Bolo de chocolate"));
        painSections.add(new PainSection(9, "Bolo de chocolate"));
        painSections.add(new PainSection(10, "Bolo de chocolate"));
        painSections.add(new PainSection(11, "Bolo de chocolate"));
        painSections.add(new PainSection(12, "Bolo de chocolate"));
        painSections.add(new PainSection(13, "Bolo de chocolate"));
        painSections.add(new PainSection(14, "Bolo de chocolate"));
        painSections.add(new PainSection(15, "Bolo de chocolate"));
        painSections.add(new PainSection(16, "Bolo de chocolate"));



        // cria um adaptador (BaseExpandableListAdapter) com os dados acima
        final PainExpandableListAdaptor adaptador = new PainExpandableListAdaptor(this, painSections);
        // define o apadtador do ExpandableListView
        elvPainSections.setAdapter(adaptador);
        elvPainSections.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    parent.expandGroup(groupPosition);
                    groupView = v;
                }
                return true;
            }
        });
        elvPainSections.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                RadioButton radioButton = v.findViewById(R.id.item_radio_button);

                if (lastRadioButton != null){
                    lastRadioButton.setChecked(false);
                }
                if (radioButton.isChecked()) {
                    radioButton.setChecked(false);
                } else {
                    radioButton.toggle();
                    lastRadioButton = radioButton;
                }
                return true;
            }
        });
    }

}