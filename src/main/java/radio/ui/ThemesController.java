package radio.ui;

import java.time.LocalDate;

class ThemesController {

    private MainController parentController;

    ThemesController(MainController cont) {
        this.parentController = cont;
    }

    void addThemeEvent(String name, String description, LocalDate start, LocalDate end) {
        System.out.println("Will create " +  name + "theme between " + start + " and " + end + "with description " + description);
    }
}
