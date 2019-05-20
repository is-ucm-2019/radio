package radio.actions;

import radio.transfer.AdTransfer;

import java.util.List;

public class ShowAdvertiserAds {
    public final String advertiserName;
    public final List<AdTransfer> list;

    public ShowAdvertiserAds(String advertiserName, List<AdTransfer> list) {
        this.advertiserName = advertiserName;
        this.list = list;
    }
}
