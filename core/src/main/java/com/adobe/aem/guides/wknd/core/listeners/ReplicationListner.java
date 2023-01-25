// package com.adobe.aem.guides.wknd.core.listeners;

// import org.apache.jackrabbit.api.security.user.Authorizable;
// import org.apache.jackrabbit.api.security.user.UserManager;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ResourceResolverFactory;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;
// import org.osgi.service.event.Event;
// import org.osgi.service.event.EventConstants;
// import org.osgi.service.event.EventHandler;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import com.day.cq.replication.ReplicationAction;

// @Component(service = EventHandler.class,
//             immediate = true,
//             property = {
//                 EventConstants.EVENT_TOPIC+"="+ReplicationAction.EVENT_TOPIC
//             }
//     )

// public class ReplicationListner implements EventHandler {
//     private static final Logger log = LoggerFactory.getLogger(ReplicationListner.class);
    
//     @Reference 
//     private ResourceResolver resourceResolver;

//     @Override
//     public void handleEvent(Event event) {
//         try {
//             log.info("Replication Triggered");
//             String pathInfo = ReplicationAction.fromEvent(event).getPath();
//             String userID = ReplicationAction.fromEvent(event).getUserId();
//             UserManager userManager = resourceResolver.adaptTo(UserManager.class);
//             Authorizable authorizable = userManager.getAuthorizable(userID);
//             String EmailID=authorizable.getProperty("./profile/email").toString();
//             log.info("Replication Triggered at path = "+pathInfo);
//         }
//         catch(Exception e){
//             log.info(e.getMessage());
//         }
        
//     }
    
// }
