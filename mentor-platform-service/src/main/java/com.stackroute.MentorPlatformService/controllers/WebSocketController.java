package com.stackroute.MentorPlatformService.controllers;

import com.stackroute.MentorPlatformService.DAO.Message;
import com.stackroute.MentorPlatformService.DAO.Session;
import com.stackroute.MentorPlatformService.DAO.SessionDto;
import com.stackroute.MentorPlatformService.Repository.SessionRepository;
import com.stackroute.MentorPlatformService.service.RabbitMqSender;
import com.stackroute.MentorPlatformService.service.WebSocketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class WebSocketController {

    @Autowired
    SessionRepository sessionRepository;


    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private final SimpMessagingTemplate template;
    @Autowired
    private WebSocketServiceImpl webSocketService;
    @Autowired
    private RabbitMqSender rabbitMqSender;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }


    int i = 1;

    @MessageMapping("/send/message/{sessionId}")
    public void onReceivedMesage(@Payload Message message, @DestinationVariable String sessionId) {
        logger.info("this is the " + message.toString());
        Message messageDTO=new Message();
        messageDTO.setTimeStamp(new SimpleDateFormat("HH:mm").format(new Date()).toString());
        messageDTO.setUserName(message.getUserName());
        messageDTO.setMessageContent(message.getMessageContent());
        logger.info("this is the message dto one" + messageDTO.toString());

        Message savedMessage= this.webSocketService.saveMessage(messageDTO);
        logger.info("Saved Message is:: " + savedMessage);

        Session savedSession=this.webSocketService.updateSessionwithMessages(sessionId,savedMessage.getMessageId());
        this.template.convertAndSend("/chat/"+sessionId, messageDTO);//sessionid



    }
    @PostMapping("/session/start")
    public Session startSession(@RequestBody Session session){
        Session getSession=this.webSocketService.startSession(session);
        return getSession;
    }
    @PostMapping("/session/{sessionId}/end")
    public Session endSession(@PathVariable("sessionId") String sessionId){
        Session getSession=this.webSocketService.endSession(sessionId);

        rabbitMqSender.send(routingKey,getSession);

        return getSession;
    }
    @GetMapping("session/message/{sessionId}")
    public SessionDto getMessageDetails(@PathVariable String sessionId){
        SessionDto getMessage = webSocketService.getBySessionId(sessionId);
        return getMessage;
    }
    @GetMapping("session/mentor/{teacherEmail}/{sessionStatus}")
    public Session getMentor(@PathVariable String teacherEmail , @PathVariable String sessionStatus){
        Session getSession = webSocketService.getMentorByEmailandStatus(teacherEmail,sessionStatus);
        return getSession;
    }

    @GetMapping("/sessions/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Session>> getsessionByEmail(@PathVariable String email){
        return new ResponseEntity<List<Session>>(sessionRepository.findByStudentEmail(email), HttpStatus.OK);
    }
    @GetMapping("/session/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Session>> getsessionByTeacherEmail(@PathVariable String email){
        return new ResponseEntity<List<Session>>(sessionRepository.findByTeacherEmail(email), HttpStatus.OK);
    }
    @PostMapping("/query")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Session> getUpdatedSession(@RequestBody Session session){
//            Session sess = sessionRepository.findBySessionId(session.getSessionId());
//            sess.setSubject(subject);
//            sess.setQuery(query);
        return new ResponseEntity<Session>(sessionRepository.save(session),HttpStatus.OK);

    }
    @PostMapping("/session/{sessionId}/{subject}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Session> getUpdated(@DestinationVariable String sessionId,@DestinationVariable String subject) {
        Session sess = sessionRepository.findBySessionId(sessionId);
        sess.setSubject(subject);
//        sess.setQuery(query);
        return new ResponseEntity<Session>(sessionRepository.save(sess), HttpStatus.OK);
    }

    @GetMapping("session/{sessionID}/feedback")
    public Session getSessionbyID(@PathVariable String sessionID){
        Session get1 = webSocketService.getSession(sessionID);
        return get1;

    }
}

