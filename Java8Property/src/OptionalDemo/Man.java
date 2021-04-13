package OptionalDemo;

public class Man {
    private Lady lady;

    public Man() {
    }

    public Man(Lady lady) {
        this.lady = lady;
    }

    public Lady getLady() {
        return lady;
    }

    public void setLady(Lady lady) {
        this.lady = lady;
    }

    @Override
    public String toString() {
        return "Man{" +
                "lady=" + lady +
                '}';
    }
}
