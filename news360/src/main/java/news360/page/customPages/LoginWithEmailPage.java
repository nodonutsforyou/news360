package news360.page.customPages;

import lombok.Getter;
import news360.page.News360Page;
import org.openqa.selenium.support.FindBy;
import platform.independent.elements.primitives.Button;
import platform.independent.elements.primitives.TextField;

/**
 * Created by MVostrikov on 03.11.2016.
 */
public interface LoginWithEmailPage extends News360Page {

    TextField getEmail();
    TextField getPassword();
    Button getSubmit();

    void submit();
    void clickClose();
}
