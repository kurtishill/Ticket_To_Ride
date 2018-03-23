package views_and_presenters;

/**
 * Created by kurtishill on 3/15/18.
 */

public interface IClaimRouteView {

    void displayToast(String toast);

    void setSelectedRouteTextView(String routeNum);

    void setRadioButtons();

    void toggleChooseButton(boolean toggle);

}
