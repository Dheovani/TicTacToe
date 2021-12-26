import javax.swing.table.AbstractTableModel;

public class Board extends AbstractTableModel {
    private final Object[] tableColumns;
    private final Object[][] tableContent;
    // Vamos criar nosso próprio modelod e tabela para facilitar a alteração de certas células

    public Board(Object[] tableColumns, Object[][] tableContent) {
        this.tableColumns = tableColumns;
        this.tableContent = tableContent;
    }

    @Override
    public int getRowCount() {
        return tableContent.length;
    }

    @Override
    public int getColumnCount() {
        return tableColumns.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return tableContent[i][i1];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        this.tableContent[row][column] = value;
        fireTableCellUpdated(row, column);
    }
}
