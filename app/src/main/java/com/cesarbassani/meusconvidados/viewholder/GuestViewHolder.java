package com.cesarbassani.meusconvidados.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.listener.OnGuestListenerInteractionListener;

public class GuestViewHolder extends RecyclerView.ViewHolder{

    private TextView mTextName;

    public GuestViewHolder(View itemView) {
        super(itemView);

        this.mTextName = itemView.findViewById(R.id.text_name);
    }

    public void binData(final GuestEntity guestEntity, final OnGuestListenerInteractionListener listener) {

        this.mTextName.setText(guestEntity.getName());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(guestEntity.getId());
            }
        });
    }
}
