package radio.ui;

import radio.transfer.BroadcastTransfer;
import radio.transfer.ThemeTransfer;
import radio.util.Procedure;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

class ThemesController {

    private MainController parentController;

    ThemesController(MainController cont) {
        this.parentController = cont;
    }

    void getAllThemes() {
        this.parentController.core.allThemes();
    }

    void validateTheme(String name, String description, LocalDate start, LocalDate end, Procedure success, Consumer<String> failure) {
        ThemeTransfer tr = new ThemeTransfer(name, description, start, end);
        if (this.parentController.core.themeExists(tr)) {
            failure.accept("Theme already exists! Pick another name");
            return;
        }

        success.accept();
        this.parentController.core.validateTheme(tr);
    }

    void confirmTheme(ThemeTransfer tr, List<BroadcastTransfer> chosen) {
        this.parentController.core.confirmTheme(tr, chosen);
    }

    void getBroadcasts(ThemeTransfer tr) {
        this.parentController.core.getThemeBroadcasts(tr);
    }
}
