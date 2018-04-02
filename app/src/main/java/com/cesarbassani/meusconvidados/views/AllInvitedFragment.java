package com.cesarbassani.meusconvidados.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.adapter.GuestListAdapter;

public class AllInvitedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);

        Context context = view.getContext();

        //Obter a RecyclerView
        this.mViewHolder.mRecyclerViewAllInvited = view.findViewById(R.id.recycler_all_invited);

        //Definir um adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter();
        this.mViewHolder.mRecyclerViewAllInvited.setAdapter(guestListAdapter);

        //Definir um layout
        this.mViewHolder.mRecyclerViewAllInvited.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewAllInvited;
    }
}
