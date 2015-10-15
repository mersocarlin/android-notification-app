package com.mersocarlin.notificationapp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.mersocarlin.notificationapp.R;
import com.mersocarlin.notificationapp.ui.fragment.dummy.DummyContent;

import java.util.List;

public class HomeFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static final String ARG_ITEM_PREFIX = "itemPrefix";
    private static final String ARG_TOTAL_ITEMS = "totalItems";

    private String itemPrefix;
    private int totalItems;

    private OnFragmentInteractionListener mListener;

    private AbsListView mListView;

    private ListAdapter mAdapter;

    private List<DummyContent.DummyItem> items;

    public static HomeFragment newInstance(String itemPrefix, int totalItems) {
        HomeFragment fragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putString(ARG_ITEM_PREFIX, itemPrefix);
        args.putInt(ARG_TOTAL_ITEMS, totalItems);

        fragment.setArguments(args);

        return fragment;
    }

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.itemPrefix = getArguments().getString(ARG_ITEM_PREFIX);
            this.totalItems = getArguments().getInt(ARG_TOTAL_ITEMS);
        }

        this.items = DummyContent.createItems(this.itemPrefix, this.totalItems);

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener == null) {
            return;
        }

        mListener.onFragmentInteraction(this.items.get(position).id);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String id);
    }

}
