package views_and_presenters;

import com.example.server.Results.Result;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public interface IChatPresenter {
    Result sendMessage(String message);
}
