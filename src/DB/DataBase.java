package DB;

import InterfacesData.Employees;
import InterfacesData.Product;

import java.util.ArrayList;

public class DataBase {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Employees> employees = new ArrayList<>();

    // Constructor
    public DataBase() {
        products.add(new Product("Jobon", 1, 10, 2000, 0));
        products.add(new Product("Cuaderno", 2, 20, 1500, 0));
        products.add(new Product("Comida para perro", 3, 4, 30000, 0));
        products.add(new Product("Arroz", 4, 40, 4000, 0));

        employees.add(new Employees("Juan", "123", 20, "Diurno", new java.util.Date()));
        employees.add(new Employees("Pedro", "456", 30, "Nocturno", new java.util.Date()));
        employees.add(new Employees("Maria", "789", 40, "Mixto", new java.util.Date()));
    }

    // Add
    public void addProduct(Product product) {
        products.add(product);
    }

    public void addEmployee(Employees employee) {
        employees.add(employee);
    }

    // Get all
    public ArrayList<Product> getProducts() {
      return products;
    }

    public ArrayList<Employees> getEmployees() {
      return employees;
    }

    // Get by id
    public Product getProductsById(int id) {
        Product productsById = null;
        for (Product product : products) {
            if (product.getId() == id) {
                productsById = product;
            }
        }
        return productsById;
    }

    public Employees getEmployeesById(String id) {
        Employees employeesById = null;
        for (Employees employee : employees) {
            if (employee.getId().equals(id)) {
                employeesById = employee;
            }
        }
        return employeesById;
    }

    // Delete
    public void deleteProduct(int id) {
        Product product = getProductsById(id);
        products.remove(product);
    }

    public void deleteEmployee(String id) {
        Employees employee = getEmployeesById(id);
        employees.remove(employee);
    }

    // Update
    public void updateProduct(Product product) {
        Product productById = getProductsById(product.getId());
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        productById.setTypeOfProduct(product.getTypeOfProduct());
        productById.setStock(product.getStock());
        productById.setProductsSold(product.getProductsSold());
    }

    public void updateEmployee(Employees employee) {
        Employees employeeById = getEmployeesById(employee.getId());
        employeeById.setName(employee.getName());
        employeeById.setAge(employee.getAge());
        employeeById.setWorkingDay(employee.getWorkingDay());
        employeeById.setStartDate(employee.getStartDate());
    }

}
