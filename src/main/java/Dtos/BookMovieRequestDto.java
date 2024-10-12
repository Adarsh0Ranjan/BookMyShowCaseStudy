package Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {
    private List<Long> showSeatsIds;
    private Long userId;
    private Long showId;
}
