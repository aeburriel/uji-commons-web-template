package es.uji.commons.web.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class HTMLTemplate extends GenericTemplate implements Template
{
    public HTMLTemplate(String name)
    {
        super(name);
    }

    @Override
    public byte[] process()
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine("XHTML",
                "/templates/", ".html");

        IContext context = new Context();
        context.getVariables().putAll(properties);

        return templateEngine.process(name, context).getBytes();
    }
}