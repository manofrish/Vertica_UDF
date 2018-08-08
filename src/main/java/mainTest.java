import utils.test.JsonParserTest;

public class mainTest {
    public static void main(String[] args) {
        String content = "{\"glossary\":{\"title\":\"example glossary\",\"GlossDiv\":{\"title\":\"S\",\"GlossList\":{\"GlossEntry\":{\"ID\":\"SGML\",\"SortAs\":\"SGML\",\"GlossTerm\":\"Standard Generalized Markup Language\",\"Acronym\":\"SGML\",\"Abbrev\":\"ISO 8879:1986\",\"GlossDef\":{\"para\":\"A meta-markup language, used to create markup languages such as DocBook.\",\"GlossSeeAlso\":[\"GML\",\"XML\"]},\"GlossSee\":\"markup\"}}}}}";
        JsonParserTest jsonParser = new JsonParserTest();
        jsonParser.getList(content, ",'$.store.b'");
        jsonParser.getList(content,"$.payload.allAssetIds[*]");
    }
}
