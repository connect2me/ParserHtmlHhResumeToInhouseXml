package ru.connect2me.util.hh.config;

import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author r.zaynullin
 */
public abstract class Module {
    static final Logger log = Logger.getLogger("ru.pronto.simple");
    static Properties props;// свойства из config.xml, props короче чем getProperties()

    public Module(Configuration config){
        init(config);
    }

    private void init(Configuration config) {
        props = config.getProperties();
    }

    public Properties getProperties() {
        return props;
    }
}
