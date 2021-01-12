package PagesStuff;

public enum PAGES {
    INDEX("index.html"), TASK("task.html"), STUDENTS("students.html");
    private String name;
    PAGES(String str){name=str;}

    public String value() {
        return name;
    }
}
