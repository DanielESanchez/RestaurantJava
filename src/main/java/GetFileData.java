import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GetFileData {
    private static final String charset = "UTF-8";
    private static final String fileNotFoundString = "file not found! ";
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public String getFileFromResourceAsStream(String fileName) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException(fileNotFoundString + fileName);
        } else {
            return convertInputStreamToString(inputStream);
        }
    }

    private static String convertInputStreamToString(InputStream is) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(charset);
    }

}
