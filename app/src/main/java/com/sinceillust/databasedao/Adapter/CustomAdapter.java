package com.sinceillust.databasedao.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.sinceillust.databasedao.DAO.Word;
import com.sinceillust.databasedao.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Since on 9/1/2558.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Word> mWords;
    private ViewHolder mViewHolder;

    private Word mWord;
    private Context mContext;

    public CustomAdapter(Context context,List<Word> words){
        Log.e("since","CustomAdapter Constructor");
        this.mContext = context;
        this.mWords = words;
        this.mInflater = (LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Show Count list item
    @Override
    public int getCount() {
        return mWords.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("since","CustomAdapter getView " + position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_item_word, parent, false);

            //view matching
            mViewHolder = new ViewHolder();
            mViewHolder.Word = (TextView) convertView.findViewById(R.id.item_word_title);
            mViewHolder.Trans = (TextView) convertView.findViewById(R.id.item_word_Trans);
            mViewHolder.CatID = (TextView) convertView.findViewById(R.id.item_word_Category_title);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        if(getCount()==0){
            Toast.makeText(mContext,"No Data",Toast.LENGTH_SHORT).show();
        } else {
            //Seting
            mWord = mWords.get(position);
            mViewHolder.Word.setText(mWord.getWord());
            mViewHolder.Trans.setText(mWord.getTermino());
            mViewHolder.CatID.setText(String.valueOf(mWord.getCat_id()));
        }

        return convertView;
    }

    private static class ViewHolder {
        //ImageView thumbnail;
        TextView Word;
        TextView Trans;
        TextView Ter;
        TextView CatID;
    }
}
