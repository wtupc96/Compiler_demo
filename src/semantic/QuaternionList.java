package semantic;

/**
 * Created by wtupc96 on 2017/6/23.
 */
public class QuaternionList {
    private String condition;
    private String firstOperator;
    private String secondOperator;
    private String addr;

    public QuaternionList(String condition, String firstOperator, String secondOperator, String addr) {
        this.condition = condition;
        this.firstOperator = firstOperator;
        this.secondOperator = secondOperator;
        this.addr = addr;
    }
}
