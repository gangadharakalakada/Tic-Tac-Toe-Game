import java.util.Random;
import java.util.Scanner;

class tictactoe {
    static char[][] board;

     tictactoe() {
        board = new char[3][3];
        initboard(); // Initialize the board when the object is created
    }
    static boolean checkdraw(){
         for(int i=0;i<=2;i++){
             for(int j=0;j<=2;j++){
                 if(board[i][j]==' '){
                     return false;
                 }
             }
         }
         return true;
    }

    static void Placemark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("invalid poisition");
        }
    }

    static void initboard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void display() {
        System.out.println(" ------------- ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println(" ------------- ");
        }
    }

    static boolean colwise() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;

            }
        }
        return false;
    }
    static boolean rowise(){
        for(int i=0;i<=2;i++){
            if(board[i][0] !=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]){
                return true;
            }
        }
        return false;
    }
     static boolean diagwin(){
        if(board[0][0] != ' ' && board[0][0]==board[1][1]&&board[1][1]==board[2][2]||board[0][2] !=' ' &&board[0][2]==board[2][2]&&board[2][2]==board[2][0]){
            return true;
        }
        return false;
    }
}
abstract class player{
    String name;
    char mark;
    abstract void makemove();
    boolean validmove(int row,int col){
        if(col>=0 && col<=2 && row>=0 && row<=2){
            if(tictactoe.board[row][col] == ' '){
                return true;
            }

        }
        return false;

    }

}
class humanplayer extends player{

    humanplayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makemove(){
        int row;
        int col;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Enter row and col");
            row = scan.nextInt();
            col = scan.nextInt();
        }
        while(!validmove(row,col));
        tictactoe.Placemark(row,col,mark);
    }
}

class aiplayer extends player{

    aiplayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makemove(){
        int row;
        int col;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Enter row and col with ai");
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        }
        while(!validmove(row,col));
        tictactoe.Placemark(row,col,mark);
    }

}


public class Lanchgame {
    public static void main(String[] args) {
        tictactoe g = new tictactoe();
        humanplayer p1 = new humanplayer("Gangu", 'X');
        aiplayer p2 = new aiplayer("Hema", 'O');
        player cp;
        cp = p1;
        while (true) {
            System.out.println(cp.name + " turn");
            cp.makemove();
            tictactoe.display();
            if (tictactoe.colwise() ||
                    tictactoe.rowise() ||
                    tictactoe.diagwin()) {
                System.out.println(cp.name + " p1 won the game");
                break;
            }
            else if(tictactoe.checkdraw()){
                System.out.println("Game over");
                break;
            }
            else{
                    if (cp == p1) {
                        cp = p2;
                    } else {
                        cp = p1;
                    }
            }

        }
    }
}