package com.yueyue.studentinfomanager.modules.main.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author : yueyue on 2018/2/8 18:05
 * desc   :
 */

public class Person implements Parcelable {
    public String number;
    public String name;
    public String gender;
    public String nativePlace;
    public String specialty;
    public String phone;
    public String birth;


    public Person() {
    };

    protected Person(Parcel in) {
        number = in.readString();
        name = in.readString();
        gender = in.readString();
        nativePlace = in.readString();
        specialty = in.readString();
        phone = in.readString();
        birth = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(nativePlace);
        dest.writeString(specialty);
        dest.writeString(phone);
        dest.writeString(birth);
    }


    @Override
    public String toString() {
        return Person.class.getSimpleName() + "{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", native_place='" + nativePlace + '\'' +
                ", specialty='" + specialty + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }

}

