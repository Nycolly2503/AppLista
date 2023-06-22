package mendes.nascimento.nycolly.applista.activity.Model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import mendes.nascimento.nycolly.applista.activity.MyItem;

public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens =new ArrayList<>();

    public List<MyItem> getItens() {
        return itens;
    }
}
