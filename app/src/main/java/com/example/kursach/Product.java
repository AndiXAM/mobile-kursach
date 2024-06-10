package com.example.kursach;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    int id;
    String name;
    int price;
    int image;
    boolean box;

    Product(String _describe, int _image, boolean _box) {

        name = _describe;
        image = _image;
        box = _box;
    }

    Product(Parcel parcel) {
        id = parcel.readInt();
        price = parcel.readInt();
        image = parcel.readInt();
        name = parcel.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(price);
        dest.writeInt(image);
        dest.writeString(name);
    }
}
