package radio.transfer;

import radio.core.Advertiser;

public class AdvertiserTransfer {
    public final String name;
    public final String phone;
    public final String email;
    public final String description;

    public AdvertiserTransfer(String name, String phone, String email, String description) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    public AdvertiserTransfer(Advertiser a) {
        this.name = a.getName();
        this.phone = a.getPhone();
        this.email = a.getEmail();
        this.description = a.getDescription();
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
