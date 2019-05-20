package radio.transfer;

import radio.core.Ad;

public class AdTransfer {
    public final AdvertiserTransfer parent;
    public final String name;
    public final String description;
    public String category;

    public AdTransfer(AdvertiserTransfer parent, String name, String category, String description) {
        this.parent = parent;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public AdTransfer(AdvertiserTransfer parent, Ad a) {
        this.parent = parent;
        this.name = a.getName();
        this.description = a.getDescription();
        this.category = a.getCategory();
    }

    @Override
    public String toString() {
        return String.format("%s — %s (%s)", parent.name, name, category);
    }
}
