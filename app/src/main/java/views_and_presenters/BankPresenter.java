package views_and_presenters;

/**
 * Created by HillcollegeMac on 2/16/18.
 */

public class BankPresenter implements IBankPresenter {

    private IBankView mBankView;

    public BankPresenter(IBankView v) {
        mBankView = v;
    }
}
