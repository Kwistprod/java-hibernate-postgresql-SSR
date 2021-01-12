package Pages.Templates;

import PagesStuff.Ipage;
import StudentsStuff.Student;
import StudentsStuff.StudentsController;
import freeMarker.FreeMarkerUtil;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsPage implements Ipage {
    private String html;
    public StudentsPage(StudentsController sc) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Student> st = sc.getStudents();
        map.put("students", st);
        Template template = FreeMarkerUtil.getConfiguration().getTemplate("students.html");
        StringWriter writer = new StringWriter();
        template.process(map, writer);
        html = writer.toString();
    }

    @Override
    public String getHtmlSource() {
        return html;
    }
}
