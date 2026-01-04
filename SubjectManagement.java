package Session15.lt2;

import java.util.ArrayList;
import java.util.Scanner;

public class SubjectManagement implements SubjectManager<Subject> {
    static ArrayList<Subject> listSubjects = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SubjectManagement subjectManagement = new SubjectManagement();
        do {
            System.out.println("============ MENU ============");
            System.out.println("1. Hien thi danh sach mon hoc");
            System.out.println("2. Them mon hoc");
            System.out.println("3. Xoa mon hoc");
            System.out.println("4. Tim kiem mon hoc theo ten");
            System.out.println("5. Loc mon hoc theo tin chi");
            System.out.println("6. Thoat");
            System.out.print("Lua chon cua ban: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        subjectManagement.display();
                        break;
                    case 2:
                        subjectManagement.addSubject(new Subject());
                        break;
                    case 3:
                        subjectManagement.removeSubject(0);
                        break;
                    case 4:
                        subjectManagement.searchSubject();
                        break;
                    case 5:
                        subjectManagement.filterSubject();
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.err.println("Vui long nhap tu 1-6.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui long nhap dung dinh dang.");
            }
        } while (true);
    }

    @Override
    public void addSubject(Subject subject) {
        Scanner sc = new Scanner(System.in);
        subject.input(sc);
        listSubjects.add(subject);
        System.out.println("Them mon hoc thanh cong.");
    }

    public int checkSubjectId(String subjectId) {
        for (int i = 0; i < listSubjects.size(); i++) {
            if (listSubjects.get(i).code.equals(subjectId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeSubject(int index) {
        if (listSubjects.isEmpty()) {
            System.out.println("Chua co mon hoc");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("=== Danh sach mon hoc ===");
            display();
            System.out.print("Nhap code mon hoc can xoa: ");
            String subjectId = sc.nextLine();
            index = checkSubjectId(subjectId);
            if (index == -1) {
                System.err.println("Khong tim thay mon hoc co code la: " + subjectId);
            } else {
                listSubjects.remove(index);
                System.out.println("Xoa khoa hoc thanh cong.");
            }
        }
    }

    @Override
    public void searchSubject() {
        if (listSubjects.isEmpty()) {
            System.out.println("Chua co mon hoc");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ten khoa hoc can tim: ");
            String subjectName = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < listSubjects.size(); i++) {
                if (listSubjects.get(i).name.toLowerCase().contains(subjectName.toLowerCase())) {
                    System.out.println(listSubjects.get(i));
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong co mon hoc phu hop.");
            }
        }
    }

    @Override
    public void filterSubject() {
        if (listSubjects.isEmpty()) {
            System.out.println("Chua co mon hoc");
        } else {
            boolean isFound = false;
            System.out.println("Cac mon hoc co credits lon hon 3: ");
            for (int i = 0; i < listSubjects.size(); i++) {
                if (listSubjects.get(i).credits > 3) {
                    System.out.println(listSubjects.get(i));
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong co mon hoc nao co credits lon hon 3.");
            }
        }
    }

    @Override
    public void display() {
        if (listSubjects.isEmpty()) {
            System.out.println("Chua co mon hoc.");
        } else {
            for (int i = 0; i < listSubjects.size(); i++) {
                System.out.println(listSubjects.get(i));
            }
        }
    }
}
