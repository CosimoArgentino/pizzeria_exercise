package awesome.pizzeria.pizzeria.customer.dto;

import awesome.pizzeria.pizzeria.model.Pizza;

import java.util.List;

public class OrderRequestDTO {
    String customerName;
    List<Pizza> pizzas;

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
