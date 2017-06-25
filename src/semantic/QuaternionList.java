package semantic;

/**
 * Created by wtupc96 on 2017/6/23.
 */
public class QuaternionList {
    private int sequentialNumber;
    private String condition;
    private String firstOperator;
    private String secondOperator;
    private String addr;

    public QuaternionList(int sequentialNumber, String condition, String firstOperator, String secondOperator, String addr) {
        this.sequentialNumber = sequentialNumber;
        this.condition = condition;
        this.firstOperator = firstOperator;
        this.secondOperator = secondOperator;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return sequentialNumber + ": (Condition: " + condition + ", First: " + firstOperator + ", Second: " + secondOperator + ", Address: " + addr + ")";
    }
}
