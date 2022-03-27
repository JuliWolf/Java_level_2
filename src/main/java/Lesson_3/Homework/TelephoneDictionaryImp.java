package Lesson_3.Homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelephoneDictionaryImp implements TelephoneDictionary {
    private final Map<String, List<String>> dictionary = new HashMap();

    @Override
    public void add(String surname, String number) {
        List<String> list;

        if (contains(surname)) {
            list = get(surname);
        } else {
            list = new ArrayList<>();
        }

        list.add(number);
        dictionary.put(surname, list);
    }

    @Override
    public List<String> get(String surname) {
        return dictionary.get(surname);
    }

    @Override
    public boolean contains(String surname) {
        return dictionary.containsKey(surname);
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        dictionary.forEach((key, value) -> {
            str.append(key).append("= \n");

            value.forEach((String val) -> {
                str.append("\t").append(val).append("\n");
            });
        });

        return str.toString();
    }
}
