package chat.infra;

import chat.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "chatDashboards",
    path = "chatDashboards"
)
public interface ChatDashboardRepository
    extends PagingAndSortingRepository<ChatDashboard, Long> {
    List<ChatDashboard> findByLastMessageTime(Date lastMessageTime);
    List<ChatDashboard> findByParticipants(List<String> participants);

    void deleteByMessageId(String messageId);
}
