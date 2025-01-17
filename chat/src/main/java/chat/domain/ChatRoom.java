package chat.domain;

import chat.ChatApplication;
import chat.domain.ChatRoomCreated;
import chat.domain.ChatRoomDeleted;
import chat.domain.ChatRoomUpdated;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ChatRoom_table")
@Data
//<<< DDD / Aggregate Root
public class ChatRoom {

    @Id
    private String roomId;

    private String roomName;

    @ElementCollection
    private List<String> participants;

    private Date createdDate;

    private String status;

    private Date lastMessageTime;

    @PostPersist
    public void onPostPersist() {
        ChatRoomCreated chatRoomCreated = new ChatRoomCreated(this);
        chatRoomCreated.publishAfterCommit();

        ChatRoomDeleted chatRoomDeleted = new ChatRoomDeleted(this);
        chatRoomDeleted.publishAfterCommit();

        ChatRoomUpdated chatRoomUpdated = new ChatRoomUpdated(this);
        chatRoomUpdated.publishAfterCommit();
    }

    public static ChatRoomRepository repository() {
        ChatRoomRepository chatRoomRepository = ChatApplication.applicationContext.getBean(
            ChatRoomRepository.class
        );
        return chatRoomRepository;
    }

    //<<< Clean Arch / Port Method
    public static void afterMessageSent(MessageSent messageSent) {
        //implement business logic here:

        /** Example 1:  new item 
        ChatRoom chatRoom = new ChatRoom();
        repository().save(chatRoom);

        */

        /** Example 2:  finding and process
        

        repository().findById(messageSent.get???()).ifPresent(chatRoom->{
            
            chatRoom // do something
            repository().save(chatRoom);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
