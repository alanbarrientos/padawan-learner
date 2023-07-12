package balan.codes.crazylist.rest;

import jakarta.persistence.*;

//@Entity
//@Table(name = "filemodal")
public class FileModal {
    // @Id annotation specifies the
    // primary key of an entity
//    @Id

    // @GeneratedValue annotation Provides for the
    // specification of generation strategies
    // for the values of primary keys
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // @Column annotation specifies
    // the name of the column
//    @Column(name = "id")
    long id;
//    @Column(name = "name")
    String fileName;
//    @Lob
//    @Column(name = "content")
    String content;
//    @Column(name = "filetype")
    private String fileType;

    public FileModal() {
        super();
    }
    public FileModal(String fileName, String content, String fileType) {
        super();
        this.fileName = fileName;
        this.content = content;
        this.fileType = fileType;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
