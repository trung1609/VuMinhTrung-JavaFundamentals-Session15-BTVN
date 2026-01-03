package Session15.lt1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Movie {
    private int id;
    public static int AUTO_ID = 1;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private float rating;

    public Movie(int id, String title, String director, LocalDate releaseDate, float rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %d - Tieu de: %s - Dao dien: %s - Ngay phat hanh: %s - Rating: %.1f", id, title, director, releaseDate.format(formatter), rating);
    }

    public void input(Scanner sc) {
        this.id = AUTO_ID++;
        System.out.print("Nhap tieu de phim: ");
        this.title = sc.nextLine();
        System.out.print("Nhap dao dien: ");
        this.director = sc.nextLine();
        this.releaseDate = inputReleaseDate(sc);
        this.rating = inputRating(sc);
    }

    public LocalDate inputReleaseDate(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        do{
            try {
                System.out.print("Nhap ngay phat hanh (dd-MM-yyyy): ");
                String date = sc.nextLine();
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Vui long nhap dung dinh dang ngay");
            }
        }while (true);
    }
    public float inputRating(Scanner sc) {
        do{
            try {
                System.out.print("Nhap rating: ");
                float rating = Float.parseFloat(sc.nextLine());
                return rating;
            }catch (NumberFormatException e){
                System.err.println("Vui long nhap dung dinh dang rating.");
            }
        }while (true);
    }
}
