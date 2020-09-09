package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Row implements Serializable {

    private List<Integer> cells = new ArrayList<>();
    private Random random = new Random();

    public Row(int columnsCount) {
        for (int columnNr = 0; columnNr < columnsCount; columnNr++) {
           cells.add(random.nextInt(10));
        }
    }
/*
    public Row (Matrix matrix , int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            cells.add(matrix.getColumnAvg(columnNumber));
        }
    }
*/
    public int sum() {
        int sum = 0;
        for (Integer value : cells) {
            sum+= value;
        }
        return sum;
    }

    public List<Integer> getCells() {
        return cells;
    }
}

