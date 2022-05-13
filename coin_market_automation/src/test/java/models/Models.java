package models;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Models {
    public class MapResponse {
        public List<Maps> data;
    }

    public class Maps {
        public String id;
        public String name;
        public String symbol;
    }

    public class InfoResponse {
        public HashMap<String, String> status;
        public HashMap<String, Info> data;
    }


    public class Info {
        public String id;
        public String name;
        public String symbol;
        public String logo;
        public List<String> tags;
        public String date_added;
        public HashMap<String, List<String>> urls;

    }

}
