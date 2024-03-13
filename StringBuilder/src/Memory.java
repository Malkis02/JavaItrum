import java.util.Stack;

public class Memory {
    private Stack<IMemento> history;
    MyStringBuilder sb;

    public Memory(MyStringBuilder sb){
        this.sb = sb;
        history = new Stack<>();
    }

    public void backup(){
        history.push(sb.save());
    }

    public void undo(){
        if(history.isEmpty()) return;
        sb.restore(history.pop());
    }
}
