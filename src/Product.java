public class Product {
    private static int nextId = 1;

    String name;
    int typeOfProduct;
    int stock;
    double price;
    double iva;
    int id;
    int productsSold;

    public Product(String name, int typeOfProduct, int stock, double price, double iva, int productsSold) {
        this.name = name;
        this.typeOfProduct = typeOfProduct;
        this.stock = stock;
        this.price = price;
        this.iva = Calculation.calculateIva(price, typeOfProduct);
        this.id = nextId++;
        this.productsSold = productsSold;
    }

    public String getName() {
        return name;
    }

    public int getTypeOfProduct() {
        return typeOfProduct;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public double getIva() {
        return this.iva;
    }

    public double getTotal() {
        return this.price + this.iva;
    }

    public int getId() {
        return id;
    }

    public int getProductsSold() {
        return productsSold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfProduct(int typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductsSold(int productsSold) {
        this.productsSold = this.productsSold + productsSold;
    }
}
