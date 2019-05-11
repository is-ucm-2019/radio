package radio.actions;

import radio.transfer.PlaylistTransfer;

import java.util.List;

public class ShowPlaylists {
    public final List<PlaylistTransfer> list;

    public ShowPlaylists(List<PlaylistTransfer> list) {
        this.list = list;
    }
}
