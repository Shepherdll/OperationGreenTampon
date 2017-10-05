package com.example.leakypete.operationgreentampon.models;

import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by Leaky Pete on 10/2/2017.
 */

public class PostMalone {
    String title;
    ImageView thumbnail;
    String username;
    String email;

    public PostMalone(String title,String username,String email) {
        this.title = title;
        //this.thumbnail = thumbnail;
        this.username = username;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
