package Problems.ecommerce.service;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ProductRepository;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductCatalogService {
    private final ProductRepository productRepository;

    public ProductCatalogService(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository, "productRepository");
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getById(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    public List<Product> searchByName(String query) {
        String needle = query.toLowerCase(Locale.ROOT);
        return productRepository.findAll().stream()
            .filter(product -> product.getName().toLowerCase(Locale.ROOT).contains(needle))
            .collect(Collectors.toList());
    }

    public List<Product> searchByCategory(Category category) {
        return productRepository.findAll().stream()
            .filter(product -> product.getCategory() == category)
            .collect(Collectors.toList());
    }
}
