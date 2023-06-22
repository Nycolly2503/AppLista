package mendes.nascimento.nycolly.applista.activity.Model;

import android.net.Uri;

public class NewItemActivityViewModel {
    Uri selectPhotoLocation = null;

    public Uri getSelectPhotoLocation(){
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
