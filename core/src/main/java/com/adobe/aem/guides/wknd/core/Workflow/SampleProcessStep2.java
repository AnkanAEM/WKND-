package com.adobe.aem.guides.wknd.core.Workflow;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {
    "process.label="+"Custom Process Step 2"
})
public class SampleProcessStep2 implements WorkflowProcess{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowsession, MetaDataMap metaDataMap) throws WorkflowException {
        String payloadType = workItem.getWorkflowData().getPayloadType();
        MetaDataMap map = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
        String value = map.get("Key1", "Default Value");
        logger.debug("Value Accessed in the second workflow step is {}",value);
        
    }

}
