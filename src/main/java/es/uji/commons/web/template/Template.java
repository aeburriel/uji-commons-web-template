package es.uji.commons.web.template;


public interface Template
{
    void put(String key, Object value);

    byte[] process() throws Exception;
}