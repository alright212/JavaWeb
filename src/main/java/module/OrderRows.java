package module;

import lombok.Getter;
import jakarta.validation.constraints.Min;


import java.util.Objects;

@Getter
public class OrderRows {
    private String itemName;
    @Min(1)
    private int quantity;
    @Min(1)
    private int price;

    public OrderRows() {}

    public OrderRows(String itemName, Integer quantity, Integer price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderRow{" +
                "itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderRows orderRows)) {
            return false;
        }
        return Objects.equals(itemName, orderRows.itemName)
                && Objects.equals(quantity, orderRows.quantity)
                && Objects.equals(price, orderRows.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, quantity, price);
    }
}
