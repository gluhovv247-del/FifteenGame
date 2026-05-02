import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class GameField extends JPanel implements ActionListener {
    private final int DOT_SIZE = 200;
    private final int ALL_DOTS = 1000;
    private final int FIFTEEN = 15;
    private Image dot;
    private Image square;
    private int dots;
    private Timer timer;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int squareX;
    private int squareY;
    private int[] arr = new int[FIFTEEN];
    ArrayList<Integer> numbers = new ArrayList<>();
    private boolean inGame = true;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    public GameField(){
        setBackground(Color.white);
        loadImages();
        InitGame();
        GenerateRandomNumbers();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }
    private void InitGame(){
        dots = 4;
        for(int i = 0; i<dots; i++){
            for(int j = 0; j<dots; j++){
                x[j] = 10 + j * DOT_SIZE;
                y[i] = 50+ i* DOT_SIZE;
            }
        }squareX = x[0];
        squareY = y[0];
    }
    public void GenerateRandomNumbers(){
        Random random = new Random();
        numbers.clear();
        int index = 0;
        for(int i =1; i<=FIFTEEN; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for(int i = 0; i<FIFTEEN; i++){
            arr[i] = numbers.get(i);
        }
    }
    public void loadImages(){
        ImageIcon iid = new ImageIcon("pictures/square.png");
        dot = iid.getImage();
        ImageIcon iis = new ImageIcon("pictures/square.png");
        square = iis.getImage();
        timer = new Timer(250, this);
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Font customFont = new Font("Times New Roman", Font.BOLD, 40);
        g2d.setFont(customFont);
        g2d.setColor(Color.black);
        int index2 = 0;
        if(inGame){
            for(int i = 0; i<dots; i++){
                for(int j = 0; j<dots; j++){
                    g.drawImage(dot, x[j], y[i], this);
                    if(index2 < 15){
                        g2d.drawString(String.valueOf(arr[index2++]), x[j]+90, y[i] + 100);
                    }
                }
            }g.fillRect(squareX, squareY, 200, 20);
        }
    }
    public void move(){
        if(right){
            squareX+=200;
            right = false;
        }
        if(left){
            squareX-=200;
            left =false;
        }
        if(down){
            squareY += 200;
            down = false;
        }
        if(up){
            squareY-= 200;
            up = false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(inGame){
            move();
        }
        repaint();

    }
    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                down = true;
            }
            if (key == KeyEvent.VK_UP) {
                up = true;
            }
        }
    }
}
