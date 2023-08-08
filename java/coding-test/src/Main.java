import sec01.application.Sec01Application;
import sec02.application.Sec02Application;
import sec03.application.Sec03Application;
import sec04.application.Sec04Application;

public class Main {
    public static void main(String[] args) {

        Sec01Application sec01 = new Sec01Application();
        Sec02Application sec02 = new Sec02Application();
        Sec03Application sec03 = new Sec03Application();
        Sec04Application sec04 = new Sec04Application();

        // sec01.ex12();
        // sec02.ex12();
        // sec03.ex06();
        sec04.ex05();

    }
}