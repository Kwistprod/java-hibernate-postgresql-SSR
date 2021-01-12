package PagesStuff;

public class PageBuilder {
    public static String getStart(){
        return "<html>" +
                "<head>" +
                    "<title>Practice 7</title>" +
                    "<script type=\"text/javascript\" src=\"https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js\"></script>"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" +
                "</head>" +
                "<body>"+
                    "<div class=\"header\">" +
                        "<a href=\"index.html\" class=\"header-link\">Главная</a>" +
                        "<a href=\"task.html\" class=\"header-link\">Решение задания</a>" +
                        "<a href=\"students.html\" class=\"header-link\">Таблица студентов</a>" +
                    "</div>";
    }
    public static String getEnd(){
        return "<script type=\"text/javascript\" src=\"main.js\"></script>"+
                "</body>" +
                "</html>";
    }
}
