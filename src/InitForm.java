import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitForm extends JFrame{
    protected DrawField num;
    protected RecordStorage recordStorage;
    protected JButton buttonShuffle = new JButton("Перемешать");
    protected JButton buttonRecords = new JButton("Рекорды");
    protected JLayeredPane layeredPane = new JLayeredPane();
    protected Timer timer;
    protected JLabel labelTime = new JLabel("00:00");
    protected JDialog recordsDialog;
    private DefaultListModel<String> listModel;
    private JList<String> listRecords;
    protected int secondsPassed = 0;
    protected int currentSeconds = 0;
    protected int currentMinutes = 0;

    public void Initialize(){
        setTitle("Fifteen Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(layeredPane);
        labelTime.setFont(new Font("Arial", Font.BOLD, 30));

        layeredPane.add(num, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(labelTime, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonShuffle, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buttonRecords, JLayeredPane.PALETTE_LAYER);
        timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassed++;
                currentMinutes = (secondsPassed % 3600) / 60;
                currentSeconds = secondsPassed % 60;
                labelTime.setText(String.format("%02d:%02d", currentMinutes, currentSeconds));
            }
        });

        layeredPane.setLayout(null);
        setSize(700, 500);
        num.setBounds(20, 20,500 , 500);
        labelTime.setBounds(30, 30, 100, 30);
        buttonShuffle.setBounds(400, 180, 150, 50);
        buttonRecords.setBounds(430, 250, 100, 30);
    }
    protected void ShowRecordsDialog(){
        recordsDialog = new JDialog(this, "Таблица рекордов", true);
        recordsDialog.setSize(300, 200);
        recordsDialog.setLocationRelativeTo(this);
        listModel = new DefaultListModel<>();
        for (Record record : recordStorage.getAllRecords()) {
            listModel.addElement(record.toString());
        }
        listRecords = new JList<>(listModel);
        recordsDialog.add(listRecords);
        recordsDialog.setVisible(true);

    }
}
