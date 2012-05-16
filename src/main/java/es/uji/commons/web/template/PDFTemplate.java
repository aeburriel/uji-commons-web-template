package es.uji.commons.web.template;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import org.apache.cocoon.optional.pipeline.components.sax.fop.FopSerializer;
import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.sax.SAXPipelineComponent;
import org.apache.cocoon.sax.component.XMLGenerator;
import org.apache.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class PDFTemplate extends GenericTemplate implements Template
{
    private static Logger log = Logger.getLogger(PDFTemplate.class);
    private final Locale locale;
    
    public PDFTemplate(String name)
    {
        this(name, new Locale("ca"));
    }

    public PDFTemplate(String name, Locale locale)
    {
        super(name);
        
        this.locale = locale;
    }
    
    public byte[] pdf(String data) throws Exception
    {
        Pipeline<SAXPipelineComponent> pipeline = new NonCachingPipeline<SAXPipelineComponent>();

        pipeline.addComponent(new XMLGenerator(data.getBytes()));
        pipeline.addComponent(new FopSerializer());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        pipeline.setup(bos);
        pipeline.execute();

        return bos.toByteArray();
    }

    @Override
    public byte[] process()
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine("XML",
                "/templates/", ".xml");

        IContext context = new Context(locale);
        context.getVariables().putAll(properties);

        String foSource = templateEngine.process(name, context);
        
        try
        {
            return pdf(foSource);
        }
        catch (Exception e)
        {
            log.error(e);
        }
        
        return null;
    }
}