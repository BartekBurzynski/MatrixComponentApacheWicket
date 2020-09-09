package test;

import org.apache.wicket.model.IModel;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Matrix implements IModel {

    private List<Row> rows = new ArrayList<>();
 //   private Row avgResult = new Row(this,3);

    public Matrix(Integer rowsCount, Integer columnsCount) {
        create(rowsCount, columnsCount);
    }

    public void create(int rowsCount, int columnsCount) {
        rows.clear();
        for (int rowNr = 0; rowNr < rowsCount; rowNr++) {
            rows.add(new Row(columnsCount));
        }
    }

    public void setCell(int rowIndex, int columnIndex, int value){
        Row row = rows.get(rowIndex);
        row.getCells().set(columnIndex, value);
    }

    public List<Row> getRows() {
        return rows;
    }
    /*
      public void print(PrintStream out) {

          for (Row row : rows) {
              for (Integer cell : row.getCells()) {
                  out.print(cell + " ");
              }
              out.print(" |  " + row.sum());
          }

      }

      public int getColumnAvg2(int columnNumber) {
          int avg = 0;
          for (Row row : rows) {
              int curremtCell = 0;
              for (Integer cell : row.getCells()) {
                  if (curremtCell == columnNumber) {
                      avg = avg + cell;
                      continue;

                  }curremtCell++;
              }
          }
          return avg;
     }
   */
    public int getColumnAvg(int columnNumber) {
        if(rows.size() > 0 && columnNumber > -1) {
            int sum = 0;
            for (Row row : rows) {
                sum = sum + row.getCells().get(columnNumber);
            }
            return sum / rows.size();
        }
        return 0;
    }

    @Override
    public Object getObject() {
        return null;
    }
}

