import javax.swing.*;

public class InitForm extends JFrame{
    protected DrawField num = new DrawField();
    protected JButton buttonShuffle = new JButton("Перемешать");
    JLayeredPane layeredPane = new JLayeredPane();

    public void Initialize(){
        setTitle("Fifteen Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(layeredPane);
        layeredPane.setLayout(null);
        layeredPane.add(num);
        layeredPane.add(buttonShuffle);

        setSize(700, 500);
        num.setBounds(20, 20,500 , 500);
        buttonShuffle.setBounds(500, 80, 150, 30);
    }
}
