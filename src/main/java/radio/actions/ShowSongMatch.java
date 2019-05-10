package radio.actions;

import radio.transfer.SongTransfer;

import java.util.List;

public class ShowSongMatch {
    public final List<SongTransfer> list;

    public ShowSongMatch(List<SongTransfer> list) {
        this.list = list;
    }
}
