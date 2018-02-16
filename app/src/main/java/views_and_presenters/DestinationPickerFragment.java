package views_and_presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hillcollegemac.tickettoride.R;

import java.util.ArrayList;

public class DestinationPickerFragment extends Fragment implements IDestinationPickerView {

    private static final String ROUTES = "routes";

    private TextView mRouteOne,
            mRouteTwo,
            mRouteThree;

    private Button mChooseButton;

    private IDestinationPickerPresenter mDestinationPickerPresenter;

    OnCloseFragmentListener mListener;

    public static DestinationPickerFragment newInstance(ArrayList<String> routes) {
        Bundle args = new Bundle();
        args.putSerializable(ROUTES, routes);

        DestinationPickerFragment fragment = new DestinationPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DestinationPickerFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnCloseFragmentListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must implement OnCloseFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Object routes = getArguments().get(ROUTES);
            ArrayList<String> pickableRoutes = new ArrayList<>();
            if (routes instanceof ArrayList) {
                pickableRoutes = (ArrayList) routes;
            }
            mDestinationPickerPresenter = new DestinationPickerPresenter(pickableRoutes);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_destination_picker, container, false);

        mRouteOne = (TextView) v.findViewById(R.id.destination_one_text_view);
        //mRouteOne.setText(mDestinationPickerPresenter.getSelectedRoutes().get(0));
        mRouteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = mDestinationPickerPresenter.routeSelected(mRouteOne.getText().toString());
                if (!select) {
                    mRouteTwo.setBackgroundColor(0);
                    mRouteThree.setBackgroundColor(0);
                    mChooseButton.setEnabled(false);
                }
                mRouteOne.setBackgroundColor(getResources().getColor(R.color.trans_light_blue));
                if (mDestinationPickerPresenter.getSelectedRoutes().size() == 2)
                    mChooseButton.setEnabled(true);
            }
        });

        mRouteTwo = (TextView) v.findViewById(R.id.destination_two_text_view);
        //mRouteTwo.setText(mDestinationPickerPresenter.getSelectedRoutes().get(1));
        mRouteTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = mDestinationPickerPresenter.routeSelected(mRouteTwo.getText().toString());
                if (!select) {
                    mRouteOne.setBackgroundColor(0);
                    mRouteThree.setBackgroundColor(0);
                    mChooseButton.setEnabled(false);
                }
                mRouteTwo.setBackgroundColor(getResources().getColor(R.color.trans_light_blue));
                if (mDestinationPickerPresenter.getSelectedRoutes().size() == 2)
                    mChooseButton.setEnabled(true);
            }
        });

        mRouteThree = (TextView) v.findViewById(R.id.destination_three_text_view);
        //mRouteThree.setText(mDestinationPickerPresenter.getSelectedRoutes().get(2));
        mRouteThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = mDestinationPickerPresenter.routeSelected(mRouteThree.getText().toString());
                if (!select) {
                    mRouteOne.setBackgroundColor(0);
                    mRouteTwo.setBackgroundColor(0);
                    mChooseButton.setEnabled(false);
                }
                mRouteThree.setBackgroundColor(getResources().getColor(R.color.trans_light_blue));
                if (mDestinationPickerPresenter.getSelectedRoutes().size() == 2)
                    mChooseButton.setEnabled(true);
            }
        });

        mChooseButton = (Button) v.findViewById(R.id.choose_button);
        mChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDestinationPickerPresenter.onClickRoutesChosen();
                mListener.onClose();
                closeFragment();
            }
        });

        return v;
    }

    private void deselect(TextView tv1, TextView tv2) {
        tv1.setBackgroundColor(getResources().getColor(R.color.white));
        tv2.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
