package radio.actions;

import radio.transfer.AdvertiserTransfer;

import java.util.List;

public class ShowAdvertisers {
    public final List<AdvertiserTransfer> list;

    public ShowAdvertisers(List<AdvertiserTransfer> list) {
        this.list = list;
    }
}
