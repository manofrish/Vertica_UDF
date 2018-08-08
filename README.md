# Develop Vertica User Defined Extensions

This Gradle project is example of Vertica User Defined Functions

In this project I Develop two kind of UDFs.
1. 	User Defined Transform Function GetArrayFromJsonPath.
		The function GetArrayFromJsonPath return a result set of values from JSON list.
2. 	User Defined Scalar Function GetScalarValueFromJson.
		The function return a scalar, Return field value from JSON.
		
# Steps for implment the functions in Vertica.

Compile the code and package it into a JAR file.
We will run the VerticaUDF [shadowJar] task.
It will build the Jar vertica.udf.jar.
We need to upload it to one of the Vertica Node and give it relevant permission (in my example /opt/vertica/bin/).
Add our library to the database catalog using the CREATE LIBRARY statement.

	CREATE OR REPLACE LIBRARY jar_udx_functions AS '/opt/vertica/bin/vertica.udf.jar' LANGUAGE 'Java';
		
Define our UDTF in the catalog using the CREATE TRANSFORM FUNCTION statement.

	CREATE OR REPLACE TRANSFORM FUNCTION getListFromJsonPath AS LANGUAGE 'Java' NAME 'GetArrayFromJsonPath' LIBRARY jar_udx_functions;

Define our UDSF in the catalog using the CREATE FUNCTION statement.

	CREATE OR REPLACE FUNCTION getScalarFromJsonPath AS LANGUAGE 'Java' NAME 'GetScalarValueFromJson' LIBRARY jar_udx_functions;


# Now we can test them:

I'll use this json string for example:

	{
		"glossary": {
			"title": "example glossary",
			"GlossDiv": {
				"title": "S",
				"GlossList": {
					"GlossEntry": {
						"ID": "SGML",
						"SortAs": "SGML",
						"GlossTerm": "Standard Generalized Markup Language",
						"Acronym": "SGML",
						"Abbrev": "ISO 8879:1986",
						"GlossDef": {
							"para": "A meta-markup language, used to create markup languages such as DocBook.",
							"GlossSeeAlso": [
								"GML",
								"XML"
							]
						},
						"GlossSee": "markup"
					}
				}
			}
		}
	}

Create example table:

	CREATE TABLE IF NOT EXISTS public.udft_example
		(id BIGINT, json_content VARCHAR(65000));

Insert script:

	INSERT INTO public.udft_example (ad_id, json_content) 
		VALUES (1,'{"glossary":{"title":"example glossary","GlossDiv":{"title":"S","GlossList":{"GlossEntry":{"ID":"SGML","SortAs":"SGML","GlossTerm":"Standard Generalized Markup Language","Acronym":"SGML","Abbrev":"ISO 8879:1986","GlossDef":{"para":"A meta-markup language, used to create markup languages such as DocBook.","GlossSeeAlso":["GML","XML"]},"GlossSee":"markup"}}}}}');

Example of getting scalar value from Json query:

	SELECT id
		,getScalarFromJsonPath(json_content,'$.glossary.GlossDiv.GlossList.GlossEntry.GlossDef.para') AS para
		,getScalarFromJsonPath(json_content,'$.glossary.GlossDiv.GlossList.GlossEntry.Abbrev') AS Abbrev
		,getScalarFromJsonPath(json_content,'$.glossary.GlossDiv.GlossList.GlossEntry.GlossTerm') AS GlossTerm
	FROM public.udft_example
	
Result:
	
<!DOCTYPE html>
<html>
<body>
<table border="1" style="border-collapse:collapse">
<tr><th>id</th><th>para</th><th>Abbrev</th><th>GlossTerm</th></tr>
<tr><td>1</td><td>A meta-markup language, used to create markup languages such as DocBook.</td><td>ISO 8879:1986</td><td>Standard Generalized Markup Language</td></tr></table>
</body>
</html>

Example of getting rsultset from array:

	SELECT id
		,  getListFromJsonPath(json_content,'$.glossary.GlossDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[*]') OVER (PARTITION BY ad_id) AS GlossSeeAlso
		FROM public.udft_example

Result:

  <!DOCTYPE html>
<html>
<body>
<table border="1" style="border-collapse:collapse">
<tr><th>id</th><th>GlossSeeAlso</th></tr>
<tr><td>1</td><td>GML</td></tr>
<tr><td>1</td><td>XML</td></tr></table>
</body>
</html>
