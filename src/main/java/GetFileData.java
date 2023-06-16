import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GetFileData {
    private static final String CHARSET = StaticVariables.CHARSET;
    private static final String FILE_NOT_FOUND_STRING = StaticVariables.FILE_NOT_FOUND;
    public static final int DEFAULT_BUFFER_SIZE = StaticVariables.DEFAULT_BUFFER_SIZE;
    public String getFileFromResourceAsStream(String fileName) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException(FILE_NOT_FOUND_STRING + fileName);
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
        return result.toString(CHARSET);
    }

}
