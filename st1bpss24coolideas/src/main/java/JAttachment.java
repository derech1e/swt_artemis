import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JAttachment extends JContent {

    private File file;

    public JAttachment(String title, String description, File file) {
        super(title, description);
        if(file == null) throw new NullPointerException();
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        if(file == null) throw new NullPointerException();
        this.file = file;

        List<String> test = new ArrayList<>();
        

        for(String item : test) {
            System.out.println(item);
        }

        for(int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i));
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}