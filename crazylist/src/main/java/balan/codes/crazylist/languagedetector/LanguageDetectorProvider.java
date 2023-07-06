package balan.codes.crazylist.languagedetector;

public interface LanguageDetectorProvider {
    String detectLanguageOfText(String text) throws Throwable;
}
