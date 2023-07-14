package balan.codes.crazylist.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordProcessing {
    Date estimated;
    Boolean isSuccessful = false;
    Boolean callItAgain = false;
}
