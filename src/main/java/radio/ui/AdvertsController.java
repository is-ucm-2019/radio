package radio.ui;

import radio.transfer.AdvertiserTransfer;

public class AdvertsController {
    private MainController controller;

    public AdvertsController(MainController cont) {
        this.controller = cont;
    }

    public void getAllAdvertisers() {
        controller.core.allAdvertisers();
    }

    public void adsFor(AdvertiserTransfer tr) {
        controller.core.getAdvertiserAds(tr);
    }
}
