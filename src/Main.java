import com.kclm.cels.controller.Controller;
import com.kclm.cels.controller.impl.ControllerImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        controller.action();
    }
}