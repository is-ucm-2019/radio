package radio.util;

public class SongDuration {
    private int minutes;
    private int seconds;

    public SongDuration(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException();
        }

        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String toString() {
        return String.format("%2s:%2s", minutes, seconds).replace(' ', '0');
    }
}
