package radio.ui;

import radio.transfer.PlaylistTransfer;

class PlaylistController {
    private MainController controller;

    PlaylistController(MainController cont) {
        this.controller = cont;
    }

    void getAllPlaylists() {
        controller.core.allPlaylists();
    }

    // TODO(borja): Check if it's being used in any broadcast
    void deletePlaylist(PlaylistTransfer tr) {
        controller.core.removePlaylist(tr);
    }
}
