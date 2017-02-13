package caelumColletions;

/**
 * Created by daniel.ye on 10/02/17.
 */
abstract class Conta {
    protected int number;

    public Conta(int number) {
        this.number = number;
    }

    protected void setNumber(int number) {
        this.number = number;
    }

    protected int getNumber() {
        return number;

    }

}
