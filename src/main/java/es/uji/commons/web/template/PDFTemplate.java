package es.uji.commons.web.template;

import java.io.ByteArrayOutputStream;

import org.apache.cocoon.optional.pipeline.components.sax.fop.FopSerializer;
import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.sax.SAXPipelineComponent;
import org.apache.cocoon.sax.component.XMLGenerator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class PDFTemplate extends GenericTemplate implements Template
{
    public PDFTemplate(String name)
    {
        super(name);
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
    public byte[] process() throws Exception
    {
        TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine("XML",
                "/templates/", ".xml");

        IContext context = new Context();
        context.getVariables().putAll(properties);

        String foSource = templateEngine.process(name, context);
        return pdf(foSource);
    }
}