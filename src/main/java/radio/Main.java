package radio;

import org.apache.commons.cli.*;
import radio.core.Core;
import radio.core.Database;
import radio.ui.MainController;
import radio.ui.MainWindow;
import radio.util.PersistenceException;

import javax.swing.*;

public class Main implements Runnable {
    private static final String DEFAULT_DB_PATH = "/tmp/db.ser";
    private String dbPath;

    private Main(String path) {
        this.dbPath = path;
    }

    @Override
    public void run() {
        Core core = new Core(this.dbPath);
        MainController controller = new MainController(core);
        MainWindow window = new MainWindow(controller);
        controller.addView(window);
        window.enable();
    }

    public static void main(String[] args) {
        Config config = parseArgs(args);
        if (config == null) {
            System.exit(1);
        }

        String path = config.getDbPath();
        switch (config.getMode()) {
            case RUN:
                SwingUtilities.invokeLater(new Main(path));
                break;
            case GENERATE:
                generateCustomData(path);
                break;
        }
    }

    private static Config parseArgs(String[] args) {
        CommandLine line;
        Config config = new Config();
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("r", "run", false, "Run in standard mode");
        options.addOption("g", "generate", false, "Run in generate mode");
        options.addOption("f", "file", true, "Path to the databse file");

        try {
            line = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(String.format("Wrong usage: %s", e.getMessage()));
            return null;
        }

        boolean modeOption = line.hasOption('r') || line.hasOption('g');
        if (!modeOption) {
            System.err.println("Wrong usage: Please specify a mode");
            return null;
        }

        if (line.hasOption('r')) {
            config.setMode(Config.Mode.RUN);
        }

        if (line.hasOption('g')) {
            config.setMode(Config.Mode.GENERATE);
        }

        if (!line.hasOption('f')) {
            System.out.println(String.format("No db file selected, will use %s", DEFAULT_DB_PATH));
            config.setDbPath(DEFAULT_DB_PATH);
        } else {
            config.setDbPath(line.getOptionValue('f'));
        }

        return config;
    }

    private static void generateCustomData(String databasePath) {
        System.out.println(String.format("Generating random data for %s", databasePath));
        Database db = new Database();
        DataGenerator.fillData(db);
        try {
            Database.toDisk(db, databasePath);
        } catch (PersistenceException e) {
            System.err.println(String.format("Couldn't save to %s", databasePath));
        }
    }
}
