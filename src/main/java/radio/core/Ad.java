package radio.core;

import radio.transfer.AdTransfer;

public class Ad extends PersistentObject<String> {
    private String name;
    private String description;
    private String category;

    public Ad(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Ad(AdTransfer tr) {
        this.name = tr.name;
        this.description = tr.description;
        this.category = tr.category;
    }

    @Override
    String getKey() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
