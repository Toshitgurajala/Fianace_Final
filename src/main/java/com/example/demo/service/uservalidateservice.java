package com.example.demo.service;
import com.example.demo.dao.userdao;
import com.example.demo.model.userinfo;
import com.example.demo.model.usertransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class uservalidateservice {

    @Autowired
    userdao ud;

    public String validate(userinfo ui) {
        ui = ud.findByUsernameAndPassword(ui.getUsername(), ui.getPassword());
        try {
            if (ui.getUsername() != null)
                return String.valueOf(ui.getId());
        }
        catch(Exception e)
        {
            return null;
        }
      return " ";
    }

    public Boolean register(userinfo ur) {
        Boolean check = usernameexists(ur.getUsername());
        if (check)
            return false;
        else {
            userinfo newUser = new userinfo();
            newUser.setUsername(ur.getUsername());
            newUser.setPassword(ur.getPassword());
            ud.save(newUser);
        }
        return true;
    }

    private Boolean usernameexists(String username) {
        userinfo exist = ud.findByUsername(username);
        return exist != null;
    }

    private userinfo useridexists(String userid) {
        userinfo exist = ud.findByid(Long.valueOf(userid));
        return exist;
    }

    public Boolean updateuser(String userid, userinfo ui) {

        userinfo user = useridexists(userid);
        if (user != null) {
            if (ui.getUsername() != null) {
                if(ui.getUsername().length()<5)
                {
                    return false;
                }
                user.setUsername(ui.getUsername());
            }
            if (ui.getPassword() != null) {
                if(ui.getPassword().length()<8)
                {
                    return false;
                }
                user.setPassword(ui.getPassword());
            }
            ud.save(user);
            return true;
        }
        return false;
    }

    public String getuser(String userid) {
        userinfo ui = ud.findByid(Long.valueOf(userid));
        return ui.getUsername().toString();
    }


}
