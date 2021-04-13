package OptionalDemo;

import java.io.Serializable;
import java.util.Optional;

public class NewMan {
    private Optional<Lady> lady = Optional.ofNullable(null);

    public Optional<Lady> getLady() {
        return lady;
    }

    public void setLady(Optional<Lady> lady) {
        this.lady = lady;
    }

    public NewMan() {
    }

    public NewMan(Optional<Lady> lady) {
        this.lady = lady;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "lady=" + lady +
                '}';
    }
}
