package servlet;

import jakarta.validation.Valid;
import module.Order;
import module.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class PostController {

    private final OrderDao dao;

    @Autowired
    public PostController(OrderDao dao) {
        this.dao = dao;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders() {
        return dao.giveAllOrders();
    }


    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = dao.findOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> insertOrder(@RequestBody @Valid Order order) {
        return ResponseEntity.ok(dao.insertOrderAndRows(order));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        dao.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody @Valid Order order) {
//        Order updatedOrder = dao.updateOrderById(id, order);
//        if (updatedOrder == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updatedOrder);
//    }
}
