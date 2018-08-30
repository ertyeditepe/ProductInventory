package com.example.etoo.productinventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.etoo.productinventory.data.InventoryContract.ProductEntry;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etoo.productinventory.data.InventoryContract;
import com.example.etoo.productinventory.data.ProductProvider;

public class ProductCursorAdapter extends CursorAdapter {
    private Button saleButton;
    private int updateQuantity;
    public static final String LOG_TAG = ProductProvider.class.getSimpleName();

    Context context;

    public ProductCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.inventory_item, viewGroup, false);

    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.nameView);
        TextView priceTextView = view.findViewById(R.id.priceView);
        TextView quantityTextView = view.findViewById(R.id.quantityView);
        Button saleButton = view.findViewById(R.id.saleButton);
        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRD_NAME));
        String price = cursor.getString(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRD_PRICE));
        final String quantity = cursor.getString(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRD_QUANTITY));

        nameTextView.setText(name);
        priceTextView.setText(price);
        quantityTextView.setText(quantity);

        final int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRD_QUANTITY);
        String currentQuantity = cursor.getString(quantityColumnIndex);
        final int quantityIntCurrent = Integer.valueOf(currentQuantity);

        final int productId = cursor.getInt(cursor.getColumnIndex(ProductEntry._ID));

        //Sell button which decrease quantity in storage
        saleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (quantityIntCurrent > 0) {
                    int newQuantity = quantityIntCurrent - 1;
                    Uri quantityUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, productId);

                    ContentValues values = new ContentValues();
                    values.put(ProductEntry.COLUMN_PRD_QUANTITY, newQuantity);
                    context.getContentResolver().update(quantityUri, values, null, null);
                    Toast.makeText(context, R.string.sold_product, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, R.string.out_of_stock, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}