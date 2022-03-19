package Lesson_1.Homework;

public class App {
    public static void main(String[] args) {
        Actionable[] examinees = new Actionable[4];
        examinees[0] = new Cat(500, 300);
        examinees[1] = new Robot(100, 1000);
        examinees[2] = new Human(200, 200);
        examinees[3] = new Cat(600, 400);

        BarrierAction[] barriers = new BarrierAction[2];
        barriers[0] = new Wall(300);
        barriers[1] = new RaceTrack(100);

        for (int i = 0; i < examinees.length; i++) {
            boolean isSucessfull = true;
            int j = 0;

            System.out.println("Участник №" + (i+1));

            while(isSucessfull && barriers.length > j) {
                isSucessfull = barriers[j].action(examinees[i]);

                j++;
            }
        }
    }
}
