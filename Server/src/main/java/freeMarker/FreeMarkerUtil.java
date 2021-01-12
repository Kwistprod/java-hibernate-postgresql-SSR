package freeMarker;

import freemarker.cache.FileTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;


public class FreeMarkerUtil {
    private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
    static{
        try {
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setClassForTemplateLoading(FreeMarkerUtil.class, "/");
            cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public static Configuration getConfiguration(){
        return cfg;
    }
}
