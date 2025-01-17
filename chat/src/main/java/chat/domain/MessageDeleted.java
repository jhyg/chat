package chat.domain;

import chat.domain.*;
import chat.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MessageDeleted extends AbstractEvent {

    private String messageId;
    private String roomId;

    public MessageDeleted(Message aggregate) {
        super(aggregate);
    }

    public MessageDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
