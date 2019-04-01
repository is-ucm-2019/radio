package radio.ui;

public interface ContentListController {
    String getWindowTitle();
    void searchEvent(String query);
    void createNewEvent();
    void loadAllContent();
}
