package com.example.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.giulieugia;

import java.util.List;

public class DuLieuGiaAdapter extends BaseAdapter {

    Context context;
    int layout ;
    List<giulieugia> arraylist ;

    public DuLieuGiaAdapter(Context context, int layout, List<giulieugia> arraylist) {
        this.context = context;
        this.layout = layout;
        this.arraylist = arraylist;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView twsp;
        TextView twgb;
        ImageView imgAnh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout , null);
            viewHolder = new ViewHolder();

            viewHolder.twsp = (TextView) view.findViewById(R.id.TVsp);
            viewHolder.twgb = (TextView) view.findViewById(R.id.TVgiaban);
            viewHolder.imgAnh = (ImageView) view.findViewById(R.id.imgV);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.twsp.setText(arraylist.get(i).TenSp);
        viewHolder.twgb.setText(arraylist.get(i).Giaban);
        viewHolder.imgAnh.setImageResource(arraylist.get(i).HinhAnh);

        return view;
    }
}
