package chat.infra;
import java.util.Date;
import chat.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "chatDashboards",
    path = "chatDashboards"
)
public interface ChatDashboardRepository
    extends PagingAndSortingRepository<ChatDashboard, String> {
    List<ChatDashboard> findByLastMessageTime(Date lastMessageTime);
    // List<ChatDashboard> findByParticipants(List<String> participants);

    void deleteByMessageId(String messageId);
    void deleteByRoomId(String roomId);
}
