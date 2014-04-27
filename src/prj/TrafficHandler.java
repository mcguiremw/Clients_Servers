package prj;

/**
 * Class that will start the producing and consuming of different types of threads.
 * Multiple producers are distinguished by the data type of threads it is producing, any consumer can consume any type
 * of thread produced.
 * <p/>
 * Created by McGuireMW on 4/19/14.
 */
public class TrafficHandler extends Thread {

    private static Consumer doubleConsumer;
    private static Consumer integerConsumer;
    private static Consumer stringConsumer;

    private static final int DOUBLE_CONS_PORT = 6651;
    private static final int INTEGER_CONS_PORT = 6652;
    private static final int STRING_CONS_PORT = 6650;


    public static void main(String[] args) {
        try {
            initializeTraffic();
        } catch (Exception e) {
            System.err.println("Error running internet: " + e);
        }
    }

    private static void initializeTraffic() {
        stringConsumer = new Consumer(STRING_CONS_PORT);
    }
}
