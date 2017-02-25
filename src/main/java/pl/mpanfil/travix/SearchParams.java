package pl.mpanfil.travix;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Created by Marcin Panfil on 16.02.17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParams {

    @NotNull
    @Size(min = 3, max = 3)
    private String origin;
    @NotNull
    @Size(min = 3, max = 3)
    private String destination;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime departureDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime returnDate;
    @Min(value = 1)
    @Max(value = 4)
    private int numberOfPassengers;

}
