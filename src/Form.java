
public class Form  extends InitForm {
    CollectionNumbers collect = new CollectionNumbers();
    public Form(){
        Initialize();
        AddEvents();
    }
    private void ShuffleClick(){
        collect.ShuffleNumbers();
        num.updateNumbers();
        layeredPane.repaint();
    }
    private void AddEvents(){
        buttonShuffle.addActionListener(e -> ShuffleClick());
    }
}
