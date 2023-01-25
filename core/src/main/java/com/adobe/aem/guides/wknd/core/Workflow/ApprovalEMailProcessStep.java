// package com.adobe.aem.guides.wknd.core.Workflow;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import javax.jcr.RepositoryException;
// import javax.jcr.Value;

// import org.apache.commons.lang3.StringUtils;
// import org.apache.commons.mail.EmailException;
// import org.apache.commons.mail.HtmlEmail;
// import org.apache.jackrabbit.api.security.user.Authorizable;
// import org.apache.jackrabbit.api.security.user.UserManager;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;

// import com.adobe.granite.workflow.WorkflowException;
// import com.adobe.granite.workflow.WorkflowSession;
// import com.adobe.granite.workflow.exec.HistoryItem;
// import com.adobe.granite.workflow.exec.WorkItem;
// import com.adobe.granite.workflow.exec.WorkflowProcess;
// import com.adobe.granite.workflow.metadata.MetaDataMap;
// // import com.day.cq.mailer.MessageGateway;
// import com.day.cq.mailer.MessageGatewayService;
// import com.adobe.acs.commons.email.EmailService;
// @Component(service = WorkflowProcess.class, property = {
//     "process.label="+"Send Approval Email"
// })
// public class ApprovalEMailProcessStep implements WorkflowProcess {

//     final String TEMPLATE_PATH = "/etc/notification/email/acs-commons/RequestAccepted.html";

//     @Reference
//     private MessageGatewayService messageGatewayService;

//     @Reference
//     private EmailService emailService;
   

//     // public void sendEmail(List<String> recipients) throws EmailException {
//     //     MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
//     //     HtmlEmail email = new HtmlEmail();
//     //     for(String recipient : recipients){
//     //         email.addTo(recipient);
//     //     }
//     //     email.setTextMsg("Hello World!");
//     //     messageGateway.send(email);
//     // }

//     // public void sendEmail(List<String> recipients, String tmeplatePath, Map<String,String> paramMap){
        

//     // }

//     @Override
//     public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
//         Map <String,String> paramMap = new HashMap<>();
//         ResourceResolver resourceResolver = session.adaptTo(ResourceResolver.class);
//         UserManager manager = resourceResolver.adaptTo(UserManager.class);
//         String emailId="";
//         String comment="";
//         Authorizable authorizable;
//         List<HistoryItem> history = session.getHistory(item.getWorkflow());
//         if(!history.isEmpty()){
//             HistoryItem last = history.get(history.size() - 1);
//             if(StringUtils.isNotBlank(last.getComment())){
//                 comment = last.getComment();
//             }
//         }
//         try {
//             authorizable = manager.getAuthorizable(item.getWorkflow().getInitiator());
//             Value[] email = authorizable.getProperty("./profile/email");
//             emailId = email[0].toString();
//         } catch (RepositoryException e2) {
//             e2.printStackTrace();
//         }
//         String[] recipient = {"tempag2000@gmail.com"};
//         paramMap.put("senderEmail","AEM-Testing@noReply");
//         paramMap.put("commentMessage", comment);
//         paramMap.put("subject", "Testing out the AEM ACS Commons EMail Service ");


       
//         try {
//             emailService.sendEmail(TEMPLATE_PATH, paramMap, recipient);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }


//     }
    
// }
