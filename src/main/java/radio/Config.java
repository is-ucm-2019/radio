package radio;

public class Config {
    public enum Mode {
        GENERATE,
        RUN
    }

    private Mode mode;
    private String dbPath;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }
}
