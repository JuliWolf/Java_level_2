import Lesson_2.Homework.MyArrayDataException;
import Lesson_2.Homework.MyArraySizeException;

public class Main {
    private final static int REQUIRED_SIZE = 4;

    public static void main(String[] args) {
        try {
            String[][] grid =  generateGrid(4);
            int sum = sumGridValues(grid, REQUIRED_SIZE);

            System.out.println("Сумма элементов массива = " + sum);
        } catch (MyArraySizeException | MyArrayDataException error) {
            System.out.println(error);
        }

    }

    public static String[][] generateGrid (int size) {
        String[][] grid = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Integer.toString(i);
            }
        }

        grid[2][3] = "J";

        return grid;
    }

    public static int sumGridValues (String[][] grid, int requiredSize) throws MyArraySizeException, MyArrayDataException {
        if (grid.length != requiredSize) {
            throw new MyArraySizeException(requiredSize);
        }

        int sum = 0;
        int xPos = 0;
        int yPos = 0;

        try {
            for (int i = 0; i < grid.length; i++) {
                xPos = i;

                if (grid[i].length != requiredSize) {
                    throw new MyArraySizeException(requiredSize);
                }

                for (int j = 0; j < grid[i].length; j++) {
                    yPos = j;

                    int value = Integer.parseInt(grid[i][j]);
                    sum += value;
                }
            }

            return sum;
        } catch (NumberFormatException error) {
            throw new MyArrayDataException(xPos, yPos, error);
        }

    }
}
