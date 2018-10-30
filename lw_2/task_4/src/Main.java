import java.util.function.DoubleToIntFunction;

class TypesTable {
  public static void main(String[] args) {
    PrintTypes();
    PrintLong();
    PrintInteger();
    PrintShort();
    PrintByte();
    PrintDouble();
    PrintFloat();
    PrintChar();
  }

  private static void PrintTypes() {
    System.out.printf("%s", "Type");
    System.out.printf("%10s", "Min");
    System.out.printf("%25s", "Max");
    System.out.printf("%25s%n", "Size");
  }

  private static void PrintLong(){
    System.out.printf("%s", "Long");
    System.out.printf("%26s", Long.MIN_VALUE);
    System.out.printf("%25s", Long.MAX_VALUE);
    System.out.printf("%6s%n", Long.SIZE / 8);
  }

  private static void PrintInteger(){
    System.out.printf("%s", "Integer");
    System.out.printf("%14s", Integer.MIN_VALUE);
    System.out.printf("%25s", Integer.MAX_VALUE);
    System.out.printf("%15s%n", Integer.SIZE / 8);
  }

  private static void PrintShort(){
    System.out.printf("%s", "Short");
    System.out.printf("%11s", Short.MIN_VALUE);
    System.out.printf("%25s", Short.MAX_VALUE);
    System.out.printf("%20s%n", Short.SIZE / 8);
  }

  private static void PrintByte(){
    System.out.printf("%s", "Byte");
    System.out.printf("%10s", Byte.MIN_VALUE);
    System.out.printf("%25s", Byte.MAX_VALUE);
    System.out.printf("%22s%n", Byte.SIZE / 8);
  }

  private static void PrintDouble(){
    System.out.printf("%s", "Double");
    System.out.printf("%13s", Double.MIN_VALUE);
    System.out.printf("%39s", Double.MAX_VALUE);
    System.out.printf("%3s%n", Double.SIZE / 8);
  }

  private static void PrintFloat(){
    System.out.printf("%s", "Float");
    System.out.printf("%13s", Float.MIN_VALUE);
    System.out.printf("%30s", Float.MAX_VALUE);
    System.out.printf("%13s%n", Float.SIZE / 8);
  }

  private static void PrintChar(){
    System.out.printf("%s", "Character");
    System.out.printf("%3s", (int)Character.MIN_VALUE);
    System.out.printf("%29s", (int)Character.MAX_VALUE);
    System.out.printf("%20s%n", Character.SIZE / 8);
  }
}