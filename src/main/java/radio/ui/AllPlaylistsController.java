package radio.ui;

public class AllPlaylistsController implements ContentListController {

    private MainController controller;

    AllPlaylistsController(MainController controller) {
        this.controller = controller;
    }

    @Override
    public String getWindowTitle() {
        return "PLAYLISTS";
    }

    @Override
    public void searchEvent(String query) {
        // TODO(borja) Implement search
    }

    @Override
    public void createNewEvent() {
        // TODO(borja) Show appropriate jframe popup
    }

    @Override
    public void loadAllContent() {
        // TODO(borja): Tell controller to load all content for view
    }
}
