package chat.domain;

import chat.domain.*;
import chat.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;
import javax.persistence.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ChatRoomUpdated extends AbstractEvent {

    private String roomId;
    private String roomName;

    @ElementCollection
    private List<String> participants;

    public ChatRoomUpdated(ChatRoom aggregate) {
        super(aggregate);
    }

    public ChatRoomUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
