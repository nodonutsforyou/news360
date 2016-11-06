package news360.page.data.dataEntities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * object to satore data about people
 * Created by MVostrikov on 21.09.2016.
 */
@Data()
public class Login {


    @XStreamAlias("email")
    @XStreamAsAttribute
    protected String email;

    @XStreamAlias("password")
    @XStreamAsAttribute
    protected String password;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @XStreamAlias("status")
    @XStreamAsAttribute
    protected String statusString;

    protected LoginBehaviourPattern status;

    public static Random rnd = new Random(System.currentTimeMillis()); //Todo I know, that it is a bad style to store random. But this is an only place we use it. So have't desided best place to store utility class with it

    public void setStatus(String status) {
        statusString = status;
        this.status = null;
        getStatus();
    }

    public enum LoginBehaviourPattern {
        active,
        wrongPassword,
        wrongEmail,
        notValidEmail,
        empty,
        tooLong
    }

    public LoginBehaviourPattern getStatus() {
        if (status== null) {
            switch (statusString) {
                case "wrongPassword":
                    status = LoginBehaviourPattern.wrongPassword;
                    break;
                case "wrongEmail":
                    status = LoginBehaviourPattern.wrongEmail;
                    break;
                case "notValidEmail":
                    status = LoginBehaviourPattern.notValidEmail;
                    break;
                case "empty":
                    status = LoginBehaviourPattern.empty;
                    break;
                case "tooLong":
                    status = LoginBehaviourPattern.tooLong;
                    break;
                case "active":
                default:
                    status = LoginBehaviourPattern.active;
                    break;
            }
        }
        return status;
    }

    public static Login EMPTY() {
        Login login = new Login();
        login.setEmail("");
        login.setPassword("");
        login.status = LoginBehaviourPattern.empty;
        return login;
    }

    public static Login RANDOM() {
        Login login = new Login();
        login.setEmail(generateString(10) + "@unexistingUrl.com");
        login.setPassword(generateString(10));
        login.status = LoginBehaviourPattern.wrongEmail;
        return login;
    }

    public static Login NOT_VALID_EMAIL() {
        Login login = new Login();
        login.setEmail(generateString(10));
        login.setPassword(generateString(10));
        login.status = LoginBehaviourPattern.notValidEmail;
        return login;
    }

    /**
     * generate random string. As rnd - it is bad to store it here.
     */
    private static String generateString(int size) {
        StringBuilder str = new StringBuilder();
//        str.append((char)(rnd.nextInt(26) + 'A')); //first is Uppercase
        for (int i=0; i<size; i++) { //if size<1 - will still return 1 capital char string
            str.append((char)(rnd.nextInt(26) + 'a'));
        }
        return str.toString();
    }


}
