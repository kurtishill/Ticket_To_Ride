package views_and_presenters;

import com.example.server.Results.Result;

import java.util.ArrayList;

/**
 * Created by HillcollegeMac on 2/15/18.
 */

public interface IDestinationPickerPresenter {

    boolean routeSelected(String selectedRoute);

    ArrayList<String> getSelectedRoutes();

    void onClickRoutesChosen();

    Result drawThreeCards();

    void postExecuteDrawCards();

    boolean getRouteSelectionChange();
}
