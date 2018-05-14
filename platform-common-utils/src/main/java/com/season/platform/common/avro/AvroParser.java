package com.season.platform.common.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by yingchun on 2017/7/27.
 */
public class AvroParser {
    public static void main(String[] args) {
        File file = new File("/Users/teambition/jiyc/mongo_slave_teambition_users_avro_processed+0+0140248675+0140254594.avro");
        DatumReader<base_users> userDatumReader =
                new SpecificDatumReader<base_users>(base_users.class);
        DataFileReader<base_users> dataFileReader = null;
        try {
            dataFileReader = new DataFileReader<base_users>(file, userDatumReader);
        } catch (IOException e) {
        }
        base_users user = null;
        try {
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves
                // us from allocating and garbage collecting many objects for
                // files with many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        } catch (IOException e) {
        }
    }
}
