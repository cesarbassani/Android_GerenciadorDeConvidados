package com.cesarbassani.meusconvidados.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.adapter.GuestListAdapter;
import com.cesarbassani.meusconvidados.business.GuestBusiness;
import com.cesarbassani.meusconvidados.constants.GuestConstants;
import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.listener.OnGuestListenerInteractionListener;

import java.util.List;

public class PresentFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_present, container, false);

        final Context context = view.getContext();

        //Obter a RecyclerView
        this.mViewHolder.mRecyclerViewAllPresent = view.findViewById(R.id.recycler_all_present);

        this.mGuestBusiness = new GuestBusiness(context);

        OnGuestListenerInteractionListener listener = new OnGuestListenerInteractionListener() {
            @Override
            public void onListClick(int id) {
                //Abrir activity de formulario
                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.BundleConstants.GUEST_ID, id);

                Intent intent = new Intent(context, GuestFormActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }

            @Override
            public void onDeleteClick() {

            }
        };

        List<GuestEntity> guestEntityList = this.mGuestBusiness.getPresent();

        //Definir um adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter(guestEntityList, listener);
        this.mViewHolder.mRecyclerViewAllPresent.setAdapter(guestListAdapter);

        //Definir um layout
        this.mViewHolder.mRecyclerViewAllPresent.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewAllPresent;
    }
}
