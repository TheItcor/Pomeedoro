package itcor.pomeedoro;

/**
 * Logics for Pomodoro timer with Relax-Work time periods.
 */
public class PomeedoroTimer {
    private int seconds = 30 * 60;
    private boolean statusIsWork = true;
    private int switchCount = 0;

    private final int WORK_TIME_SECONDS = 30 * 60;
    private final int RELAX_TIME_SECONDS = 5 * 60;

    public PomeedoroTimer() {}

    /**
     * Turns status RELAX into WORK ; WORK into RELAX
     */
    public void switchStatus() {
        statusIsWork = !statusIsWork;
    }

    /**
     * Switches to the next status and resets the timer.
     * Work duration: {@link #RELAX_TIME_SECONDS} seconds.
     * Relax duration: {@link #WORK_TIME_SECONDS} seconds.
     */
    public void nextStatus() {
        switchStatus();
        seconds = statusIsWork ? WORK_TIME_SECONDS : RELAX_TIME_SECONDS;
        switchCount++;
    }

    public void tick() {
        if (seconds > 0) seconds--;
    }

    /**
     * Seconds getter
     * @return {@link #seconds}
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Checks if the countdown has finished.
     * @return true if no seconds remain, false otherwise
     */
    public boolean isFinished() {
        return seconds <= 0;
    }

    /**
     * Returns String "Work" or "Relax" according to the {@link #statusIsWork}.
     * @return "Work" / "Relax"
     */
    public String getStatusWork() {
        return statusIsWork ? "Work" : "Relax";
    }

    /**
     * Adds 60 * 5 to {@link #seconds} (5 minutes more)
     */
    public void addFiveMinutes() {
        seconds += 60 * 5;
    }
}
