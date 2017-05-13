import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {

        for (ShellSort.IntervalType type : ShellSort.IntervalType.values())
            System.out.println(Arrays.toString(type.generateIntervals(100)));

    }

}
