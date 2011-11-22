package es.uji.commons.web.template;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;

public class Template
{
    private HttpServletRequest request;
    private String name;

    private Map<String, Object> properties;

    public Template(HttpServletRequest request, String name)
    {
        this.request = request;
        this.name = name;
        
        this.properties = new HashMap<String, Object>();
    }
    
    public void put(String key, Object value)
    {
        this.properties.put(key, value);
    }

    public String process()
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine();

        IContext context = new WebContext(request);
        context.getVariables().putAll(properties);

        return templateEngine.process(name, context);
    }
}