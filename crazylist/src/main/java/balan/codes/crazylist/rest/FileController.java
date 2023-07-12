package balan.codes.crazylist.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
   private static List<FileModal> fileModals = new ArrayList<>();

    // @GetMapping annotation for
    // mapping HTTP GET requests onto
    // specific handler methods. */
    @GetMapping("/file")
    public String getData() {
        return "file";
    }

    // @PostMapping annotation maps HTTP POST
    // requests onto specific handler methods
    @PostMapping("/file")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model modal) {
        try {
            // Declare empty list for collect the files data
            // which will come from UI
            List<FileModal> fileList = new ArrayList<FileModal>();
            for (MultipartFile file : files) {
                String fileContentType = file.getContentType();
                String sourceFileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
                String fileName = file.getOriginalFilename();
                FileModal fileModal = new FileModal(fileName, sourceFileContent, fileContentType);

                // Adding file into fileList
                fileList.add(fileModal);
            }

            // Saving all the list item into database
            fileModals.addAll(fileList);
//            fileServiceImplementation.saveAllFilesList(fileList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send file list to View using modal class
        // fileServiceImplementation.getAllFiles() used to
        // fetch all file list from DB
        modal.addAttribute("allFiles", fileModals);

        return "filelist";
    }
}
