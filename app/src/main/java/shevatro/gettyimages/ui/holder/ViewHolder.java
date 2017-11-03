package shevatro.gettyimages.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import shevatro.gettyimages.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import shevatro.gettyimages.ui.adapter.ListAdapter;

/**
 * Holder for {@link ListAdapter}
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name) public TextView name;
    @BindView(R.id.image) public ImageView image;

    public ViewHolder(final View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
