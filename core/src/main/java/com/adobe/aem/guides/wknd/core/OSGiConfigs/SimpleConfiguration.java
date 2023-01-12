package com.adobe.aem.guides.wknd.core.OSGiConfigs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name="Custom OSGI Configuration",
        description = "Thsi configuration is made to test out Custom OSGI Configuration")
public @interface SimpleConfiguration {

    @AttributeDefinition(
        name="PropertyOne",
        description = "This is a test Property Only",
        type = AttributeType.STRING

    )
    String getPropertyOne();

    @AttributeDefinition(
        name = "Property2",
        description = "This is the second dummy deopdown Field",
        options = {
            @Option(label = "Matt", value = "Matt Parker") , @Option(label = "Benn", value = "Hammer")
        }
    )
    String getPropertyTwo();
}