package sample.tritemius_key_strategies;

/**
 * @author DBudko
 */
public class TritemiusQuadraticKeyCalculator implements TritemiusKeyCalculator {
    private Long a;
    private Long b;
    private Long c;

    public TritemiusQuadraticKeyCalculator(Long a,Long b,Long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Override
    public Integer getOffset(Integer inputIndex) {
        Long result = a * inputIndex * inputIndex + b * inputIndex + c;
        return result.intValue();
    }
}
