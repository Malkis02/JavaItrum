public class StringBuilderMemento implements IMemento{
    private String str;

    public StringBuilderMemento(String s){
        str = s;
    }
    @Override
    public String getString() {
        return str;
    }
}
