package chat.domain;

import chat.ChatApplication;
import chat.domain.MessageDeleted;
import chat.domain.MessageSent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Message_table")
@Data
//<<< DDD / Aggregate Root
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String messageId;

    private String roomId;

    private String senderId;

    private String content;

    private Long timestamp;

    @PostPersist
    public void onPostPersist() {
        MessageSent messageSent = new MessageSent(this);
        messageSent.publishAfterCommit();

        MessageDeleted messageDeleted = new MessageDeleted(this);
        messageDeleted.publishAfterCommit();
    }

    public static MessageRepository repository() {
        MessageRepository messageRepository = ChatApplication.applicationContext.getBean(
            MessageRepository.class
        );
        return messageRepository;
    }

    //<<< Clean Arch / Port Method
    public static void notifyParticipants(MessageSent messageSent) {
        //implement business logic here:

        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        

        repository().findById(messageSent.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
