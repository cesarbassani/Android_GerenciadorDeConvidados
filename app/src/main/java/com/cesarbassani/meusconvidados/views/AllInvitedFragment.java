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
import android.widget.TextView;

import com.cesarbassani.meusconvidados.R;
import com.cesarbassani.meusconvidados.adapter.GuestListAdapter;
import com.cesarbassani.meusconvidados.business.GuestBusiness;
import com.cesarbassani.meusconvidados.constants.GuestConstants;
import com.cesarbassani.meusconvidados.entities.GuestCount;
import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.listener.OnGuestListenerInteractionListener;

import java.util.List;

public class AllInvitedFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestBusiness mGuestBusiness;
    private OnGuestListenerInteractionListener mOnGuestListenerInteractionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);

        final Context context = view.getContext();

        //Obter a RecyclerView
        this.mViewHolder.mRecyclerViewAllInvited = view.findViewById(R.id.recycler_all_invited);
        this.mViewHolder.mTextPresentCount = view.findViewById(R.id.text_present_count);
        this.mViewHolder.mTextAbsentCount = view.findViewById(R.id.text_absent_count);
        this.mViewHolder.mTextAllInvitedCount = view.findViewById(R.id.text_all_invited);

        this.mGuestBusiness = new GuestBusiness(context);

        this.mOnGuestListenerInteractionListener = new OnGuestListenerInteractionListener() {
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
            public void onDeleteClick(int id) {
                mGuestBusiness.remove(id);
            }
        };

        //Definir um layout
        this.mViewHolder.mRecyclerViewAllInvited.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.loadDashboard();

        this.loadGuests();
    }

    private void loadGuests() {
        List<GuestEntity> guestEntityList = this.mGuestBusiness.getInvited();

        //Definir um adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter(guestEntityList, this.mOnGuestListenerInteractionListener);
        this.mViewHolder.mRecyclerViewAllInvited.setAdapter(guestListAdapter);

        guestListAdapter.notifyDataSetChanged();
    }

    private void loadDashboard() {

        GuestCount guestCount = this.mGuestBusiness.loadDashboard();

        this.mViewHolder.mTextAllInvitedCount.setText(String.valueOf(guestCount.getAllInvitedCount()));
        this.mViewHolder.mTextPresentCount.setText(String.valueOf(guestCount.getPresentCount()));
        this.mViewHolder.mTextAbsentCount.setText(String.valueOf(guestCount.getAbsentCount()));
    }

    private static class ViewHolder {
        RecyclerView mRecyclerViewAllInvited;
        TextView mTextPresentCount;
        TextView mTextAbsentCount;
        TextView mTextAllInvitedCount;
    }
}
