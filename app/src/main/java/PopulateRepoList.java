import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemil.gitexplorer.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by hemil on 5/25/2016.
 */
public class PopulateRepoList extends BaseAdapter {


    Context context;
    JSONArray jsonArray;
    LayoutInflater layoutInflater;
    Holder holder;
    static class Holder{
        ImageView imageView;
        TextView name, desc, fork, commit;
    }

    public PopulateRepoList(Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return jsonArray.length();
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
            Picasso.with(context).load( jsonArray.getJSONObject(position).get("avatar_url").toString()).into( holder.imageView);
            holder.name.setText(jsonArray.getJSONObject(position).get("full_name").toString());
            holder.desc.setText(jsonArray.getJSONObject(position).get("description").toString());
            holder.fork.setText(jsonArray.getJSONObject(position).get("forks_count").toString());
            holder.commit.setText(jsonArray.getJSONObject(position).get("watchers").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return null;
    }
}
