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
    }; // Essa matriz possui todas as possíveis combinações de células que dão ao usuário a vitória

    public Player(char playerSymbol) {
        this.playerSymbol = playerSymbol;
        // Vamos guardar o "símbolo" do player
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public boolean winCondition() {
        for(Object[] array : wins) {
            if(Arrays.asList(cells).containsAll(Arrays.asList(array))) {
                // Estamos apenas verificando se as células que o usuário guardou correspondem ao necessário para uma vitória
                return true;
            }
        }
        return false;
    }

    public int setCellValue(Object receivedValue, Object p1Symbol, Object p2Symbol) {
        // Caso o valor recebido não seja o símbolo de um usuário, guardamos o valor recebido
        if(!receivedValue.equals(p1Symbol) && !receivedValue.equals(p2Symbol)) {
            cells[cont] = receivedValue;
            cont ++;
            return 1;
            // Caso tudo ocorra como o esperado, iremos retornar 1 para somar à variável "round" e passar a rodada
        }
        return 0;
    }
}
