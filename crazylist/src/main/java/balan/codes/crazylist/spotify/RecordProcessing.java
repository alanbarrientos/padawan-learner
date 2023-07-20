package balan.codes.crazylist.spotify;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecordProcessing {
    Date estimated;
    Boolean isSuccessful = false;
    Boolean callItAgain = false;
}
