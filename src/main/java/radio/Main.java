package radio;

import radio.core.Core;
import radio.ui.MainController;
import radio.ui.MainWindow;

import javax.swing.*;

public class Main implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }

    @Override
    public void run() {
        Core core = new Core();
        MainController controller = new MainController(core);
        MainWindow window = new MainWindow(controller);
        controller.addView(window);
        window.enable();
    }
}
