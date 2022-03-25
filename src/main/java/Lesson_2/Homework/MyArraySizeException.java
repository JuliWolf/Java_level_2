package Lesson_2.Homework;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(int size) {
        super("Размер массива должен быть " + size);
    }
}
