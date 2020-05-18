package osfix.ag.crm.service.dto.feedback;

import lombok.Data;

@Data
public class DiscussionDTO {
    Long id;
    Long date;
    String author;
    String status;
    String subject;
    String text;
    Boolean isRead;
}
