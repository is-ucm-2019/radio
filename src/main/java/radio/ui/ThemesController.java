package radio.ui;

import radio.transfer.BroadcastTransfer;
import radio.transfer.ThemeTransfer;

import java.time.LocalDate;
import java.util.List;

class ThemesController {

    private MainController parentController;

    ThemesController(MainController cont) {
        this.parentController = cont;
    }

    void getAllThemes() {
        this.parentController.core.allThemes();
    }

    void validateTheme(String name, String description, LocalDate start, LocalDate end) {
        System.out.println("Validating theme with name " + name);
        ThemeTransfer tr = new ThemeTransfer(name, description, start, end);
        this.parentController.core.validateTheme(tr);
    }

    void confirmTheme(ThemeTransfer tr, List<BroadcastTransfer> chosen) {
        this.parentController.core.confirmTheme(tr, chosen);
    }
}
