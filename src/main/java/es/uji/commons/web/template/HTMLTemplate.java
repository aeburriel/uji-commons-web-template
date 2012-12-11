package es.uji.commons.web.template;

import java.util.Locale;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class HTMLTemplate extends GenericTemplate implements Template
{
    private final Locale locale;
    private String application;
    
    public HTMLTemplate(String name)
    {
        this(name, new Locale("ca"));
    }

    public HTMLTemplate(String name, Locale locale)
    {
        super(name);

        this.locale = locale;
    }

    public HTMLTemplate(String name, Locale locale, String application)
    {
        super(name);

        this.locale = locale;
        this.application = application;
    }

    @Override
    public byte[] process()
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine("HTML5",
                "/etc/uji/templates/", ".html", this.application);

        IContext context = new Context(locale);
        context.getVariables().putAll(properties);

        return templateEngine.process(name, context).getBytes();
    }
}