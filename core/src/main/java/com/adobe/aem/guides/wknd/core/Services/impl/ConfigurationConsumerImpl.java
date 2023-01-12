package com.adobe.aem.guides.wknd.core.Services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.adobe.aem.guides.wknd.core.OSGiConfigs.SimpleConfiguration;
import com.adobe.aem.guides.wknd.core.Services.ConfigurationConsumer;

@Component(service = ConfigurationConsumer.class)
@Designate(ocd=SimpleConfiguration.class)
public class ConfigurationConsumerImpl implements ConfigurationConsumer {
    
    public String propertyOne ;
    public String propertyTwo ;

    @Activate
    protected void activate(SimpleConfiguration simpleConfiguration){
        propertyOne = simpleConfiguration.getPropertyOne();
        propertyTwo = simpleConfiguration.getPropertyTwo();

    }
    @Override
    public String PrintValues() {

        return "The properties are"+"\n"+propertyOne+"\n"+propertyTwo;
    }
    
}
