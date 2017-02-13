package caelumColletions;

/**
 * Created by daniel.ye on 10/02/17.
 */
public class ContaPoupanca extends Conta implements Comparable<ContaPoupanca> {

    public ContaPoupanca(int number) {
        super(number);
    }

    public int compareTo(ContaPoupanca other) {
        return Integer.compare(this.getNumber(), other.getNumber());
    }


}