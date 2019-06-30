package com.example.strongcore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class PainExpandableListAdaptor extends BaseExpandableListAdapter {

        private List<PainSection> lstPainSections;
        private Context context;

        public PainExpandableListAdaptor(Context context, List<PainSection> painSections) {
            // inicializa as variáveis da classe
            this.context = context;
            lstPainSections = painSections;
        }

        @Override
        public int getGroupCount() {
            // retorna a quantidade de grupos
            return lstPainSections.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // retorna a quantidade de itens de um grupo
            return lstPainSections.get(groupPosition).getItems().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            // retorna um grupo
            return lstPainSections.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // retorna um item do grupo
            return lstPainSections.get(groupPosition).getItems().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            // retorna o id do grupo, porém como nesse exemplo
            // o grupo não possui um id específico, o retorno
            // será o próprio groupPosition
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // retorna o id do item do grupo, porém como nesse exemplo
            // o item do grupo não possui um id específico, o retorno
            // será o próprio childPosition
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            // retorna se os ids são específicos (únicos para cada
            // grupo ou item) ou relativos
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            // cria os itens principais (grupos)

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.pain_section, null);
            }

            TextView tvGrupo = (TextView) convertView.findViewById(R.id.tvGrupo);
            TextView tvQtde = (TextView) convertView.findViewById(R.id.tvQtde);

            PainSection pain = (PainSection) getGroup(groupPosition);
            tvGrupo.setText(String.valueOf(pain.getIndex()));
            tvQtde.setText(pain.getDescription());

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            // cria os subitens (itens dos grupos)

            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.pain_section_items, null);
                convertView.setClickable(false);
            }

            TextView radioItem = (TextView) convertView.findViewById(R.id.item_radio_button);

            radioItem.setText((String) getChild(groupPosition, childPosition));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // retorna se o subitem (item do grupo) é selecionável
            return true;
        }
}
