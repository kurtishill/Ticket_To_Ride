package views_and_presenters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.GameHistory;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link GameHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameHistoryFragment extends Fragment implements IGameHistoryView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IGameHistoryPresenter mGameHistoryPresenter;

    private RecyclerView mGameHistoryRecyclerView;
    private Button mCloseButton;
    private GameHistoryAdapter mAdapter;

    private TextView mGameHistoryListItemView;

    private OnCloseFragmentListener mListener;

    public GameHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameHistoryFragment newInstance(String param1, String param2) {
        GameHistoryFragment fragment = new GameHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private class GameHistoryHolder extends RecyclerView.ViewHolder{
        private GameHistory mGameHistoryItem;

        private GameHistoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game_history, parent, false));

            mGameHistoryListItemView = (TextView) itemView.findViewById(R.id.game_history_list_item_text_view);
        }

        private void bind(GameHistory gameHistoryItem) {
            mGameHistoryItem = gameHistoryItem;

            mGameHistoryListItemView.setText(mGameHistoryItem.toString());
            mGameHistoryListItemView.setTextColor(getResources().getColor(R.color.white));
            mGameHistoryListItemView.setBackgroundResource(GameResources.getBackgroundColors().get(mGameHistoryItem.getColor()));
        }
    }

    private class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryHolder> {
        private List<GameHistory> mGameHistoryList;

        public GameHistoryAdapter(List<GameHistory> gameHistories) {
            mGameHistoryList = gameHistories;
        }

        @Override
        public GameHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new GameHistoryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(final GameHistoryHolder holder, int position) {
            GameHistory gameHistory = mGameHistoryList.get(position);
            holder.setIsRecyclable(false);
            holder.bind(gameHistory);
        }

        @Override
        public int getItemCount() {
            return mGameHistoryList.size();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_history, container, false);

        mGameHistoryPresenter = new GameHistoryPresenter(this);

        mGameHistoryRecyclerView = (RecyclerView) v.findViewById(R.id.game_history_recycler_view);
        mGameHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        displayGameHistory();

        mCloseButton = (Button) v.findViewById(R.id.game_history_close_button);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClose();
                closeFragment();
            }
        });


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCloseFragmentListener) {
            mListener = (OnCloseFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCloseFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void displayGameHistory() {
        mAdapter = new GameHistoryAdapter(mGameHistoryPresenter.getGameHistoryList());
        mGameHistoryRecyclerView.setAdapter(mAdapter);
    }
}
