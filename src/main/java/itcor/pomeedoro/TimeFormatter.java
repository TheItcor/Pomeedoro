package itcor.pomeedoro;

public class TimeFormatter {
    public static String interpretSeconds(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60; // Получаем остаток
        return "%02d:%02d".formatted(minutes, remainingSeconds);
    }
}
