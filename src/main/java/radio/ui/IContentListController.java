package radio.ui;

public interface IContentListController {
    String getWindowTitle();

    void searchEvent(String query);

    void createNewEvent();

    void loadAllContent();
}
