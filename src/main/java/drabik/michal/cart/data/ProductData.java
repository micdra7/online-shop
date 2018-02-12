package drabik.michal.cart.data;

import drabik.michal.entity.Product;

public class ProductData extends Product {
    private Long id;
    private String producer;
    private String name;
    private Double price;
    private Integer quantity;
    private Integer selectedQuantity;

    public ProductData(Product product) {
        this.id = product.getId();
        this.producer = product.getProducer();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.selectedQuantity = 0;
    }

    public static Product getProductInstance(ProductData data) {
        return new Product(data.getProducer(), data.getName(),
                data.getDescription(), data.getPrice(), data.getQuantity());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(Integer selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
}
