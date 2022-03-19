package Lesson_1.Homework;

public class Cat implements Actionable{
    private final int jumpHeight;
    private final int runDistance;

    public Cat (int jumpHeight, int runDistance) {
        this.jumpHeight = jumpHeight;
        this.runDistance = runDistance;
    }

    @Override
    public int getJumpHeight() {
        return jumpHeight;
    }

    @Override
    public int getRunDistance() {
        return runDistance;
    }


    @Override
    public void jump() {
        System.out.println("Cat jump");
    }

    @Override
    public void run() {
        System.out.println("Cat run");
    }


}
