package Lesson_5.Homework;

import java.util.Arrays;

public class Main {
    private static final int ARRAY_SIZE = 100_000_000;

    public static void main(String[] args) {
        testFillArrayInOneThread();
        testFillArrayInTwoThreads();
    }

    public static void testFillArrayInOneThread () {
        float[] arr = new float[ARRAY_SIZE];
        FillArray fill = new FillArray() {
            @Override
            public float[] fillArray(float[] arr) {
                return FillArray.super.fillArray(arr);
            }
        };
//      start time
        long a = System.currentTimeMillis();

        fill.fillArray(arr);

//      end time
        System.out.println("One thread time: " + (System.currentTimeMillis() - a));
//        System.out.println(Arrays.toString(arr));
    }

    public static void testFillArrayInTwoThreads () {
        int arrayLength = ARRAY_SIZE / 2;
        float[] arr = new float[ARRAY_SIZE];
        float[] firstArr = new float[arrayLength];
        float[] secondArr = new float[arrayLength];

//      start time
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, firstArr, 0, arrayLength);
        System.arraycopy(arr, arrayLength, secondArr, 0, arrayLength);

        ArrayRunnable firstRunnable = new ArrayRunnable(firstArr);
        ArrayRunnable secondRunnable = new ArrayRunnable(secondArr);
        Thread firstThread = new Thread(firstRunnable);
        Thread secondThread = new Thread(secondRunnable);

        try {
            firstThread.start();
            secondThread.start();

            firstThread.join();
            secondThread.join();

            System.arraycopy(firstRunnable.getArray(), 0, arr, 0, arrayLength);
            System.arraycopy(secondRunnable.getArray(), 0, arr, arrayLength, arrayLength);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//      end time
        System.out.println("Two threads time: " + (System.currentTimeMillis() - a));
//        System.out.println(Arrays.toString(arr));
    }
}


