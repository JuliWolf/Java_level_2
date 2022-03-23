package Lesson_2.Homework;

public class MyArrayDataException extends Exception {
    public MyArrayDataException (int x, int y, Throwable cause) {
        super("Неверный формат данных в ячейке: x=" + x + " y=" + y, cause);
    }
}
