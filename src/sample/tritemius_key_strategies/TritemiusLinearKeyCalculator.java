package sample.tritemius_key_strategies;

/**
 * Created by Dima on 09.03.2017.
 */
public class TritemiusLinearKeyCalculator implements TritemiusKeyCalculator {
    private Long a;
    private Long b;

    public TritemiusLinearKeyCalculator(Long a,Long b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public Integer getOffset(Integer inputIndex) {
        Long result = a * inputIndex + b;
        return result.intValue();
    }
}
