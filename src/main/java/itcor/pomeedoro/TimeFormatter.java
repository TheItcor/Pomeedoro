package itcor.pomeedoro;

/**
 * Formatter for time.
 */
public class TimeFormatter {
    /**
     * Interprets seconds to format MM:SS.
     * Example: 120 -> 02:00, 67 -> 01:07
     * @param seconds - time
     * @return String "00:00"
     */
    public static String interpretSeconds(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60; // Получаем остаток
        return "%02d:%02d".formatted(minutes, remainingSeconds);
    }
}
