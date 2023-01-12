package com.adobe.aem.guides.wknd.core.Workflow;

import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = ParticipantStepChooser.class, property = {
    "chooser.label="+"Dynamic Participant Chiser Step 1"
})
public class DynamicParticipantChoser1 implements ParticipantStepChooser{

    @Override
    public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args)
            throws WorkflowException {
        return "wkfapprover12023";
    }
    
}
