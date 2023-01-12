package com.adobe.aem.guides.wknd.core.Workflow;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@Component(service = WorkflowProcess.class, property = {
    "process.label="+"Send Approval Email"
})
public class ApprovalEMailProcessStep implements WorkflowProcess {
    @Reference
    private MessageGatewayService messageGatewayService;

    public void sendEmail(List<String> recipients) throws EmailException {
        MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
        HtmlEmail email = new HtmlEmail();
        for(String recipient : recipients){
            email.addTo(recipient);
        }
        email.setTextMsg("Hello World!");
        messageGateway.send(email);
    }
    

    @Override
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
        ResourceResolver resourceResolver = session.adaptTo(ResourceResolver.class);
        UserManager manager = resourceResolver.adaptTo(UserManager.class);
        String emailId="";
        Authorizable authorizable;
        
        try {
            authorizable = manager.getAuthorizable(item.getWorkflow().getInitiator());
            Value[] email = authorizable.getProperty("./profile/email");
            emailId = email[0].toString();
        } catch (RepositoryException e2) {
            e2.printStackTrace();
        }
        List<String> recipient = new ArrayList<String>();
        recipient.add(emailId);
        try {
            sendEmail(recipient);
        } catch (EmailException e) {
            e.printStackTrace();
        }


    }
    
}
