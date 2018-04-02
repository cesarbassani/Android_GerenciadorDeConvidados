package com.cesarbassani.meusconvidados.business;

import android.content.Context;

import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.repository.GuestRerpository;

public class GuestBusiness {

    private GuestRerpository mGuestRerpository;

    public GuestBusiness(Context context) {
        this.mGuestRerpository = GuestRerpository.getINSTANCE(context);
    }

    public Boolean insert(GuestEntity guestEntity) {
        return this.mGuestRerpository.insert(guestEntity);
    }
}
