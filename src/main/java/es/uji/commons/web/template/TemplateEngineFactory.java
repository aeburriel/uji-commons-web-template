package es.uji.commons.web.template;

import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

public class TemplateEngineFactory
{
    private static Map<String, TemplateEngine> templateEngines = new HashMap<String, TemplateEngine>();

    private static TemplateEngine initializeTemplateEngine(String templateMode, String prefix,
            String sufix, boolean cacheable, Long timeToLive)
    {
        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(templateMode);
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(sufix);
        templateResolver.setCacheable(cacheable);
        templateResolver.setCacheTTLMs(timeToLive);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageResolver(new OneFileMessageResolver());
        
        return templateEngine;
    }

    public static TemplateEngine getTemplateEngine(String templateMode, String prefix, String sufix)
    {
        return getTemplateEngine(templateMode, prefix, sufix, false, 3600000L);
    }

    public static TemplateEngine getTemplateEngine(String templateMode, String prefix,
            String sufix, boolean cacheable, Long timeToLive)
    {
        if (templateEngines == null || !templateEngines.containsKey(templateMode))
        {
            TemplateEngine templateEngine = initializeTemplateEngine(templateMode, prefix, sufix,
                    false, 3600000L);
            templateEngines.put(templateMode, templateEngine);
        }

        return templateEngines.get(templateMode);
    }
}