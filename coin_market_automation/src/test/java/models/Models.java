package models;

import java.util.List;

public class Models {
    public class MapResponse{
        public List<Maps> data;
    }
    public class Maps{
        public String id;
        public String name;
        public String symbol;
    }

}
