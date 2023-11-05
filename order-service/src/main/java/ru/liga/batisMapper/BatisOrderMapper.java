package ru.liga.batisMapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.liga.entity.*;

public interface BatisOrderMapper {

    @Select("select * from orders " +
            "left join restaurants on orders.restaurant_id = restaurants.restaurant_id " +
            "left join customers on orders.customer_id = customers.customer_id " +
            "left join couriers on orders.courier_id = couriers.courier_id " +
            "where orders.order_id = #{id}"
    )
    @Results(value = {
            @Result(property = "id", column = "order_id"),
            @Result(property = "customer", column = "customer_id", one = @One(select = "getCustomer")),
            @Result(property = "restaurant", column = "restaurant_id", one = @One(select = "getRestaurant")),
            @Result(property = "courier", column = "courier_id", one = @One(select = "getCourier"))
    })
    Order getOrderById(Long id);

    @Select("select * from restaurants where restaurants.restaurant_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "restaurant_id"),
            @Result(property = "address", column = "address_id", one = @One(select = "getAddress")),
            @Result(property = "payment", column = "payment_id", one = @One(select = "getPayment"))
    })
    Restaurant getRestaurant(Long id);

    @Select("select * from customers where customers.customer_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "customer_id"),
            @Result(property = "address", column = "address_id", one = @One(select = "getAddress"))
    })
    Customer getCustomer(Long id);

    @Select("select * from couriers where couriers.courier_id = #{id}")
    @Result(property = "id", column = "courier_id")
    Courier getCourier(Long id);

    @Select("select * from address where address.address_id = #{id}")
    @Result(property = "id", column = "address_id")
    Address getAddress(Long id);

    @Select("select * from payments where payments.payment_id = #{id}")
    @Result(property = "id", column = "payment_id")
    Payment getPayment(Long id);
}
