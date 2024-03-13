public class Main {
    public static void main(String[] args) {
        MyStringBuilder msb = new MyStringBuilder("Hell");

        Memory mem = new Memory(msb);

        msb.getString();

        System.out.println("append:o");
        msb.append("o");

        msb.getString();

        System.out.println("Save");
        mem.backup();

        System.out.println("append: World ");
        msb.append(" World");

        msb.getString();

        System.out.println("restore in Memory");
        mem.undo();

        msb.getString();
    }
}