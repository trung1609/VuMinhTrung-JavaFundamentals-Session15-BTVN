package Session15.lt1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieManagement implements MovieManager<Movie> {
    static ArrayList<Movie> movieList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieManagement movieManagement = new MovieManagement();
        do {
            System.out.println("========== MENU ===========");
            System.out.println("1. Them phim");
            System.out.println("2. Xoa phim");
            System.out.println("3. Sua phim");
            System.out.println("4. Hien thi phim");
            System.out.println("5. Tim kiem phim theo ten");
            System.out.println("6. Loc phim theo rating");
            System.out.println("7. Thoat");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    movieManagement.addMovie(new Movie());
                    break;
                case 2:
                    movieManagement.removeMovie(0);
                    break;
                case 3:
                    movieManagement.updateMovie(0, new Movie());
                    break;
                case 4:
                    movieManagement.display();
                    break;
                case 5:
                    movieManagement.searchMovie();
                    break;
                case 6:
                    movieManagement.filterMovie();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui long nhap tu 1-7.");
            }
        } while (true);
    }

    @Override
    public void addMovie(Movie item) {
        Scanner sc = new Scanner(System.in);
        item.input(sc);
        movieList.add(item);
        System.out.println("Phim da duoc them thanh cong.");
    }

    public int checkMovieId(int movieId) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getId() == movieId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeMovie(int index) {
        if (movieList.isEmpty()) {
            System.out.println("Chua co phim duoc them");
        } else {
            System.out.println("*** Danh sach phim ***");
            display();
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ID phim can xoa: ");
            int movieId = Integer.parseInt(sc.nextLine());
            index = checkMovieId(movieId);
            if (index == -1) {
                System.out.println("Khong tim thay ID phim can xoa.");
            } else {
                movieList.remove(index);
                System.out.println("Phim da duoc xoa thanh cong.");
            }
        }
    }

    @Override
    public void updateMovie(int index, Movie item) {
        if (movieList.isEmpty()) {
            System.out.println("Chua co phim duoc them");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ID phim can sua: ");
            int movieId = Integer.parseInt(sc.nextLine());
            index = checkMovieId(movieId);
            if (index == -1) {
                System.out.printf("Khong tim thay ID phim voi id = %d\n.", movieId);
            } else {
                do {
                    System.out.println("1. Sua tieu de phim");
                    System.out.println("2. Sua dao dien");
                    System.out.println("3. Sua ngay phat hanh");
                    System.out.println("4. Sua rating");
                    System.out.println("5. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int movieUpdate = Integer.parseInt(sc.nextLine());
                    switch (movieUpdate) {
                        case 1:
                            System.out.print("Nhap tieu de moi: ");
                            movieList.get(index).setTitle(sc.nextLine());
                            System.out.println("Cap nhat tieu de thanh cong.");
                            break;
                        case 2:
                            System.out.print("Nhap dao dien moi: ");
                            movieList.get(index).setDirector(sc.nextLine());
                            System.out.println("Cap nhat dao dien thanh cong.");
                            break;
                        case 3:
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            LocalDate localDate;
                            do {
                                try {
                                    System.out.print("Nhap ngay phat hanh moi (dd-MM-yyyy): ");
                                    String date = sc.nextLine();
                                    localDate = LocalDate.parse(date, formatter);
                                    break;
                                } catch (DateTimeParseException e) {
                                    System.err.println("Vui long nhap dung dinh dang ngay");
                                }
                            } while (true);
                            movieList.get(index).setReleaseDate(localDate);
                            break;
                        case 4:
                            float rating;
                            do {
                                try {
                                    System.out.print("Nhap rating moi: ");
                                    rating = Float.parseFloat(sc.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.err.println("Vui long nhap dung dinh dang rating.");
                                }
                            } while (true);
                            movieList.get(index).setRating(rating);
                            break;
                        case 5:
                            return;
                        default:
                            System.err.println("Vui long nhap tu 1-5.");
                    }
                } while (true);
            }
        }
    }

    @Override
    public void filterMovie() {
        if (movieList.isEmpty()) {
            System.out.println("Chua co phim duoc them");
        } else {
            boolean isFound = false;
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap rating toi thieu de loc: ");
            float minRating = Float.parseFloat(sc.nextLine());
            System.out.printf("Phim co rating lon hon %.1f: \n", minRating);
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getRating() > minRating) {
                    System.out.println(movieList.get(i));
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay phim co rating lon hon " + minRating + ".");
            }
        }
    }

    @Override
    public void searchMovie() {
        if (movieList.isEmpty()) {
            System.out.println("Chua co phim duoc them");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap tieu de phim de tim kiem: ");
            String title = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
                    System.out.println(movieList.get(i));
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay phim co tieu de: " + title);
            }
        }
    }

    @Override
    public void display() {
        if (movieList.isEmpty()) {
            System.out.println("Chua co phim duoc them.");
        } else {
            for (Movie item : movieList) {
                System.out.println(item);
            }
        }
    }
}
