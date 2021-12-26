import javax.swing.table.AbstractTableModel;

public class Board extends AbstractTableModel {
    private final Object[] tableColumns;
    private final Object[][] tableContent;
    // Vamos criar nosso próprio modelod e tabela para facilitar a alteração de certas células

    private final Object p1Symbol;
    private final Object p2Symbol;

    public Board(Object[] tableColumns, Object[][] tableContent, Object p1Symbol, Object p2Symbol) {
        this.tableColumns = tableColumns;
        this.tableContent = tableContent;
        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;
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
        Object cell = tableContent[row][column];
        return !cell.equals(p1Symbol) && !cell.equals(p2Symbol);
        // A célula só deve ser editável caso ela não corresponda aos símbolos de um dos players
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if(this.isCellEditable(row, column)) {
            this.tableContent[row][column] = value;
            fireTableCellUpdated(row, column);
            // Caso a célula seja editável, podemos alterar o valor dela
        }
    }
}
