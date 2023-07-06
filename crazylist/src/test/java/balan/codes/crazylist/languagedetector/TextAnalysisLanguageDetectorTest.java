package balan.codes.crazylist.languagedetector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TextAnalysisLanguageDetectorTest {
    @Autowired
    TextAnalysisLanguageDetector textAnalysisLanguageDetector;

    @Test
    public void shouldReturnLanguageEnglish() throws Throwable {
        String text = "Hello I'm Alan and I am writing in english";
        String language = textAnalysisLanguageDetector.detectLanguageOfText(text);
        assertThat(language).isEqualTo("en");
    }
}
