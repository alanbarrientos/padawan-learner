package balan.codes.crazylist.languagedetector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GoogleLanguageDetectorTest {

    @Autowired
    GoogleLanguageDetector googleLanguageDetector;

    @Test
    public void shouldReturnEnglishLanguage() throws Throwable {
        String text = "Hello I'm Alan and I am writing in english";
        String language = googleLanguageDetector.detectLanguageOfText(text);
        assertThat(language).isEqualTo("en");
    }
}
