import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class GameWindow {
    JFrame window = new JFrame();
    // Criando o JFrame

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    // As variáveis acima serão utilizadas para definir o tamanho de certos elementos da tela

    Object[] tableColumns = {"", "", ""};
    Object[][] tableContent = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
    Board board;
    // Os conjuntos acima irão ser utilizados para criar a tabela

    Player player1; // Variável que guarda os atributos do jogador 1
    Player player2; // Variável que guarda os atributos do jogador 2
    static int round = 0; // Essa variável será utilizada para determinar qual jogador está na vez

    public GameWindow(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(tableColumns, tableContent, player1.getPlayerSymbol(), player2.getPlayerSymbol());
    }

    protected void start() {
        window.setTitle("Jogo da velha");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.DARK_GRAY);

        window.setLayout(new BorderLayout());
        window.add(northPanel(), BorderLayout.NORTH);
        window.add(centerPanel(player1, player2), BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        // Esse método dá início ao jogo
    }

    private JPanel northPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.DARK_GRAY);
        northPanel.setPreferredSize(new Dimension(screenWidth, screenHeight / 8));
        northPanel.add(northLabel());
        return northPanel;
        // Crinaod o painel norte
    }

    private JPanel centerPanel(Player player1, Player player2) {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.DARK_GRAY);
        northPanel.setPreferredSize(new Dimension(screenWidth, screenHeight / 4));
        northPanel.add(centerTable(player1, player2));
        return northPanel;
        // Criando o painel central, onde ficará a tabela
    }

    private JLabel northLabel() {
        JLabel northLabel = new JLabel();
        northLabel.setText("JOGO DA VELHA");
        northLabel.setFont(new Font("Arial", Font.BOLD, 50));
        northLabel.setBackground(Color.BLACK);
        northLabel.setForeground(Color.WHITE);
        return northLabel;
        // Criando uma label com o título do jogo
    }

    private void tableRenderer(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Alinhando o conteúdo das células no meio

        table.setRowHeight(150); // Definingo o height das células
        for(int i = 0; i < table.getColumnCount(); i ++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(150); // Definingo o width das células
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private JTable centerTable(Player player1, Player player2) {
        JTable myTable = new JTable(board);
        myTable.setRowSelectionAllowed(false); // Não podemos deixar que toda uma linha seja selecionada com um click
        myTable.setFont(new Font("Arial", Font.BOLD, 90));
        tableRenderer(myTable);
        // Finalizamos o desenvolvimento da aparência da tabela

        myTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(round % 2 == 0) {
                    round += player1.setCellValue(myTable.getValueAt(myTable.getSelectedRow(), myTable.getSelectedColumn()), player1.getPlayerSymbol(), player2.getPlayerSymbol());
                    // A vez do jogador só irá passar caso ele escolha uma casa válida
                    myTable.setValueAt(player1.getPlayerSymbol(), myTable.getSelectedRow(), myTable.getSelectedColumn());
                    // Adicionando o símbolo do jogador à casa escolhida
                    myTable.setSelectionForeground(Color.RED);
                } else {
                    round += player2.setCellValue(myTable.getValueAt(myTable.getSelectedRow(), myTable.getSelectedColumn()), player1.getPlayerSymbol(), player2.getPlayerSymbol());
                    myTable.setValueAt(player2.getPlayerSymbol(), myTable.getSelectedRow(), myTable.getSelectedColumn());
                    myTable.setSelectionForeground(Color.GREEN);
                }

                String[] options = {"Jogar novamente", "Sair"}; // Opções após o fim da partida
                int choice = 5;
                if(player1.winCondition()) {
                    choice = JOptionPane.showOptionDialog(null, "PLAYER 1 VENCEU!", "Fim de jogo!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    // Retorna 0
                } else if(player2.winCondition()) {
                    choice = JOptionPane.showOptionDialog(null, "PLAYER 2 VENCEU!", "Fim de jogo!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    // Retorna 1
                }
                if(choice == 0) {
//                    round = 0; // Podemos reiniciar o round ou não
                    // Optei por não reiniciar o round para que o player 2 seja o primeiro a jogar dessa vez
                    board.resetTable(tableContent); // Reiniciamos a table
                    player1.resetPlayer();
                    player2.resetPlayer();
                    // Reiniciamos os jogadores
                    GameWindow gm = new GameWindow(player1, player2);
                    gm.start(); // Criamos um novo jogo
                    window.setVisible(false); // Escondemos o primeiro jogo

                    // Ainda é um problema que os Frames já utilizados não sejam fechados. Resolverei isso no futuro.
                } else if(choice == 1) {
                    window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
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
