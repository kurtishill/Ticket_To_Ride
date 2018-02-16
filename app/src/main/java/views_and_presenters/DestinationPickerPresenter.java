package views_and_presenters;

import java.util.ArrayList;

/**
 * Created by HillcollegeMac on 2/15/18.
 */

public class DestinationPickerPresenter implements IDestinationPickerPresenter {

    private ArrayList<String> mSelectedRoutes;

    public DestinationPickerPresenter(ArrayList<String> routes) {
        mSelectedRoutes = routes;
    }

    public boolean routeSelected(String selectedRoute) {
        if (!mSelectedRoutes.contains(selectedRoute)) {
            if (mSelectedRoutes.size() == 2) {
                mSelectedRoutes.clear();
                mSelectedRoutes.add(selectedRoute);
                return false;
            }
            mSelectedRoutes.add(selectedRoute);
            return true;
        }
        return true;
    }

    public ArrayList<String> getSelectedRoutes() {
        return mSelectedRoutes;
    }

    public void onClickRoutesChosen() {
        // do something with the routes chosen by the player
    }
}
