public class Main {
    public static void main(String[] args) {
        MyStringBuilder msb = new MyStringBuilder("Hell",new MyStringBuilder());

        msb.getString();

        System.out.println("append:o");
        msb.append("o");

        msb.getString();

        System.out.println("Save");
        msb.backup();

        System.out.println("append: World ");
        msb.append(" World");

        msb.getString();

        System.out.println("restore in Memory");
        msb.undo();

        msb.getString();
    }
}