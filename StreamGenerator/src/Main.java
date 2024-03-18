import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("PC",2000.0)
        );
                Map<String,Double> groupingOrders =  orders.stream()
                        .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));
        System.out.println(groupingOrders);

        groupingOrders.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(System.out::println);

        List<Order> sortedDesc = orders.stream()
                .sorted(Comparator.comparing(Order::getCost).reversed())
                .distinct()
                .limit(3)
                .toList();
        sortedDesc.forEach(System.out::println);

        groupingOrders.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .forEach(System.out::println);
    }
}