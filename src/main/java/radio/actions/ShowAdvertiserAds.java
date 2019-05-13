package radio.actions;

import radio.transfer.AdTransfer;

import java.util.List;

public class ShowAdvertiserAds {
    public final List<AdTransfer> list;

    public ShowAdvertiserAds(List<AdTransfer> list) {
        this.list = list;
    }
}
