package Pages.Templates;

import PagesStuff.Ipage;
import freeMarker.FreeMarkerUtil;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskPage implements Ipage {
    private String html;
    public TaskPage() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Template template = FreeMarkerUtil.getConfiguration().getTemplate("task.html");
        StringWriter writer = new StringWriter();
        template.process(map, writer);
        html = writer.toString();
    }

    @Override
    public String getHtmlSource() {
        return html;
    }
}
