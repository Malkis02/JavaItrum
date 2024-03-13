public class MyStringBuilder {
    private String str;

    public MyStringBuilder(String s){
        str = s;
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
}
