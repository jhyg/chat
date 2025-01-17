package chat.infra;

import chat.config.kafka.KafkaProcessor;
import chat.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ChatDashboardViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ChatDashboardRepository chatDashboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenChatRoomCreated_then_CREATE_1(
        @Payload ChatRoomCreated chatRoomCreated
    ) {
        try {
            if (!chatRoomCreated.validate()) return;

            // view 객체 생성
            ChatDashboard chatDashboard = new ChatDashboard();
            // view 객체에 이벤트의 Value 를 set 함
            chatDashboard.setRoomId(chatRoomCreated.getRoomId());
            chatDashboard.setRoomName(chatRoomCreated.getRoomName());
            chatDashboard.setParticipants(chatRoomCreated.getParticipants());
            // view 레파지 토리에 save
            chatDashboardRepository.save(chatDashboard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMessageSent_then_CREATE_2(
        @Payload MessageSent messageSent
    ) {
        try {
            if (!messageSent.validate()) return;

            // view 객체 생성
            ChatDashboard chatDashboard = new ChatDashboard();
            // view 객체에 이벤트의 Value 를 set 함
            chatDashboard.setRoomId(messageSent.getRoomId());
            chatDashboard.setTimestamp(messageSent.getTimestamp());
            chatDashboard.setSenderId(messageSent.getSenderId());
            chatDashboard.setMessageId(messageSent.getMessageId());
            chatDashboard.setContent(messageSent.getContent());
            // view 레파지 토리에 save
            chatDashboardRepository.save(chatDashboard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMessageSent_then_UPDATE_1(
        @Payload MessageSent messageSent
    ) {
        try {
            if (!messageSent.validate()) return;
            // view 객체 조회

            List<ChatDashboard> chatDashboardList = chatDashboardRepository.findByLastMessageTime(
                new Date(messageSent.getTimestamp())
            );
            for (ChatDashboard chatDashboard : chatDashboardList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                chatDashboard.setRoomId(messageSent.getRoomId());
                chatDashboard.setLastMessageTime(new Date(messageSent.getTimestamp()));
                chatDashboard.setMessageId(messageSent.getMessageId());
                chatDashboard.setSenderId(messageSent.getSenderId());
                chatDashboard.setContent(messageSent.getContent());
                // view 레파지 토리에 save
                chatDashboardRepository.save(chatDashboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @StreamListener(KafkaProcessor.INPUT)
    // public void whenChatRoomUpdated_then_UPDATE_2(
    //     @Payload ChatRoomUpdated chatRoomUpdated
    // ) {
    //     try {
    //         if (!chatRoomUpdated.validate()) return;
    //         // view 객체 조회

    //         List<ChatDashboard> chatDashboardList = chatDashboardRepository.findByParticipants(
    //             chatRoomUpdated.getParticipants()
    //         );
    //         for (ChatDashboard chatDashboard : chatDashboardList) {
    //             // view 객체에 이벤트의 eventDirectValue 를 set 함
    //             chatDashboard.setRoomId(chatRoomUpdated.getRoomId());
    //             chatDashboard.setParticipants(
    //                 chatRoomUpdated.getParticipants()
    //             );
    //             chatDashboard.setRoomName(chatRoomUpdated.getRoomName());
    //             // view 레파지 토리에 save
    //             chatDashboardRepository.save(chatDashboard);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenChatRoomDeleted_then_DELETE_1(
        @Payload ChatRoomDeleted chatRoomDeleted
    ) {
        try {
            if (!chatRoomDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            chatDashboardRepository.deleteById(chatRoomDeleted.getRoomId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMessageDeleted_then_DELETE_2(
        @Payload MessageDeleted messageDeleted
    ) {
        try {
            if (!messageDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            chatDashboardRepository.deleteByMessageId(
                messageDeleted.getMessageId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
