public class Main {
    public static void main(String[] args) {
        GameWindow gm = new GameWindow();
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        gm.Start(player1, player2);
    }
}
