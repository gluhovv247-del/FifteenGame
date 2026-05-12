import javax.swing.*;
import java.awt.event.ActionEvent;

public class Form  extends InitForm {
    private boolean inGame = false;
    CollectionNumbers collect;

    public Form(){
        collect = new CollectionNumbers();
        recordStorage = new RecordStorage();
        num = new DrawField(collect);
        Initialize();
        AddEvents();
        getKey();
    }
    private void ShuffleClick(){
        inGame = true;
        secondsPassed = 0;
        timer.start();
        collect.ShuffleNumbers();
        layeredPane.repaint();
    }
    public void checkWin(){
        if(collect.victoryCondition()){
            inGame = false;
            timer.stop();
            JOptionPane.showMessageDialog(Form.this, "Пятнашки собраны! Время:" + String.format("%02d:%02d", currentMinutes, currentSeconds) );
            recordStorage.addRecord(currentMinutes, currentSeconds);
        }
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
                if(inGame && !collect.victoryCondition()){
                    collect.MoveUp();
                    layeredPane.repaint();
                    checkWin();
                }
                else{
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inGame && !collect.victoryCondition()) {
                    collect.MoveDown();
                    layeredPane.repaint();
                    checkWin();
                }
                else{
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inGame && !collect.victoryCondition()) {
                    collect.MoveLeft();
                    layeredPane.repaint();
                    checkWin();
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
                    checkWin();
                }
                else if(!inGame){
                    JOptionPane.showMessageDialog(Form.this, "Сначала перемешайте");
                }
            }
        });
    }
    private void AddEvents(){
        buttonShuffle.addActionListener(e -> ShuffleClick());
        buttonRecords.addActionListener(e -> ShowRecordsDialog());
    }
}
