package Session15.lt2;

public interface SubjectManager <T>{
    void addSubject(T subject);
    void removeSubject(int index);
    void searchSubject();
    void filterSubject();
    void display();
}
