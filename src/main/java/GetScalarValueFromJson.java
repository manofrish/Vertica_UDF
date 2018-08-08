import com.vertica.sdk.*;
import utils.JsonParserScalar;

public class GetScalarValueFromJson extends ScalarFunctionFactory {
    @Override
    public ScalarFunction createScalarFunction(ServerInterface srvInterface) {
        return new JsonParserScalar();
    }

    @Override
    public void getPrototype(ServerInterface srvInterface, ColumnTypes argTypes, ColumnTypes returnType) {
        argTypes.addVarchar();
        argTypes.addVarchar();
        returnType.addVarchar();
    }

    @Override
    public void getReturnType(ServerInterface serverInterface, SizedColumnTypes inputTypes, SizedColumnTypes outputTypes)
    {
         outputTypes.addVarchar(inputTypes.getColumnType(0).getStringLength(),"value");
    }
}
