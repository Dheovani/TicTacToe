import java.util.Arrays;

public class Player {
    private final char playerSymbol;
    private final Object[] cells = new Object[5];
    private int cont = 0;
    private final Object[][] wins = new Object[][]{
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"},
            {"1", "4", "7"},
            {"2", "5", "8"},
            {"3", "6", "9"},
            {"1", "5", "9"},
            {"3", "5", "7"}
    };

    public Player(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    private boolean winCondition() {
        for(Object[] array : wins) {
            if(Arrays.asList(cells).containsAll(Arrays.asList(array))) {
                return true;
            }
        }
        return false;
    }

    public boolean setCellValue(Object receivedValue) {
        cells[cont] = receivedValue;
        cont ++;
        return winCondition();
    }
}
