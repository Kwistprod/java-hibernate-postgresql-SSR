package Pages;

import PagesStuff.Ipage;
import PagesStuff.PageBuilder;

public class Task implements Ipage {
    public static final String FILE_NAME = "index.html";
    private String htmlSource;
    public Task(String body){
        htmlSource = PageBuilder.getStart() + body + PageBuilder.getEnd();
    }
    public Task(){
        htmlSource = htmlSource = PageBuilder.getStart() + getDefaultBody() + PageBuilder.getEnd();
    }

    private String getDefaultBody(){
        return "<div class= \"task\">" +
                    "<p>Введите текст:</p>" +
                    "<input type=\"text\" id=\"inp\" name=\"inp\">"+
                    "<label for=\"ans\"></label>"+
                    "<button class=\"accBut\" onClick=\"getResult()\">Принять</button>" +
                    "<p id=\"answer\">Ответ: </p>"+
                "</div>";
    }

    @Override
    public String getHtmlSource() {
        return htmlSource;
    }

}
