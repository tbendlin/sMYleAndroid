package com.example.theodorabendlin.mysmileandroid.database;

import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.SerializableType;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Custom data type persister. Use like so:
 * DatabaseField(persisterClass = SerializableCollectionsType.class)
 * private List<MyObject> objects;
 */
public class SerializableCollectionsType extends SerializableType {

    private static SerializableCollectionsType singleton;

    public SerializableCollectionsType() {
        super(SqlType.SERIALIZABLE, new Class<?>[0]);
    }

    public static SerializableCollectionsType getSingleton() {
        if (singleton == null) {
            singleton = new SerializableCollectionsType();
        }
        return singleton;
    }

    @Override
    public boolean isValidForField(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }
}
