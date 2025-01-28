package app.tp136.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String text;
    private String userFirstName;
    private String userLastName;
    private LocalDateTime created;
}
