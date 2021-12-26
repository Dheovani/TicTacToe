import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    Object[] tableColumns = {"", "", ""};
    Object[][] tableContent = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
    Board board = new Board(tableColumns, tableContent);
    static int round = 0;

    protected void Start(Player player1, Player player2) {
        JFrame window = new JFrame();
        window.setTitle("Jogo da velha");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.DARK_GRAY);

        window.setLayout(new BorderLayout());
        window.add(northPanel(), BorderLayout.NORTH);
        window.add(centerPanel(player1, player2), BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    private JPanel northPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.DARK_GRAY);
        northPanel.setPreferredSize(new Dimension(screenWidth, screenHeight / 8));
        northPanel.add(northLabel());
        return northPanel;
    }

    private JPanel centerPanel(Player player1, Player player2) {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.DARK_GRAY);
        northPanel.setPreferredSize(new Dimension(screenWidth, screenHeight / 4));
        northPanel.add(centerTable(player1, player2));
        return northPanel;
    }

    private JLabel northLabel() {
        JLabel northLabel = new JLabel();
        northLabel.setText("JOGO DA VELHA");
        northLabel.setFont(new Font("Arial", Font.BOLD, 50));
        northLabel.setBackground(Color.BLACK);
        northLabel.setForeground(Color.WHITE);
        return northLabel;
    }

    private void tableRenderer(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(150);
        for(int i = 0; i < table.getColumnCount(); i ++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(150);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private JTable centerTable(Player player1, Player player2) {
        JTable myTable = new JTable(board);
        myTable.setRowSelectionAllowed(false);
        myTable.setCellSelectionEnabled(true);
        myTable.setFont(new Font("Arial", Font.BOLD, 90));
        tableRenderer(myTable);
        myTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                boolean p1Win = false;
                boolean p2Win = false;
                if(round % 2 == 0) {
                    p1Win = player1.setCellValue(myTable.getValueAt(myTable.getSelectedRow(), myTable.getSelectedColumn()));
                    myTable.setValueAt(player1.getPlayerSymbol(), myTable.getSelectedRow(), myTable.getSelectedColumn());
                } else {
                    p2Win = player2.setCellValue(myTable.getValueAt(myTable.getSelectedRow(), myTable.getSelectedColumn()));
                    myTable.setValueAt(player2.getPlayerSymbol(), myTable.getSelectedRow(), myTable.getSelectedColumn());
                }
                round ++;
                if(p1Win) {
                    JOptionPane.showMessageDialog(null, "PLAYER 1 VENCEU!");
                } else if(p2Win) {
                    JOptionPane.showMessageDialog(null, "PLAYER 2 VENCEU!");
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                myTable.setSelectionBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                myTable.setSelectionBackground(Color.WHITE);
            }
        });
        return myTable;
    }
}
