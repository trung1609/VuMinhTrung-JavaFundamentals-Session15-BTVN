package Session15.lt3;

public interface ProductManager<T> {
    void addProduct(T item);

    void removeProduct(int index);

    void display();
}
