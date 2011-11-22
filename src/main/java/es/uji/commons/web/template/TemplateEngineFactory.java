package es.uji.commons.web.template;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class TemplateEngineFactory
{
    private static TemplateEngine templateEngine;
     
    static
    {
        initializeTemplateEngine(false, 3600000L);
    }

    private static void initializeTemplateEngine(boolean cacheable, Long timeToLive)
    {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.XHTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
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