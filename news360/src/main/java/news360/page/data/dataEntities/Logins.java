package news360.page.data.dataEntities;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * holder of persons
 * Created by MVostrikov on 21.09.2016.
 */
public class Logins {


    @XStreamImplicit(itemFieldName = "login")
    private List<Login> loginsList;


    public List<Login> getLoginList() {
        return getLoginList(null);
    }
    public List<Login> getLoginList(Login.LoginBehaviourPattern lbp) {
        if (loginsList==null) {
            loginsList = new ArrayList<>();
        }
        if (lbp!=null) {
            List<Login> values = new ArrayList<>();
            for (Login login : loginsList) {
                if (login.getStatus()==lbp) {
                    values.add(login);
                }
            }
            return values;
        }
        return loginsList;
    }
}
