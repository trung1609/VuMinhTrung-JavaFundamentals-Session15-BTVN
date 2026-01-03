package Session15.lt1;

public interface MovieManager <T>{
    void addMovie(T item);
    void removeMovie(int index);
    void updateMovie(int index, T item);
    void filterMovie();
    void searchMovie();
    void display();
}
