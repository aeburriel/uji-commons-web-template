package es.uji.commons.web.template;

import java.util.Locale;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class HTMLTemplate extends GenericTemplate implements Template
{
    private final Locale locale;

    public HTMLTemplate(String name)
    {
        this(name, new Locale("ca"));
    }

    public HTMLTemplate(String name, Locale locale)
    {
        super(name);

        this.locale = locale;
    }

    @Override
    public byte[] process()
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine("XHTML",
                "/templates/", ".html");

        IContext context = new Context(locale);
        context.getVariables().putAll(properties);

        return templateEngine.process(name, context).getBytes();
    }
}