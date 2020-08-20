package com.example.kamil.dyplomowa.utils;

import java.util.ArrayList;

/**
 * Created by Kamil on 29.11.2016.
 */

public class Items {

    private String name;

    private ArrayList<SubCategory> subCategoryList;

    public Items(String name, ArrayList<SubCategory> mSubCategoryList) {
        super();
        this.name = name;
        this.subCategoryList = mSubCategoryList;
    }


    public String getName() {
        return name;
    }

    public ArrayList<SubCategory> getmSubCategoryList() {
        return subCategoryList;
    }


    /**
     * second level item
     */

    public static class SubCategory {


        private String subCatName;
        private ArrayList<ItemList> itemListArray;

        public SubCategory(String subCatName,
                           ArrayList<ItemList> itemListArray) {
            super();
            this.subCatName = subCatName;
            this.itemListArray = itemListArray;

        }

        public String getpSubCatName() {
            return subCatName;
        }

        public void setpSubCatName(String pSubCatName) {
            this.subCatName = pSubCatName;
        }

        public ArrayList<ItemList> getItemListArray() {
            return itemListArray;
        }

        public void setItemListArray(ArrayList<ItemList> itemListArray) {
            this.itemListArray = itemListArray;
        }

        /**
         * third level item
         */
        public static class ItemList {

            private String itemName;

            public ItemList(String itemName) {
                super();
                this.itemName = itemName;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }


        }

    }

}
