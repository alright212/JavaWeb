package module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.Valid;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@AllArgsConstructor
public class Order {

    private static AtomicInteger idAutoIncrement = new AtomicInteger(1);

    private Long id;
    private String orderNumber;
    private List<@Valid OrderRows> orderRows;
    public Order() {
        this.id = (long) idAutoIncrement.getAndIncrement();
        this.orderRows = new ArrayList<>();
    }

    public Order(String orderNumber) {
        this((long) idAutoIncrement.getAndIncrement(), orderNumber, new ArrayList<>());
    }

    public void add(OrderRows orderRow) {
        if (this.orderRows == null) {
            this.orderRows = new ArrayList<>();
        }
        this.orderRows.add(orderRow);
    }

    public void autoIdDecrement() {
        idAutoIncrement.decrementAndGet();
    }
}
