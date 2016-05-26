package com.example.hemil.gitexplorer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemil.gitexplorer.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

/**
 * Created by hemil on 5/25/2016.
 */
public class PopulateRepoList extends BaseAdapter {


    Context context;
    JsonArray jsonArray;
  //  JSONObject[] jsonArray;
    LayoutInflater layoutInflater;
    Holder holder;
    static class Holder{
        ImageView imageView;
        TextView name, desc, fork, commit;
    }

    public PopulateRepoList(Context context, JsonArray jsonArray){
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
            convertView = layoutInflater.inflate(R.layout.repo_items,null);

            holder = new Holder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.avatar_image);
            holder.name = (TextView) convertView.findViewById(R.id.full_name);
            holder.desc = (TextView) convertView.findViewById(R.id.description);
            holder.fork = (TextView) convertView.findViewById(R.id.fork_count);
            holder.commit = (TextView) convertView.findViewById(R.id.watchers);

            convertView.setTag(holder);
        }
        else {
                holder = (Holder) convertView.getTag();
        }

        try {

//    if(jsonArray.get(position).getAsJsonObject().get("owner").getAsJsonObject().has("avatar_url")){
//        Picasso.with(context).load( jsonArray.get(position).getAsJsonObject().get("owner").getAsJsonObject().get("avatar_url").toString()).into( holder.imageView);
//    }else            holder.imageView.setImageResource(R.drawable.noimage);

            String str = jsonArray.get(position).getAsJsonObject().get("owner").getAsJsonObject().get("avatar_url").toString();
            str = str.substring(1,str.length()-1);
            Picasso.with(context).load( str).into( holder.imageView);
            holder.name.setText((jsonArray.get(position)).getAsJsonObject().get("full_name").toString());
            holder.desc.setText(jsonArray.get(position).getAsJsonObject().get("description").toString());
            holder.fork.setText(jsonArray.get(position).getAsJsonObject().get("forks_count").toString());
            holder.commit.setText(jsonArray.get(position).getAsJsonObject().get("watchers").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
