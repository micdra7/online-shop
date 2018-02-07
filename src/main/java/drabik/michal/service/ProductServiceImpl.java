package drabik.michal.service;

import drabik.michal.dao.ProductDAO;
import drabik.michal.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO dao;

    @Transactional
    @Override
    public void addProduct(Product product) {
        dao.addProduct(product);
    }

    @Transactional
    @Override
    public Product getProduct(long id) {
        return dao.getProduct(id);
    }

    @Transactional
    @Override
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    @Transactional
    @Override
    public void deleteProduct(long id) {
        dao.deleteProduct(id);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        dao.updateProduct(product);
    }
}
