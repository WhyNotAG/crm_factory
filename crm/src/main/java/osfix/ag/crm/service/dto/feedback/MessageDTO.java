package osfix.ag.crm.service.dto.feedback;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    Long id;
    Long date;
    String author;
    String text;
    Long discussionId;
}
