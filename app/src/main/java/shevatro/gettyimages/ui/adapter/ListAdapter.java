package shevatro.gettyimages.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import shevatro.gettyimages.other.Config565Transformation;
import shevatro.gettyimages.R;
import shevatro.gettyimages.data.db.ImageRealm;
import shevatro.gettyimages.ui.holder.ViewHolder;

import com.squareup.picasso.Picasso;

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> implements RealmChangeListener {

    private final RealmResults<ImageRealm> images;

    public ListAdapter(RealmResults<ImageRealm> images) {
        this.images = images;
        images.addChangeListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(images.get(position).getName());
        Picasso.with(holder.image.getContext()).load(images.get(position).getUri())
                .resize(400, 300)
                .transform(new Config565Transformation())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }
}
