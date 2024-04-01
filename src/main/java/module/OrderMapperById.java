package module;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapperById implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = Order.builder().build();
        List<OrderRows> orderRowList = new ArrayList<>();
        order.setId(rs.getLong("id"));
        order.setOrderNumber(rs.getString("orderNumber"));
        orderRowList.add(new OrderRows(rs.getString("itemName"),
                rs.getInt("quantity"),
                rs.getInt("price")));
        while (rs.next()) {
            orderRowList.add(new OrderRows(rs.getString("itemName"),
                    rs.getInt("quantity"),
                    rs.getInt("price")));
        }
        order.setOrderRows(orderRowList);


        return order;
    }
}