package Lesson_5.Homework;


public class ArrayRunnable implements Runnable, FillArray {
    private float[] array;

    public ArrayRunnable (float[] arr) {
        array = arr;
    }

    float[] getArray () {
        return array;
    }

    @Override
    public void run() {
        fillArray(array);
    }
}
