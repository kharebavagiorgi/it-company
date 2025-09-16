package report;

public class ReportGenerator implements AutoCloseable {

    public void generate() {
        System.out.println("Generating a detailed report...");
    }

    @Override
    public void close() {
        System.out.println("Report Generator closed. All resources released.");
    }

}