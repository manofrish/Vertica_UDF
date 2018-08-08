package utils.test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

import java.util.List;

public class JsonParserTest {

    public Object getList(String content, String path) {
        Configuration conf = Configuration.defaultConfiguration();
        conf = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        conf = conf.addOptions(Option.ALWAYS_RETURN_LIST);
        List<Object> result = null;
        try {
            result = JsonPath.using(conf).parse(content).read(path);
        }
        catch (Exception ex)
        {
            return null;
        }


         return result;

    }

}

