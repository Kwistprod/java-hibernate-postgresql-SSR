package Pages;

import PagesStuff.Ipage;
import PagesStuff.PageBuilder;
import StudentsStuff.Student;
import StudentsStuff.StudentsController;

public class Students implements Ipage {
    public static final String FILE_NAME = "students.html";
    private String htmlSource;

    public Students(String body){
        htmlSource = PageBuilder.getStart() + body + PageBuilder.getEnd();
    }
    public Students(StudentsController sc){
        htmlSource = htmlSource = PageBuilder.getStart() + getDefaultBody(sc) + PageBuilder.getEnd();
    }
    private String getDefaultBody(StudentsController sc){
        String html =  "<div class=\"students\">" +
                        "<p>Перечень студентов:</p>" +
                        "<table class=\"stud-table\">"+
                        "<tr>"+
                            "<th>id</th>"+
                            "<th>Студент</th>"+
                            "<th>Возраст</th>"+
                            "<th>Действие</th>"+
                        "</tr>";
        for(Student st : sc.getStudents()){
            html +="<tr><td>" + st.getId() + "</td><td>" + st.getFIO() + "</td><td>" + st.getAge() + "</td>"+
                    "<td><button onClick=\"delRow(this)\" class=\"delBut tbt\">Удалить</button><button onClick=\"editRow(this)\" class=\"editBut tbt\">Редактировать</button></td></tr>";
        }
        html += "</table>" +
                "<div class\"butCont\">"+
                "<button id=\"addbtn\" class=\"addBtn tbtt\">Добавить</button>" +
                "</div>" +
                "<div class=\"form-wrap\">" +
                "<div class=\"formCont\">" +
                    "<input type=\"text\" id=\"fio\" name=\"field\" placeholder=\"ФИО\">" +
                    "<input type=\"text\" id=\"age\" name=\"field\" placeholder=\"Возраст\">" +
                    "<button class=\"saveBtn tbtt\">Сохранить</button>" +
                "</div>" +
                "</div>" +
                "</div>";
        return html;
    }


    @Override
    public String getHtmlSource() {
        return htmlSource;
    }
}
