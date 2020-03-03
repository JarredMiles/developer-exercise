package assignment.implem;

import assignment.api.Item;

import java.util.Objects;

public class ItemImplem implements Item {
    private String id;
    private String name;
    private int amount;

    private ItemImplem(String id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemImplem item = (ItemImplem) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount);
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ", " + amount + ")";
    }

    public static Item newItem(String id, String name, int amount) {
        return new ItemImplem(id, name, amount);
    }
}
