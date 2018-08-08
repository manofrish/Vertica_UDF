import com.vertica.sdk.*;
import utils.JsonParserArray;

public class GetArrayFromJsonPath extends TransformFunctionFactory{
    @Override
    public TransformFunction createTransformFunction(ServerInterface serverInterface) {
        return new JsonParserArray();
    }

    @Override
    public void getPrototype(ServerInterface serverInterface, ColumnTypes argTypes, ColumnTypes returnType) {
        argTypes.addVarchar();
        argTypes.addVarchar();
        returnType.addVarchar();

    }

    @Override
    public void getReturnType(ServerInterface serverInterface, SizedColumnTypes inputTypes, SizedColumnTypes outputTypes) throws UdfException {
        outputTypes.addVarchar(inputTypes.getColumnType(0).getStringLength(),"value");

    }
}
