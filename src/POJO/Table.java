package POJO;

import java.util.concurrent.ConcurrentHashMap;

public class Table {


    public static final ConcurrentHashMap<String,String> DataMap = new ConcurrentHashMap<String,String>();

    public static ConcurrentHashMap<String,String> GetDataMap()

    {

        return DataMap;



    }




}
