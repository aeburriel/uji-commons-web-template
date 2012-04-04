package es.uji.commons.web.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

public class TemplateEngineFactory
{
    private static TemplateEngine templateEngine;
     
    static
    {
        initializeTemplateEngine(false, 3600000L);
    }

    private static void initializeTemplateEngine(boolean cacheable, Long timeToLive)
    {
        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(cacheable);
        templateResolver.setCacheTTLMs(timeToLive);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public static TemplateEngine getTemplateEngine()
    {
        return templateEngine;
    }
}