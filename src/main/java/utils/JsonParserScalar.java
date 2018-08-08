package utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.vertica.sdk.*;

public class JsonParserScalar extends ScalarFunction {
    @Override
    public void processBlock(ServerInterface srvInterface, BlockReader argReader, BlockWriter resWriter) throws UdfException, DestroyInvocation {
        {
            Configuration conf = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);

            do {
                if (argReader.isStringNull(0) | argReader.isStringNull(1)) {
                    resWriter.setStringNull();
                } else {
                    try {
                        Object result = JsonPath.using(conf).parse(argReader.getString(0)).read(argReader.getString(1));
                        resWriter.setString(result.toString());
                    } catch (Exception ex) {
                        resWriter.setStringNull();
                    }
                }
                resWriter.next();
            }
            while (argReader.next());
        }
    }
}