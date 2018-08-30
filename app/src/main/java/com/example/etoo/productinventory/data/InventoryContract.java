package com.example.etoo.productinventory.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract {

    private InventoryContract(){

    }
    public static final String CONTENT_AUTHORITY = "com.example.etoo.productinventory";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.products/products/ is a valid path for
     * looking at product data. content://com.example.android.products/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PRODUCTS= "products";


    /**
     * Inner class that defines constant values for the products database table.
     * Each entry in the table represents a single product.
     */
    public static final class ProductEntry implements BaseColumns{

        /** Name of database table for products */
        public final static String TABLE_NAME = "products";

        // Unique ID number for the product (only for use in the database table).
        //Type: INTEGER
        public final static String _ID = BaseColumns._ID;

        // Name of the product.
        // Type: TEXT
        public final static String COLUMN_PRD_NAME = "product_name";

        //Price of the product.
        // Type: INTEGER
        public final static String COLUMN_PRD_PRICE = "price";

        //Quantity of the product.
        // Type: INTEGER
        public final static String COLUMN_PRD_QUANTITY = "quantity";

        //Supplier of the product.
        // Type: TEXT
        public final static String COLUMN_PRD_SUPPLIER = "supplier";

        //Phone number of the product supplier.
        // Type: TEXT
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "phone";

        /** The content URI to access the product data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
    }

}
