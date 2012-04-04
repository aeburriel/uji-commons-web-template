package es.uji.commons.web.template;

import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class Template
{
    private String name;

    private Map<String, Object> properties;

    public Template(String name)
    {
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

        IContext context = new Context();
        context.getVariables().putAll(properties);

        return templateEngine.process(name, context);
    }
}