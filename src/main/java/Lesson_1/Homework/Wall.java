package Lesson_1.Homework;

public class Wall implements BarrierAction {
    private final int height;

    public Wall (int height) {
        this.height = height;
    }

    @Override
    public boolean action (Actionable examinee) {
        if (examinee.getJumpHeight() >= height) {
            System.out.println("Успешно перепрыгнул");
            return true;
        }

        System.out.println("Не смог перепрыгнуть");
        return false;
    }
}
