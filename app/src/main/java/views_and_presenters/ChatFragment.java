package views_and_presenters;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.ChatMessage;
import com.example.server.Results.ChatResult;
import com.example.server.Results.Result;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class ChatFragment extends Fragment implements IChatView {
    private RecyclerView mChatRecyclerView;
    private Button send;
    private EditText messageToSend;
    private TextView listMessages;
    private TextView listName;
    private Button close;
    private ChatPresenter mChatPresenter;
    private String messageText;
    private ChatAdapter mAdapter;
    private OnCloseFragmentListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private class ChatHolder extends RecyclerView.ViewHolder{
        private ChatMessage mMessage;

        private ChatHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_chat, parent, false));

            listMessages = (TextView) itemView.findViewById(R.id.chat_list_item_text_view);
            listName = (TextView) itemView.findViewById(R.id.text_message_name);

        }

        private void bind(ChatMessage message) {
            mMessage = message;

            listMessages.setText(mMessage.getMessage());
            listName.setText(mMessage.getUsername());

            if (mMessage.getUsername().equals(mChatPresenter.getUser().getUsername())) {
                listMessages.setGravity(Gravity.END);
                listName.setGravity(Gravity.END);
            }

            listMessages.setBackgroundResource(GameResources.getChatBackgrounds().get(mMessage.getColor()));
        }
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
        private List<ChatMessage> mChatList;

        public ChatAdapter(List<ChatMessage> chat) {
            mChatList = chat;
        }

        @Override
        public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ChatFragment.ChatHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(final ChatFragment.ChatHolder holder, int position) {
            ChatMessage message = mChatList.get(position);
            //holder.setIsRecyclable(false);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return mChatList.size();
        }
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
            System.out.println("ADD SOMETHING IN CHAT ONCREATE ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_chat, container, false);

        mChatPresenter = new ChatPresenter(this);

        mChatRecyclerView = (RecyclerView) v.findViewById(R.id.chat_recycler_view);
        mChatRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        displayChat();

        send = (Button) v.findViewById(R.id.chat_send_button);
        send.setEnabled(false);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendMessageAsync().execute();//toggleViews(false);
                messageToSend.setText("");
                displayChat();

            }
        });

        messageToSend = (EditText) v.findViewById(R.id.chat_edit_text);
        messageToSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                messageText = charSequence.toString();
                if (messageText.isEmpty()){
                    send.setEnabled(false);
                }
                else {
                    send.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        close = (Button) v.findViewById(R.id.chat_close_button);
        close.isEnabled();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClose();
                closeFragment();
            }
        });
        return v;
    }

    private void closeFragment() { // its so hard to push
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        mChatPresenter.close();
    }

    @Override
    public void displayChat() { // todo the stuff wont show up after sent but it is sent back
        mAdapter = new ChatAdapter(ClientModelRoot.instance().getCurrGame().getChat());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mChatRecyclerView.setAdapter(mAdapter);
            }
        });
    }
    private class SendMessageAsync extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            return mChatPresenter.sendMessage(messageText);
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.isSuccess()) {
                ChatResult chatResult = (ChatResult) result;
                mChatPresenter.setChatList(chatResult.getChat());
            } else
                Toast.makeText(getActivity(), result.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
