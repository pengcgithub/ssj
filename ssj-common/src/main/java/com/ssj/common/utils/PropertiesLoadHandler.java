package com.ssj.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesLoadHandler {
    private static Logger logger = LoggerFactory
            .getLogger(PropertiesLoadHandler.class);

    public static final String XML_FILE_EXTENSION = ".xml";

    private static Properties MSG_PROPERTY = new Properties();

    protected Properties[] localProperties;

    private Resource[] locations;

    public void setLocations(Resource[] locations)
    {
        this.locations = locations;
    }

    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    private String fileEncoding;

    private boolean ignoreResourceNotFound = false;

    protected boolean localOverride = false;

    public void setFileEncoding(String encoding)
    {
        this.fileEncoding = encoding;
    }

    public void setPropertiesPersister(PropertiesPersister propertiesPersister)
    {
        this.propertiesPersister = (propertiesPersister != null
                ? propertiesPersister : new DefaultPropertiesPersister());
    }

    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound)
    {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    public void setLocalOverride(boolean localOverride)
    {
        this.localOverride = localOverride;
    }

    public void setProperties(Properties properties)
    {
        this.localProperties = new Properties[] { properties };
    }

    public void init()
    {
        logger.info("开始加载自定义异常配置信息");

        try
        {
            MSG_PROPERTY = mergeProperties();
        }
        catch (IOException e)
        {
            logger.error("load errormsg property error!");
        }
        logger.info("自定义异常配置信息加载完成");

    }

    /**
     * 根据key找出信息并返回，如果不存在则返回编号
     * @param key
     * @return
     */
    public static String getProperty(String key)
    {
        String msg = MSG_PROPERTY.getProperty(key);

        if (StringUtils.isEmpty(msg))
        {
            logger.warn(
                    "Warning! No Message Had Defined For server config:" + key);
            msg = "";
        }
        return msg;
    }

    private Properties mergeProperties() throws IOException
    {
        Properties result = new Properties();

        if (this.localOverride)
        {
            loadProperties(result);
        }

        if (this.localProperties != null)
        {
            for (Properties localProp : this.localProperties)
            {
                CollectionUtils.mergePropertiesIntoMap(localProp, result);
            }
        }

        if (!this.localOverride)
        {
            // Load properties from file afterwards, to let those properties override.
            loadProperties(result);
        }

        return result;
    }

    private void loadProperties(Properties props) throws IOException
    {
        if (this.locations != null)
        {
            for (Resource location : this.locations)
            {
                if (logger.isInfoEnabled())
                {
                    logger.info("Loading properties file from " + location);
                }
                InputStream is = null;
                try
                {
                    is = location.getInputStream();
                    String filename = location.getFilename();
                    if (filename != null
                            && filename.endsWith(XML_FILE_EXTENSION))
                    {
                        this.propertiesPersister.loadFromXml(props, is);
                    }
                    else
                    {
                        if (this.fileEncoding != null)
                        {
                            this.propertiesPersister.load(props,
                                    new InputStreamReader(is,
                                            this.fileEncoding));
                        }
                        else
                        {
                            this.propertiesPersister.load(props, is);
                        }
                    }
                }
                catch (IOException ex)
                {
                    if (this.ignoreResourceNotFound)
                    {
                        if (logger.isWarnEnabled())
                        {
                            logger.warn("Could not load properties from "
                                    + location + ": " + ex.getMessage());
                        }
                    }
                    else
                    {
                        throw ex;
                    }
                }
                finally
                {
                    if (is != null)
                    {
                        is.close();
                    }
                }
            }
        }
    }
}
