package awesome.pizzeria.dto;

import awesome.pizzeria.status.Pizza;

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
