package utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.vertica.sdk.*;

import java.util.List;

public class JsonParserArray extends TransformFunction {

    @Override
    public void processPartition(ServerInterface serverInterface, PartitionReader inputReader, PartitionWriter outputWriter) throws UdfException, DestroyInvocation {
        Configuration conf = Configuration.defaultConfiguration();
        conf = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        conf = conf.addOptions(Option.ALWAYS_RETURN_LIST);
        do {
            if (inputReader.isStringNull(0) | inputReader.isStringNull(1)) {
                outputWriter.setStringNull(0);
            } else {
                try {
                    List<Object> result = JsonPath.using(conf).parse(inputReader.getString(0)).read(inputReader.getString(1));

                    for (int i = 0; i <  result.size(); i++)  {
                        outputWriter.getStringWriter(0).copy(result.get(i).toString());
                        outputWriter.next();
                    }
                }
                catch (Exception ex)
                {
                    outputWriter.setStringNull(0);
                    outputWriter.next();
                }

            }
        } while (inputReader.next());
    }
}