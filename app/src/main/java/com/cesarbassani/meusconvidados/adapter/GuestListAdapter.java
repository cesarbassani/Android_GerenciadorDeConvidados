package com.cesarbassani.meusconvidados.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.listener.OnGuestListenerInteractionListener;
import com.cesarbassani.meusconvidados.viewholder.GuestViewHolder;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestViewHolder> {

    private List<GuestEntity> mGuestEntityList;
    private OnGuestListenerInteractionListener mOnGuestListenerInteractionListener;

    public GuestListAdapter(List<GuestEntity> guestEntityList, OnGuestListenerInteractionListener listener) {

        this.mGuestEntityList = guestEntityList;
        this.mOnGuestListenerInteractionListener = listener;
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View guestView = layoutInflater.inflate(R.layout.row_guest_list, parent, false) ;

        return new GuestViewHolder(guestView);

    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        //Entidade convidado
        GuestEntity guestEntity = this.mGuestEntityList.get(position);
        holder.binData(guestEntity, mOnGuestListenerInteractionListener);
    }

    @Override
    public int getItemCount() {
        return this.mGuestEntityList.size();
    }
}
