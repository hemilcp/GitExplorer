package com.example.hemil.gitexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;

/**
 * Created by hemil on 5/26/2016.
 */
public class PopulateCommitAdapter extends BaseAdapter {

    JsonArray jsonArray;
    Context context;
    LayoutInflater layoutInflater;
    Holder holder;

    static class Holder{
        TextView author,date,message;
    }

    public PopulateCommitAdapter(Context context, JsonArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return jsonArray.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = layoutInflater.inflate(R.layout.commit_item,null);
            holder = new Holder();

            holder.author = (TextView) convertView.findViewById(R.id.author_name);
            holder.date = (TextView) convertView.findViewById(R.id.commit_date);
            holder.message = (TextView) convertView.findViewById(R.id.message);

            convertView.setTag(holder);
        }
        else holder = (Holder) convertView.getTag();

        holder.author.setText(jsonArray.get(position).getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("name").toString());
        holder.date.setText(jsonArray.get(position).getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date").toString());
        holder.message.setText(jsonArray.get(position).getAsJsonObject().get("commit").getAsJsonObject().get("message").toString());

        return convertView;
    }
}
