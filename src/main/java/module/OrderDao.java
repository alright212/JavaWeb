package module;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao {

    private static final String ORDERS_TABLE = "orders";
    private static final String ORDER_ROWS_TABLE = "orderRows";
    private static final String ORDER_ID_COLUMN = "id";
    private static final String INSERT_ORDER_ROWS_SQL = "insert into orderRows(id, itemName, quantity, price) values (?,?,?,?)";
    private static final String SELECT_ALL_ORDERS_SQL = "select orders.id, orderNumber, itemname, price, quantity " +
            "from orders left join orderRows on orderRows.id = orders.id order by orders.id";
    private static final String DELETE_ORDER_SQL = "DELETE FROM orders WHERE id = ?";
    private static final String DELETE_ORDER_ROWS_SQL = "DELETE FROM orderRows WHERE id = ?";

    private final JdbcTemplate template;

    public OrderDao(JdbcTemplate template) {
        this.template = template;
    }

    public Order insertOrderAndRows(Order order) {
        var data = new BeanPropertySqlParameterSource(order);
        Number id = new SimpleJdbcInsert(template)
                .withTableName(ORDERS_TABLE).usingGeneratedKeyColumns(ORDER_ID_COLUMN)
                .executeAndReturnKey(data);
        order.setId(id.longValue());

        if (order.getOrderRows() != null) {
            insertOrderRows(order);
        }
        return order;
    }


    public void insertOrderRows(Order order) {
        for (OrderRows row : order.getOrderRows()) {
            template.update(INSERT_ORDER_ROWS_SQL, order.getId(), row.getItemName(), row.getQuantity(), row.getPrice());
        }

    }

    public List<Order> giveAllOrders() {
        List<List<Order>> query = template.query(SELECT_ALL_ORDERS_SQL, new OrderMapperGetAllOrders());
        return query.isEmpty() ? new ArrayList<>() : query.get(0);
    }

    public Order findOrderById(Long id) {
        String sql = "select orders.id, orderNumber, itemname, price, quantity from orders left join orderRows on orderRows.id = orders.id where orders.id = " + id;
        List<Order> query = template.query(sql, new OrderMapperById());
        return query.get(0);
    }

    public void deleteOrderById(Long id) {
        template.update(DELETE_ORDER_SQL, id);
        template.update(DELETE_ORDER_ROWS_SQL, id);
    }


}