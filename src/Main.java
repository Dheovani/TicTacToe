public class Main {
    public static void main(String[] args) {
        Player player1 = new Player('X');
        Player player2 = new Player('O');
        GameWindow gm = new GameWindow(player1, player2);
        gm.start();
    }
}
