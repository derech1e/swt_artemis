import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JAttachment extends JContent {

    private File file;

    public JAttachment(String title, String description, File file) {
        super(title, description);
        if (file == null) throw new NullPointerException();
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        if (file == null) throw new NullPointerException();
        this.file = file;
    }

    @Override
    public String toString() {
        return String.format("Attachment: %s\n%s", this.getTitle(), this.getDescription());
    }
}