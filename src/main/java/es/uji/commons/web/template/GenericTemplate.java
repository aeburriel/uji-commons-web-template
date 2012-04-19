package es.uji.commons.web.template;

import java.util.HashMap;
import java.util.Map;

public abstract class GenericTemplate
{
    protected String name;
    protected Map<String, Object> properties;

    public GenericTemplate(String name)
    {
        this.name = name;
        this.properties = new HashMap<String, Object>();
    }

    public void put(String key, Object value)
    {
        this.properties.put(key, value);
    }
}
