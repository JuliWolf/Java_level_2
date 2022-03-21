package Lesson_1.Homework;

public class RaceTrack implements BarrierAction {
    private final int length;

    public RaceTrack (int length) {
        this.length = length;
    }

    @Override
    public boolean action (Actionable examinee) {
        if (examinee.getRunDistance() >= length) {
            System.out.println("Успешно пробежал");
            return true;
        }

        System.out.println("Не усмог пробежать");
        return false;
    }
}
