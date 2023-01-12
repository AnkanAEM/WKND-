package com.adobe.aem.guides.wknd.core.Workflow;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {
    "process.label="+"Custom Process Step 1"
})
public class SampleProcessStep1 implements WorkflowProcess{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");


    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowsession, MetaDataMap metaDataMap) throws WorkflowException {
        logger.debug("Logger is Working");
        String payloadType = workItem.getWorkflowData().getPayloadType();
        MetaDataMap map = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
        map.put("Key1", "Value1-"+simpleDateFormat.format(date));
        logger.debug("Value is set in MetaDataMap using JAVA code");
        
    }
    
}
