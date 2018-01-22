package com.mryang.uitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList =new ArrayList<>();
    private EditText input_text;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
        input_text = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = input_text.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//fresh
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    input_text.setText("");

                }
            }
        });
    }

    private void inits(){
        Msg msg1 = new Msg("Hello, Mr.yang.",Msg.TYPE_RECIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello Jack.What's up?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("oh,Nothing.",Msg.TYPE_RECIVED);
        msgList.add(msg3);

    }
}
