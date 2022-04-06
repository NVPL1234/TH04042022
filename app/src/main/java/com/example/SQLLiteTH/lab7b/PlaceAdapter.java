package com.example.SQLLiteTH.lab7b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lab7a.R;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {
    private Context context;
    private List<String> nameList;
    private int layout;

    public PlaceAdapter(Context context, List<String> nameList, int layout) {
        this.context = context;
        this.nameList = nameList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int i) {
        return nameList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        }
        TextView txtName=(TextView) view.findViewById(R.id.txtNamePlace);
        txtName.setText(nameList.get(i));
        ImageButton btnDelete=(ImageButton) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=getItem(i).toString();
                DatabaseHandler databaseHandler=new DatabaseHandler(context);
                databaseHandler.deletePlace(name);
                removeName(name);
                getView(i, view, viewGroup);
            }
        });
        return view;
    }

    public void addName(String name){
        nameList.add(name);
    }
    public void removeName(String name){
        nameList.remove(name);
    }
}
