import java.util.Random;
public class CollectionNumbers {
    Random random = new Random();
    private int[][] numbers= {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    private final int moves[][] = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    private int emptyRow = 3;
    private int emptyCol = 3;

    public int getEmptyRow(){
        return emptyRow;
    }
    public int getEmptyCol(){
        return emptyCol;
    }
    public int[][] getNumbers(){
        return numbers;
    }
    public void ShuffleNumbers(){
        for(int i = 0; i<1000; i++){

            int move[] = moves[random.nextInt(4)];
            int newRow = emptyRow + move[0];
            int newCol = emptyCol + move[1];
            if(newRow >= 0 & newRow < 4 & newCol >= 0 & newCol < 4){
                numbers[emptyRow][emptyCol] = numbers[newRow][newCol];
                numbers[newRow][newCol] = 0;

                emptyRow = newRow;
                emptyCol= newCol;
            }
        }
    }

    public void MoveEmptyUp(){
        if(emptyRow > 0){
            numbers[emptyRow][emptyCol] = numbers[emptyRow-1][emptyCol];
            numbers[emptyRow-1][emptyCol] = 0;
            emptyRow--;
        }
    }
    public void MoveEmptyDown(){
        if(emptyRow < 3){
            numbers[emptyRow][emptyCol] = numbers[emptyRow+1][emptyCol];
            numbers[emptyRow+1][emptyCol] = 0;
            emptyRow++;
        }
    }
    public void MoveEmptyRight(){
        if(emptyCol < 3){
            numbers[emptyRow][emptyCol] = numbers[emptyRow][emptyCol+1];
            numbers[emptyRow][emptyCol+1] = 0;
            emptyCol++;
        }
    }
    public void MoveEmptyLeft(){
        if(emptyCol > 0){
            numbers[emptyRow][emptyCol] = numbers[emptyRow][emptyCol-1];
            numbers[emptyRow][emptyCol-1] = 0;
            emptyCol--;
        }
    }
}
