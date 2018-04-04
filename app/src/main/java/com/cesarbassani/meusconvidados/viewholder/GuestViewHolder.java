package com.cesarbassani.meusconvidados.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.listener.OnGuestListenerInteractionListener;

public class GuestViewHolder extends RecyclerView.ViewHolder{

    private TextView mTextName;
    private Context mContext;
    private ImageView mImageRemove;

    public GuestViewHolder(View itemView, Context context) {
        super(itemView);

        this.mContext = context;
        this.mTextName = itemView.findViewById(R.id.text_name);
        this.mImageRemove = itemView.findViewById(R.id.image_remove);
    }

    public void binData(final GuestEntity guestEntity, final OnGuestListenerInteractionListener listener) {

        this.mTextName.setText(guestEntity.getName());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(guestEntity.getId());
            }
        });

        this.mImageRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.remover_convidado)
                        .setMessage(R.string.deseja_remover)
                        .setIcon(R.drawable.remove)
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDeleteClick(guestEntity.getId());
                            }
                        })
                        .setNeutralButton(R.string.nao, null)
                        .show();
            }
        });

        this.mTextName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.remover_convidado)
                        .setMessage(R.string.deseja_remover)
                        .setIcon(R.drawable.remove)
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDeleteClick(guestEntity.getId());
                            }
                        })
                        .setNeutralButton(R.string.nao, null)
                        .show();

                return true;
            }
        });
    }
}
