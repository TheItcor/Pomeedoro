package itcor.pomeedoro;

public class PomeedoroTimer {
    private int seconds = 10;
    private boolean statusIsWork = true;
    private int switchCount = 0;

    private final int WORK_TIME_SECONDS = 10;
    private final int RELAX_TIME_SECONDS = 5;

    public PomeedoroTimer() {
    }

    public void switchStatus() {
        statusIsWork = !statusIsWork;
    }

    public void nextStatus() {
        switchStatus();
        seconds = statusIsWork ? WORK_TIME_SECONDS : RELAX_TIME_SECONDS;
        switchCount++;
    }

    public void tick() {
        if (seconds > 0) seconds--;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isStatusIsWork() {
        return statusIsWork;
    }

    public int getSwitchCount() {
        return switchCount;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getStatusWork() {
        return statusIsWork ? "Work" : "Relax";
    }
}
