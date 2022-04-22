package GUI;

import java.io.*;

public class LoginChecker {
    public boolean isUserExist(String name, String password) throws IOException {
        String path = "src/main/resources/userInfo/" + name +".txt";
        File f = new File(path);
        if(f.exists() && !f.isDirectory()){
            BufferedReader br = new BufferedReader(new FileReader(f));
            String userName = br.readLine();
            String pass = br.readLine();
            if(pass.equals(password))
                return true;
        }
        return false;
    }
}
