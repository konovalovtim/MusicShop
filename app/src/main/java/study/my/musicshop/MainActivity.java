package study.my.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.TimedText;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

//    запуск активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);

        createSpinner();
        createMap();

    }
//    создание спиннера
    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
    }
//    создание списка элементов
    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1000.0);
    }
    //    обработка нажатия на +
    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.coast);
        priceTextView.setText("" + quantity * price);
    }
    //    обработка нажатия на -
    public void degreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.coast);
        priceTextView.setText("" + quantity * price);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.coast);
        priceTextView.setText("" + quantity * price);

        ImageView itemImageView = findViewById(R.id.itemImage);
//        переключение imageview при выборе в спиннере
        switch (goodsName) {
            case "guitar":
                itemImageView.setImageResource(R.drawable.guitar);
                break;
                case "drums":
                itemImageView.setImageResource(R.drawable.drums);
                break;
                case "keyboard":
                itemImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                itemImageView.setImageResource(R.drawable.guitar);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.selectedPrice = order.orderPrice / quantity;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameIntent", order.userName);
        orderIntent.putExtra("goodsNameIntent", order.goodsName);
        orderIntent.putExtra("quantityIntent", order.quantity);
        orderIntent.putExtra("orderPriceIntent", order.orderPrice);
        orderIntent.putExtra("selectedPriceIntent", order.selectedPrice);
        startActivity(orderIntent);


    }
}