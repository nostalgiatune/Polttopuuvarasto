package polttopuuvarasto.tiedonsiirto;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author Tuoppi
 */
public class FTPhandler {
    
    private final String connAddr;
    private URL url;
    private URLConnection conn;

    public FTPhandler(String host, String port, String username, String password) throws UnsupportedEncodingException {
        
        if (username.equals("") && password.equals(""))
            connAddr = "ftp://"+host+":"+port;
        else
            connAddr = "ftp://"+URLEncoder.encode(username, "UTF-8")+":"+URLEncoder.encode(password, "UTF-8")+"@"+host+":"+port;
    }
    
    public File download(String path) throws IOException {
        
        String addr = connAddr + "/" + path;
        url = new URL(addr);
        conn = url.openConnection();
        
        File file = new File(Polttopuuvarasto.CSV_FILE);
        file.createNewFile();
        
        try (InputStream in = conn.getInputStream();
                FileOutputStream out = new FileOutputStream(file)) 
        {
            int b;
            while ((b = in.read()) != -1)
                out.write(b);
            
            return file;
        }
    }
    
    public void upload(File file, String path) throws IOException {
        
        String addr = connAddr + "/" + path + "/" + Polttopuuvarasto.CSV_FILE;
        url = new URL(addr);
        conn = url.openConnection();
        
        try (OutputStream out = conn.getOutputStream();
                FileInputStream in = new FileInputStream(file))
        {
            int b;
            while ((b = in.read()) != -1)
                out.write(b);
        }
    }
    
}