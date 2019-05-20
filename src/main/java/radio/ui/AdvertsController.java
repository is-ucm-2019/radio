package radio.ui;

import radio.transfer.AdTransfer;
import radio.transfer.AdvertiserTransfer;
import radio.util.Procedure;

import java.util.function.Consumer;

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

    void newAdFor(AdvertiserTransfer parent, String adName, String category, String description, Procedure success, Consumer<String> failure) {
        AdTransfer tr = new AdTransfer(parent, adName, category, description);
        if (this.controller.core.adExists(tr)) {
            failure.accept("An ad with this name already exists. Please pick another name");
            return;
        }

        success.accept();
        this.controller.core.addAdvert(tr);
    }
}
