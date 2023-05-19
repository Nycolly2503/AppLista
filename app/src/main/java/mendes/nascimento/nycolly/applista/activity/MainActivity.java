package mendes.nascimento.nycolly.applista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import mendes.nascimento.nycolly.applista.R;

public class MainActivity extends AppCompatActivity {
    static int New_Item_Request=1;
    List<MyItem> itens = new ArrayList<>();

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenção do botão fab e atribuição de click no botão
        FloatingActionButton  fabAddItem = findViewById(R.id.fabAddNewItem);
        // Onclick para navegação para tela NewItemAcitvity
        fabAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i  = new Intent(MainActivity.this,NewItemActivity.class);
                startActivityForResult(i,New_Item_Request);
            }



        });

        RecyclerView rvItens=findViewById(R.id.rvItens);
        myAdapter=new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);
        rvItens.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);


        }
        @Override
        protected void onActivityResult(int requestCode,int resultCode, Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            if (requestCode==New_Item_Request){
                if (resultCode== Activity.RESULT_OK){
                    MyItem myItem=new MyItem();
                    myItem.title=data.getStringExtra("title");
                    myItem.description=data.getStringExtra("description");
                    myItem.photo=data.getData();
                    itens.add(myItem);
                    myAdapter.notifyItemInserted(itens.size()-1);

                }
            }

        }





    }




