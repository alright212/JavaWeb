package module;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapperGetAllOrders implements RowMapper<List<Order>> {

    @Override
    public List<Order> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Order currentOrder;
        currentOrder = Order.builder().id(rs.getLong("id"))
                .orderNumber(rs.getString("ordernumber")).orderRows(new ArrayList<>()).build();
        orders.add(currentOrder);


        if (rs.getString("itemname") != null) {
            add(currentOrder, rs);
        }

        while (rs.next()) {

            if (!rs.getString("id").equals(String.valueOf(currentOrder.getId()))) {

                currentOrder = Order.builder().id(rs.getLong("id"))
                        .orderNumber(rs.getString("ordernumber")).orderRows(new ArrayList<>()).build();
                orders.add(currentOrder);

            }

            if (rs.getString("itemname") != null) {
                add(currentOrder, rs);
            }


        }
        return orders;
    }

    private void add(Order order, ResultSet rs) throws SQLException {
        order.add(new OrderRows(rs.getString("itemname"),
                rs.getInt("quantity"),
                rs.getInt("price")));
    }
}