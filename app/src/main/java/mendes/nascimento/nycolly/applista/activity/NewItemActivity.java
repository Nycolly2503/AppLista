package mendes.nascimento.nycolly.applista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import mendes.nascimento.nycolly.applista.R;
import mendes.nascimento.nycolly.applista.activity.Model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        //mantendo a imagem na rotação de tela
        NewItemActivityViewModel vm =new ViewModelProvider(this).get(NewItemActivityViewModel.class );
        Uri selectPhotoLocaition = vm.getSelectPhotoLocation();
        if(selectPhotoLocaition!=null){
            ImageView imvPhotoPrevew =findViewById(R.id.imvPhotoPrevew);
            imvPhotoPrevew.setImageURI(selectPhotoLocaition);
        }


        // definição do click para abertura da galeria
        ImageButton imgCI = findViewById(R.id.imbCI);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });


        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri selectPhotoLocaition = vm.getSelectPhotoLocation();
                if (selectPhotoLocaition == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent();
                i.setData(selectPhotoLocaition);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
               Uri photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPrevew);
                imvfotoPreview.setImageURI(photoSelected);
                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class );
                vm.setSelectPhotoLocation(photoSelected);


            }
        }
    }

}


