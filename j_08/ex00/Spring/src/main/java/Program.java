import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import printer.Printer;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/context.xml");
        Printer printer = context.getBean("printerWithDateTimeErrUpper", Printer.class);
        printer.print("Hello!");

        printer = context.getBean("printerWithDateTimeStandUpper", Printer.class);
        printer.print("");

        printer = context.getBean("printerWithDateTimeErrLower", Printer.class);
        printer.print("HELLO!");

        printer = context.getBean("printerWithDateTimeStandLower", Printer.class);
        printer.print("HELLO!");

        printer = context.getBean("printerWithPrefixErrUpper", Printer.class);
        printer.print("Hello!");

        printer = context.getBean("printerWithPrefixStandUpper", Printer.class);
        printer.print("Hello!");

        printer = context.getBean("printerWithPrefixErrLower", Printer.class);
        printer.print("HELLO!");

        printer = context.getBean("printerWithPrefixStandLower", Printer.class);
        printer.print("HELLO!");
    }
}
