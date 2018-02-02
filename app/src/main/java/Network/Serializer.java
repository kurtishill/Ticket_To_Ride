package Network;

import com.example.server.Results.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by tnels on 2/2/2018.
 */

public class Serializer {

    public static String encode(Object info)
    {
        Gson gson = new Gson();
        return gson.toJson(info, info.getClass());
    }

    public static Result decode(String json)
    {
        Gson gson = new Gson();
        return (Result) gson.fromJson(json, Result.class);
    }

    /**
     * Converts a InputStream to a string
     * @param is The inputStream to be converted to a string
     * @return The string of the stream
     * @throws IOException
     */
    public static String readString(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0)
        {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /**
     * Writes a string to an outputStreamWriter
     * @param str The string to be written
     * @param os The output stream to which the string will be written
     * @throws IOException
     */
    public static void writeString(String str, OutputStream os) throws IOException
    {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
