package printer;

import org.springframework.cglib.core.Local;
import renderer.Renderer;
import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        if (text.equals("")){
            renderer.print("" + LocalDateTime.now());
        }
        else
            renderer.print(text + " " + LocalDateTime.now());
    }
}
