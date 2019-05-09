package radio.actions;

import radio.transfer.ThemeTransfer;

import java.util.List;

public class ShowThemeList {
    public final List<ThemeTransfer> tr;

    public ShowThemeList(List<ThemeTransfer> tr) {
        this.tr = tr;
    }
}
