package study.my.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
//новое активити
public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceIntent", 0);
        double selectedPrice = receivedOrderIntent.getDoubleExtra("selectedPriceIntent", 0);
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("Customer Name: " + userName + "\n" + "Goods Name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" + "Price: " + selectedPrice + "\n" + "Order Price: " + orderPrice);
    }
}