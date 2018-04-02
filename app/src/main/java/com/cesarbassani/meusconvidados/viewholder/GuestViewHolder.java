package com.cesarbassani.meusconvidados.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.entities.GuestEntity;

public class GuestViewHolder extends RecyclerView.ViewHolder{

    private TextView mTextName;

    public GuestViewHolder(View itemView) {
        super(itemView);

        this.mTextName = itemView.findViewById(R.id.text_name);
    }

    public void binData(GuestEntity guestEntity) {
        mTextName.setText(guestEntity.getName());
    }
}