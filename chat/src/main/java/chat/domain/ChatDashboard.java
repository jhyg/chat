package chat.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;
import java.time.LocalDate;


//<<< EDA / CQRS
@Entity
@Table(name="ChatDashboard_table")
@Data
public class ChatDashboard {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private String roomId;

        @ElementCollection
        private List<String> participants;
        
        private Date lastMessageTime;
        private String content;
        private Long timestamp;
        private String senderId;
        private String messageId;
        private String roomName;


}
