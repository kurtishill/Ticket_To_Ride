package views_and_presenters;

import com.example.server.Model.Route;
import com.example.server.Results.Result;

/**
 * Created by fryti on 3/14/2018.
 */

public interface IClaimRoutePresenter {
    public boolean hasEnoughTrains(Route route);

    public Result claimRoute(Route selectedRoute);

    public void selectRoute(String selectedRoute);
}
