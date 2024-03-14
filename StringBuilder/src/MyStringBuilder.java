import java.util.Stack;

public class MyStringBuilder {

    private Stack<IMemento> history;
    MyStringBuilder sb;
    private String str;

    public MyStringBuilder(String s,MyStringBuilder sb){
        str = s;
        this.sb = sb;
        history = new Stack<>();
    }

    public MyStringBuilder(){

    }
    public void append(String s){
        str = str.concat(s);
    }

    public void getString(){
        System.out.println(str);
    }

    public StringBuilderMemento save(){
        return new StringBuilderMemento(str);
    }
    public void restore(IMemento stringBuilderMemento){
        str = stringBuilderMemento.getString();
    }

    public void backup(){
        history.push(sb.save());
    }

    public void undo(){
        if(history.isEmpty()) return;
        sb.restore(history.pop());
    }
}
