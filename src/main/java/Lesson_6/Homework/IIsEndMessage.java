package Lesson_6.Homework;

public interface IIsEndMessage {
    default boolean isEndMessage (String message) {
        return "/end".equalsIgnoreCase(message);
    }
}
