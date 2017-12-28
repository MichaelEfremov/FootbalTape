package android.mike.ru.footbaltape;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Michael on 26.12.2017.
 */

public class Content implements Parcelable {

    private String title;
    private String description;
    private String url;

    public Content(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Content (Parcel parcel){
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.url = parcel.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
    }


    public static final Parcelable.Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel parcel) {
            return new Content(parcel);
        }

        @Override
        public Content[] newArray(int i) {
            return new Content[i];
        }
    };
}

