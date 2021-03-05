package osfix.ag.crm.domain.admin;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "fileuploadDownload")
@Data
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String fileName;
    private String fileType;
    @Lob
    private byte[] fileData;

    public FileModel(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;

    }
    public FileModel(String fileName, String fileType, byte[] fileData)     {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public FileModel() {

    }
}
