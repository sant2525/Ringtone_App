package com.devloper.ringtone_app.adapter;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FavRingtoneModel {

    private String ringtoe_id;
    private String user_id;
    private boolean isFav;

    public FavRingtoneModel() {
    }

    public String getRingtoe_id() {
        return ringtoe_id;
    }

    public void setRingtoe_id(String ringtoe_id) {
        this.ringtoe_id = ringtoe_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}
