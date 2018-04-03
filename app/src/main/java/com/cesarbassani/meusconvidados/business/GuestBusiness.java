package com.cesarbassani.meusconvidados.business;

import android.content.Context;

import com.cesarbassani.meusconvidados.constants.DataBaseConstants;
import com.cesarbassani.meusconvidados.constants.GuestConstants;
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

    public boolean update(GuestEntity guestEntity) {
        return this.mGuestRerpository.update(guestEntity);
    }

    public List<GuestEntity> getInvited() {

        return this.mGuestRerpository.getGuestsByQuery("select * from " + DataBaseConstants.GUEST.TABLE_NAME);
    }

    public List<GuestEntity> getPresent() {

        return this.mGuestRerpository.getGuestsByQuery("select * from " + DataBaseConstants.GUEST.TABLE_NAME + " where " +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = " + GuestConstants.CONFIRMATION.PRESENT);
    }

    public List<GuestEntity> getAbsent() {

        return this.mGuestRerpository.getGuestsByQuery("select * from " + DataBaseConstants.GUEST.TABLE_NAME + " where " +
            DataBaseConstants.GUEST.COLUMNS.PRESENCE + " = " + GuestConstants.CONFIRMATION.ABSENT);
    }

    public GuestEntity load(int id) {
        return this.mGuestRerpository.load(id);
    }
}
