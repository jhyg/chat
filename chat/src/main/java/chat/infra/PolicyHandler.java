package chat.infra;

import chat.config.kafka.KafkaProcessor;
import chat.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MessageSent'"
    )
    public void wheneverMessageSent_NotifyParticipants(
        @Payload MessageSent messageSent
    ) {
        MessageSent event = messageSent;
        System.out.println(
            "\n\n##### listener NotifyParticipants : " + messageSent + "\n\n"
        );

        // Sample Logic //
        Message.notifyParticipants(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MessageSent'"
    )
    public void wheneverMessageSent_AfterMessageSent(
        @Payload MessageSent messageSent
    ) {
        MessageSent event = messageSent;
        System.out.println(
            "\n\n##### listener AfterMessageSent : " + messageSent + "\n\n"
        );

        // Sample Logic //
        ChatRoom.afterMessageSent(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
