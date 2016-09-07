package view;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopTableModel extends AbstractTableModel {

    private int columnCount = 6;
    private ArrayList<String []> dataArrayList;

    public ShopTableModel() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        dataArrayList = new ArrayList<String []>();
        for(int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String [] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "#Order";
            case 1: return "Date";
            case 2: return "CustomerName";
            case 3: return "CustomerSurname";
            case 4: return "Summ";
            case 5: return "Status";
        }
        return "";
    }

    public void addDate(String  []row) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String []rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);

    }
    public void dateNull() {
        dataArrayList.clear();
    }

}
