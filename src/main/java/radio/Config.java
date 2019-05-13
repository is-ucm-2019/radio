package radio;

class Config {
    public enum Mode {
        GENERATE,
        RUN
    }

    private Mode mode;
    private String dbPath;

    Mode getMode() {
        return mode;
    }

    void setMode(Mode mode) {
        this.mode = mode;
    }

    String getDbPath() {
        return dbPath;
    }

    void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }
}
