package Pages;

import PagesStuff.Ipage;
import PagesStuff.PageBuilder;

public class Index implements Ipage {
    public static final String FILE_NAME = "index.html";
    private String htmlSource;

    public Index(String body){
        htmlSource = PageBuilder.getStart() + body + PageBuilder.getEnd();
    }
    public Index(){
        htmlSource = PageBuilder.getStart() + getDefaultBody() + PageBuilder.getEnd();
    }
    @Override
    public String getHtmlSource(){
        return htmlSource;
    }
    public String getDefaultBody(){
        return "<p>Работу Выполнил: Соловьев Илья Игоревич</p>" +
                "<p>Номер группы: ИКБО-03-18</p>" +
                "<p>Номер индивидуального задания: 7</p>" +
                "<p>Текст индивидуального задания: Дан текст. Определите процентное отношение строчных и прописных\n" +
                "букв к общему числу символов в нем. На вход поступает текст в виде строки.</p>";
    }

}
