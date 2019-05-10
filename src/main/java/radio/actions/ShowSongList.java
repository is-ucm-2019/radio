package radio.actions;

import radio.transfer.SongTransfer;

import java.util.List;

public class ShowSongList {
    public final List<SongTransfer> list;

    public ShowSongList(List<SongTransfer> l) {
        this.list = l;
    }
}
