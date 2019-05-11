package radio.ui;

interface IContentListController {
    String getWindowTitle();

    void searchEvent(String query);

    void createNewEvent();

    void loadAllContent();
}
