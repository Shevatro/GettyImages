package shevatro.gettyimages.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.Sort;

import shevatro.gettyimages.App;
import shevatro.gettyimages.R;
import shevatro.gettyimages.data.db.ImageRealm;
import shevatro.gettyimages.ui.adapter.ListAdapter;

public class ListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @Inject
    Realm realm;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().injectListFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridLayoutManager lLayout = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(new ListAdapter(realm.where(ImageRealm.class).findAllSorted(ImageRealm.DT_NAME, Sort.DESCENDING)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
