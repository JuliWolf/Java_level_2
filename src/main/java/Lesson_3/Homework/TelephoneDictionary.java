package Lesson_3.Homework;

import java.util.List;

public interface TelephoneDictionary {
    void add(String number, String surname);

    List<String> get(String surname);

    boolean contains(String surname);

    int size();

    boolean isEmpty();

    void display();
}
