import javax.swing.*;
import java.awt.*;

public class DrawField extends JPanel{
    CollectionNumbers collect = new CollectionNumbers();
    private int[][] num = collect.getNumbers();;
    private final int PosNumX = 30;
    private final int PosNumY = 90;
    public void updateNumbers(){
        collect.ShuffleNumbers();
        num = collect.getNumbers();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Font customFont = new Font("Times New Roman", Font.BOLD, 40);
        g2d.setFont(customFont);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(PosNumX+70*4-30, PosNumY-35, 5, 75*4-20);
        g2d.fillRect(PosNumX-25, PosNumY+70*4-35, 75*4-19, 5);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                g2d.drawRect((j*70)+PosNumX-20, (i*70)+PosNumY-30, 65, 65);
                g2d.setColor(Color.BLUE);
                g2d.fillRect((j*70)+PosNumX-25, (i*70)+PosNumY-35, 5, 70);
                g2d.fillRect((j*70)+PosNumX-25, (i*70)+PosNumY-35, 71, 5);
                g2d.setColor(Color.black);
                if(num[i][j]!= 0) {
                    g2d.drawString(String.valueOf(num[i][j]), (j*70)+PosNumX, (i*70)+PosNumY);
                }
            }
        }


    }
}
