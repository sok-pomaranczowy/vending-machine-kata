package tdd.vendingMachine;

/**
 * Created by sok_pomaranczowy on 19.03.17.
 */
public class Product {
    private String name;
    private TYPE type;

    public Product(String name, TYPE type) {
        this.name = name;
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public enum TYPE {
        BEVERAGE, FOOD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return type == product.type;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
