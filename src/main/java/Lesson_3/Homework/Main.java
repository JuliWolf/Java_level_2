package Lesson_3.Homework;

import java.util.*;

public class Main {
    final static int LIST_SIZE = 20;
    final static String[] AVAILABLE_CHARACTERS = { "a", "b", "c", "d", "e" };

    public static void main(String[] args) {
        List<String> stringList = generateList(LIST_SIZE);
        System.out.println(stringList);
        Map<String, Integer> charDict = countCharacters(stringList);
        System.out.println(charDict);

        TelephoneDictionaryImp telephoneDict = new TelephoneDictionaryImp();
        telephoneDict.add("Иванов", "+7 965-67-90");
        telephoneDict.add("Смирнов", "+7 940-80-96");
        telephoneDict.add("Федоров", "+7 876-37-34");
        telephoneDict.add("Иванов", "+7 489-23-65");
        telephoneDict.add("Федоров", "+7 345-56-56");

        System.out.println(telephoneDict.get("Иванов"));
        System.out.println(telephoneDict.get("Смирнов"));
        System.out.println(telephoneDict.get("Федоров"));
        telephoneDict.display();
    }

    public static List<String> generateList (int size) {
        Random random = new Random();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(AVAILABLE_CHARACTERS.length);
            stringList.add(AVAILABLE_CHARACTERS[index]);
        }

        return stringList;
    }

    public static Map<String, Integer> countCharacters (List<String> stringList) {
        Map<String, Integer> stringDict = new HashMap<String, Integer>();

        stringList.forEach((String character) -> {
            Integer value = 1;

            if (stringDict.containsKey(character)) {
                value = stringDict.get(character);
                value++;
            }

            stringDict.put(character, value);
        });

        return stringDict;
    }
}
