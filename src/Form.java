import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Form  extends InitForm {
    private boolean inGame = false;
    CollectionNumbers collect;
    public Form(){
        collect = new CollectionNumbers();
        num = new DrawField(collect);
        Initialize();
        AddEvents();
        getKey();
    }
    private void ShuffleClick(){
        inGame = true;
        collect.ShuffleNumbers();
        layeredPane.repaint();
    }

    private void getKey() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inGame){
                    collect.MoveUp();
                    layeredPane.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inGame) {
                    collect.MoveDown();
                    layeredPane.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inGame) {
                    collect.MoveLeft();
                    layeredPane.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inGame) {
                    collect.MoveRight();
                    layeredPane.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
    }

    private void AddEvents(){
        buttonShuffle.addActionListener(e -> ShuffleClick());
    }


}
