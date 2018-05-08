package com.natallia.radaman.epamlabnotewithcontentprovider;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author Natallia Radaman
 * @since 05-2018
 */
public class DataCursorAdapter extends BaseAdapter {
    private Context mContext;

    private List<String[]> mData;

    public DataCursorAdapter(Context context, List<String[]> data) {
        mContext = context;
        mData = data;
    }

    public void setData(List<String[]> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ConstraintLayout constraintLayout = (ConstraintLayout) layoutInflater
                .inflate(R.layout.note_list_view, null);

        TextView rowInt = constraintLayout.findViewById(R.id.text_view_row_number);
        TextView rowString = constraintLayout.findViewById(R.id.text_view_row_name);

        rowString.setText(mData.get(i)[1]);
        rowInt.setText(mData.get(i)[2]);

        return constraintLayout;
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(mData.get(i)[0]);
    }
}
