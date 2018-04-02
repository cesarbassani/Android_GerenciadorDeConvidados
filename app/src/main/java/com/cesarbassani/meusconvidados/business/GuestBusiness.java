package com.cesarbassani.meusconvidados.business;

import android.content.Context;

import com.cesarbassani.meusconvidados.constants.DataBaseConstants;
import com.cesarbassani.meusconvidados.entities.GuestEntity;
import com.cesarbassani.meusconvidados.repository.GuestRerpository;

import java.util.List;

public class GuestBusiness {

    private GuestRerpository mGuestRerpository;

    public GuestBusiness(Context context) {
        this.mGuestRerpository = GuestRerpository.getINSTANCE(context);
    }

    public Boolean insert(GuestEntity guestEntity) {
        return this.mGuestRerpository.insert(guestEntity);
    }

    public List<GuestEntity> getInvited() {

        return this.mGuestRerpository.getGuestsByQuery("select * from " + DataBaseConstants.GUEST.TABLE_NAME);
    }

    public GuestEntity load(int id) {
        return this.mGuestRerpository.load(id);
    }
}
