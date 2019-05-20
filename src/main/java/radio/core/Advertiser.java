package radio.core;

import radio.transfer.AdvertiserTransfer;

import java.util.ArrayList;
import java.util.List;

public class Advertiser extends PersistentObject<String> {

    private String name;
    private String phone;
    private String email;
    private String description;
    private List<Ad> ads;

    public Advertiser(String name, String phone, String email, String description) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.ads = new ArrayList<>();
    }

    public Advertiser(AdvertiserTransfer tr) {
        this.name = tr.name;
        this.phone = tr.phone;
        this.description = tr.description;
        this.ads = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void addAdvert(Ad a) {
        this.ads.add(a);
    }

    public void removeAdvert(Ad a) {
        this.ads.remove(a);
    }

    @Override
    String getKey() {
        return name;
    }
}
